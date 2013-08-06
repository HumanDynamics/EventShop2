package backend;

import java.util.Iterator;
import java.util.Arrays;

public class EmageBuilder {
		
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
	
	/*
	 * TODO: Takes a pointstream (and possibly a timewindow or some limiting factor) and
	 * returns an emage worthy of forwarding and adding to the DB
	 * 
	 * TODO: should we assume pointstream will always return something?
	 */
	
	public Emage buildEmage(PointStream pointStream, double timeWindow, Operator operator) throws Exception {
		Iterator<STTPoint> pointIterator = pointStream.getPointsForEmage(true);
		GeoParams geoParams = pointStream.getGeoParams();
		
		//Initialize grid to correct values
		double[][] valueGrid = createEmptyValueGrid(geoParams, operator);
		
		//For holding the counts so we can compute the avg of each cell
		double[][] avgAssistGrid = createEmptyValueGrid(geoParams, Operator.AVG);
		
		while (pointIterator.hasNext()) {
			STTPoint currPoint = pointIterator.next();
			int x = getXIndex(geoParams, currPoint);
			int y = getYIndex(geoParams, currPoint);
			
			switch (operator) {
			case MAX:
				valueGrid[x][y] = Math.max(valueGrid[x][y], currPoint.getValue());
				break;
			case MIN:
				valueGrid[x][y] = Math.max(valueGrid[x][y], currPoint.getValue());
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
		return new Emage(valueGrid, timeWindow, pointStream.getAuthFields(), 
				pointStream.getWrapperParams(), geoParams);
	}
	
	/*
	 * Assumes well formed bounding box, takes the absolute value of the differences.
	 */
	private double[][] createEmptyValueGrid(GeoParams geoParams, Operator operator) {
		double delta_x = Math.abs(geoParams.geoBoundNW.latitude - geoParams.geoBoundSE.latitude);
		double delta_y = Math.abs(geoParams.geoBoundNW.longitude - geoParams.geoBoundSE.longitude);
		
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
	
	/*
	 * TODO: This is definitely going to need some stronger error handling, for example near latitude 180 or 360 or whatever
	 */
	private int getXIndex(GeoParams geoParams, STTPoint sttPoint) {
		/*
		 *  assuming that since it's the top left corner, this will always be distance out from there
		 * TODO: possbily thats not the case (what if it's outside the bounding box???) maybe in that
		 * case we should just throw it out?
		 */
		double delta_x = Math.abs(geoParams.geoBoundNW.latitude - sttPoint.getLatLong().latitude);
		return (int) Math.round(delta_x/geoParams.geoResolutionX);
	}
	
	//TODO: same issues as getXIndex
	private int getYIndex(GeoParams geoParams, STTPoint sttPoint) {
		double delta_y = Math.abs(geoParams.geoBoundNW.longitude - sttPoint.getLatLong().longitude);
		return (int) Math.round(delta_y/geoParams.geoResolutionY);
	}
}
