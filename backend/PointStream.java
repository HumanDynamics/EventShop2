package backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class PointStream {
	
	public final AbstractDataWrapper wrapperReference;
	
	//TODO: This is how often we call getWrappedData() periodically
	private long pointPollingTimeMS;
	private Timestamp lastDataPullTime;
	private Iterator<STTPoint> pointIterator;
	private ArrayList<STTPoint> emagePointQueue;
	private ArrayList<STTPoint> mostRecentPoints;
	
	/**
	 * PollingTime defaults to 100MS, can be set after instantiation
	 * @param wrapperReference
	 */
	public PointStream(AbstractDataWrapper wrapperReference, int pointPollingTimeMS) {
		this.wrapperReference = wrapperReference;
		this.pointPollingTimeMS = pointPollingTimeMS;
		this.mostRecentPoints = new ArrayList<STTPoint>();
		this.emagePointQueue = new ArrayList<STTPoint>();
		this.lastDataPullTime = new Timestamp(System.currentTimeMillis());
	}
	
	public STTPoint getNextPoint() {
		long timeSinceLastPull = System.currentTimeMillis() - this.lastDataPullTime.getTime();
		
		if (timeSinceLastPull > this.pointPollingTimeMS) {
			ArrayList<STTPoint> newData = this.wrapperReference.getWrappedData();
			this.mostRecentPoints = newData;
			this.lastDataPullTime = new Timestamp(System.currentTimeMillis());
			
			this.emagePointQueue.addAll(newData);
			this.pointIterator = newData.iterator();
			return getNextPoint();
		} else {
			if (pointIterator == null || !pointIterator.hasNext()) {
				long sleepTime = 5 + this.pointPollingTimeMS - (System.currentTimeMillis() - this.lastDataPullTime.getTime());
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return getNextPoint();
			} else {
				return pointIterator.next();
			}
		}
	}
	
	
	/**
	 * Access point for the front end to get the most recent STTPoints from the datasource
	 * @return
	 */
	public ArrayList<STTPoint> getMostRecentPoints() {
		ArrayList<STTPoint> output = new ArrayList<STTPoint>(this.mostRecentPoints);
		return output;
	}
	
	protected ArrayList<STTPoint> getAndClearNewPointsQueue() {
		if (!(this.wrapperReference instanceof AbstractGeoWrapper)) {
			throw new IllegalArgumentException();
		}
		ArrayList<STTPoint> temp = this.emagePointQueue;
		this.emagePointQueue = new ArrayList<STTPoint>();
		return temp;
	}
	
	public GeoParams getGeoParams() {
		if (this.wrapperReference instanceof AbstractGeoWrapper) {
			return ((AbstractGeoWrapper) this.wrapperReference).getGeoParams();
		} else {
			//TODO: should never reach here because EmageBuilder will fail calling getPointsForEmage
			return null;
		}
	}
	
	public WrapperParams getWrapperParams() {
		return this.wrapperReference.getWrapperParams();
	}
	
	public AuthFields getAuthFields() {
		return this.wrapperReference.getAuthFields();
	}
	
	public void setPollingTimeMS(long pollTimeMS) {
		this.pointPollingTimeMS = pollTimeMS;
	}
}
