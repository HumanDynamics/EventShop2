package backend;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * TODO: PointStream needs to keep two copies of the data from the wrapper, one is an iterator that is a queue for getNextPoint
 * The other should be an array or something that can be added to, and every time an emage is made, all of it's data is used and
 * it is reset.
 */


public class PointStream {
	
	private final AbstractDataWrapper wrapperReference;
	
	//TODO: This is how often we call getWrappedData() periodically
	private double pollingTimeMS;
	private Iterator<STTPoint> pointIterator;
	private double emagePollingTimeMS;
	private ArrayList<STTPoint> emagePointQueue;
	
	//TODO: should this be a timestamp?
	private double lastEmageCreationTime;
	
	public PointStream(AbstractDataWrapper wrapperReference) {
		this.wrapperReference = wrapperReference;
		this.emagePointQueue = new ArrayList<STTPoint>();
	}
	
	public STTPoint getNextPoint() {
		if (pointIterator == null || !pointIterator.hasNext()) {
			ArrayList<STTPoint> newData = this.wrapperReference.getWrappedData();
			this.emagePointQueue.addAll(newData);
			this.pointIterator = newData.iterator();
			
			/*TODO: MUST FIX. This could easily be a source of infinite recursion. 
			 *  but assuming you will eventually get some data it shouldn't be an issue now,
			 *  and pollingTime should permanently fix this
			*/
			return getNextPoint();
		} else {
			return pointIterator.next();
		}
	}
	
	/**
	 * Method to be called periodically to receive all of the new points since it's last invoking
	 * @param resetQueue Whether or not to empty the queue of fresh point that will be used to generate emages
	 * @return
	 */
	public Iterator<STTPoint> getEmagePoints(boolean resetQueue) {
		Iterator<STTPoint> temp = this.emagePointQueue.iterator();
		if (resetQueue) {
			this.emagePointQueue = new ArrayList<STTPoint>();
		}
		return temp;
	}
	
}
