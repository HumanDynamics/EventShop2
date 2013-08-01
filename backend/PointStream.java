package backend;

import java.util.Iterator;

public class PointStream {
	
	private final AbstractDataWrapper wrapperReference;
	
	//TODO: should this be a timestamp?
	private double pollingTimeMS;
	private Iterator<STTPoint> pointIterator;
	private double emagePollingTimeMS;
	
	//TODO: should this be a timestamp?
	private double lastEmageCreationTime;
	
	public PointStream(AbstractDataWrapper wrapperReference) {
		this.wrapperReference = wrapperReference;
	}
	
	public STTPoint getNextPoint() {
		if (pointIterator == null || !pointIterator.hasNext()) {
			pointIterator = this.wrapperReference.getWrappedData().iterator();
			
			/*TODO: MUST FIX. This could easily be a source of infinite recursion. 
			 *  but assuming you will eventually get some data it shouldn't be an issue now,
			 *  and pollingTime should permanently fix this
			*/
			return getNextPoint();
		} else {
			return pointIterator.next();
		}
	}
	
}
