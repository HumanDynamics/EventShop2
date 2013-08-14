package backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * TODO: PointStream needs to keep two copies of the data from the wrapper, one is an iterator that is a queue for getNextPoint
 * The other should be an array or something that can be added to, and every time an emage is made, all of it's data is used and
 * it is reset.
 */


public class PointStream {
	
	public final AbstractDataWrapper wrapperReference;
	
	//TODO: This is how often we call getWrappedData() periodically
	private long pointPollingTimeMS;
	private Timestamp lastDataPullTime;
	private Iterator<STTPoint> pointIterator;
	private ArrayList<STTPoint> emagePointQueue;
	
	
	/**
	 * PollingTime defaults to 100MS, can be set after instantiation
	 * @param wrapperReference
	 */
	public PointStream(AbstractDataWrapper wrapperReference) {
		this.wrapperReference = wrapperReference;
		
		//TODO: is it wise to have this be a default? should there be a separate constructor? 
		this.pointPollingTimeMS = 500;
		this.emagePointQueue = new ArrayList<STTPoint>();
		this.lastDataPullTime = new Timestamp(System.currentTimeMillis());
	}
	
	public STTPoint getNextPoint() {
		long timeSinceLastPull = System.currentTimeMillis() - this.lastDataPullTime.getTime();
		
		if (timeSinceLastPull > this.pointPollingTimeMS) {
			ArrayList<STTPoint> newData = this.wrapperReference.getWrappedData();
			this.lastDataPullTime = new Timestamp(System.currentTimeMillis());
			
			//TODO: is this the right place to handle this? or should we find a way
			// to ensure the wrapper never returns a null result
			if (newData == null) {
				return getNextPoint();
			}
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
	 * Method to be called periodically to receive all of the new points since it's last invoking
	 * Currently hard fails if the Wrapper isn't a GeoWrapper, as Emage will not be buildable
	 * @param keepPointsAfter Points created after this time will be saved on the queue. Passing null empties the whole queue.
	 * @return
	 */
	public Iterator<STTPoint> getPointsForEmage(Timestamp keepPointsAfter) {
		
		if (!(this.wrapperReference instanceof AbstractGeoWrapper)) {
			throw new IllegalArgumentException();
		}
		Iterator<STTPoint> temp = this.emagePointQueue.iterator();
		if (keepPointsAfter == null) {
			this.emagePointQueue = new ArrayList<STTPoint>();
		} else {
			this.emagePointQueue = clearNonWindowPoints(this.emagePointQueue, keepPointsAfter);
		}
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
