package backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class EmageBuilder {
	
	private final PointStream pointStream;
	private final Operator operator;
	private ArrayList<STTPoint> pointQueue;
	
	public EmageBuilder(PointStream pointStream, Operator operator) {
		this.pointStream = pointStream;
		this.operator = operator;
		this.pointQueue = new ArrayList<STTPoint>();
	}
		
	/**
	 * Enum class for specifiying which type of aggregation happens in the EmageBuilder
	 * 
	 * op-MAX -> maximum value of all the STTPoints that fit in that EmageCell
	 * op-MIN -> min '''''
	 * sum -> sum of all values of the STTPoints in that specific cell
	 * count -> number of STTPoint in that specfic cell
	 * avg -> avg value of the STTPoints in that cell
	 */
	public enum Operator {
		MAX, MIN, SUM, COUNT, AVG
	}
	
	/**
	 * Takes the most recent STTPoints from it's point stream, and aggregates them into
	 * a value grid based on the operator its instantiated with
	 * @param timeWindowStart Only points created after this Timestamp will be included
	 * @param timeWindowEnd Only points created before this Timestamp will be included
	 * @return
	 */

	public Emage buildEmage(Timestamp timeWindowStart, long windowSizeMS) {
		
		this.pointQueue.addAll(this.pointStream.getAndClearNewPointsQueue());
		Iterator<STTPoint> pointIterator = this.pointQueue.iterator();
		
		//TODO: This needs to change to allow for a rolling window
		this.pointQueue = new ArrayList<STTPoint>();
		
		GeoParams geoParams = this.pointStream.getGeoParams();
		
		//Initialize grid to correct values
		double[][] valueGrid = createEmptyValueGrid(geoParams, this.operator);
		
		//For holding the counts so we can compute the avg of each cell
		double[][] avgAssistGrid = createEmptyValueGrid(geoParams, Operator.AVG);
		
		while (pointIterator.hasNext()) {
			STTPoint currPoint = pointIterator.next();
			
			boolean isWithinTimeWindow = currPoint.getCreationTime().after(timeWindowStart);
			
			if (isWithinTimeWindow) {	
				int x = getXIndex(geoParams, currPoint);
				int y = getYIndex(geoParams, currPoint);
				
				switch (this.operator) {
				case MAX:
					valueGrid[x][y] = Math.max(valueGrid[x][y], currPoint.getValue());
					break;
				case MIN:
					valueGrid[x][y] = Math.min(valueGrid[x][y], currPoint.getValue());
					break;
				case SUM:
					valueGrid[x][y] += currPoint.getValue();
					break;
				case COUNT:
					valueGrid[x][y]++;
					break;
				case AVG:
					double total = valueGrid[x][y]*avgAssistGrid[x][y];
					//Add one to the count from that cell, 
					valueGrid[x][y] = (total+currPoint.getValue())/++avgAssistGrid[x][y];
					break;
				}
			}
		}
		return new Emage(valueGrid, timeWindowStart, new Timestamp(System.currentTimeMillis()), this.pointStream.getAuthFields(), 
				this.pointStream.getWrapperParams(), geoParams);
	}
	
	/*
	 * Assumes well formed bounding box, takes the absolute value of the differences.
	 */
	private double[][] createEmptyValueGrid(GeoParams geoParams, Operator operator) {
		double delta_y = Math.abs(geoParams.geoBoundNW.latitude - geoParams.geoBoundSE.latitude);
		double delta_x;
		if (geoParams.geoBoundNW.longitude > geoParams.geoBoundSE.longitude) {
			//We've wrapped around from 180 to -180,
			delta_x = 360-(geoParams.geoBoundNW.longitude+geoParams.geoBoundSE.longitude);
		} else {
			delta_x = Math.abs(geoParams.geoBoundNW.longitude - geoParams.geoBoundSE.longitude);
		}
		
		int x_width = (int) Math.round(delta_x/geoParams.geoResolutionX);
		int y_width = (int) Math.round(delta_y/geoParams.geoResolutionY);
		
		double[][] valueGrid = new double[x_width][y_width];
		
		if (operator.equals(Operator.MAX)) {
			for (int i=0; i<x_width; i++) {
				Arrays.fill(valueGrid[i], Integer.MIN_VALUE);
			}
		}
		if (operator.equals(Operator.MIN)) {
			for (int i=0; i<x_width; i++) {
				Arrays.fill(valueGrid[i], Integer.MAX_VALUE);
			}
		}
		return valueGrid;
	}

	/**
	 * So sorry if you have to read this code. Probably another way to do this logic
	 * but this was what made sense to me
	 * @return index of the point in the grid, or -1 if it is outside of the grid
	 */
	private int getXIndex(GeoParams geoParams, STTPoint sttPoint) {
		/*
		 * TODO: what if it's outside the bounding box???) maybe in that case we should
		 *  just throw it out?
		 */
		double delta_x;
		if (geoParams.geoBoundNW.longitude > geoParams.geoBoundSE.longitude && 
				geoParams.geoBoundNW.longitude > sttPoint.getLatLong().longitude) {
			//Our point is wrapped around 180/-180
			if (geoParams.geoBoundSE.longitude > sttPoint.getLatLong().longitude) {
				delta_x =  (180 - geoParams.geoBoundNW.longitude) + (180 + sttPoint.getLatLong().longitude);
			} else {
				//Outside of the bounding box
				return -1;
			}
		} else {
			if (geoParams.geoBoundNW.longitude > geoParams.geoBoundSE.longitude || 
					geoParams.geoBoundSE.longitude < sttPoint.getLatLong().longitude) {
				//Outside of the boudning box
				return -1;
			} else {
			delta_x = Math.abs(geoParams.geoBoundNW.longitude - sttPoint.getLatLong().longitude);
			}
		}
		return (int) Math.round(delta_x/geoParams.geoResolutionX);
	}

	/**
	 * @return index of the point in the grid, or -1 if it is outside of the grid
	 */
	private int getYIndex(GeoParams geoParams, STTPoint sttPoint) {
		double delta_y = Math.abs(geoParams.geoBoundNW.latitude - sttPoint.getLatLong().latitude);
		return (int) Math.round(delta_y/geoParams.geoResolutionY);
	}
	
	/**
	 * Helper method for enabling a rolling time window. Returns an array of all
	 * the points that were created after a certain time
	 * TODO: is there a more efficient way of doing this? does it matter?
	 */
	private ArrayList<STTPoint> clearNonWindowPoints(ArrayList<STTPoint> list, Timestamp keepPointsAfter) {
		ArrayList<STTPoint> output = new ArrayList<STTPoint>();
		for (int i=0;i<list.size();i++){
			if (keepPointsAfter.before(list.get(i).getCreationTime())) {
				output.add(list.get(i));
			}
		}
		return output;
	}
}
