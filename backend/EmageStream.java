package backend;

import java.sql.Timestamp;

/*
 * TODO: Emage stream should have two submethods, one to get the points from Point Stream,
 * One to get call Emagebuilder to build the emage from those points at the correct time
 * 
 * TODO: Would it be better to just collapse this into the PointStream class???
 */


public class EmageStream {
	private EmageBuilder emageBuilder;
	private Timestamp lastEmageCreationTime;
	private long pollingTimeMS;
	private Timestamp timeWindowStart, timeWindowEnd;
	
	/**
	 * polling time defaults to 30000MS, can be set after instantiation
	 * @param emageBuilder
	 */
	public EmageStream(EmageBuilder emageBuilder) {
		this.emageBuilder = emageBuilder;
		
		//TODO: is it wise to have this be a default? should there be a separate constructor? 
		this.pollingTimeMS = 60*60*1000;
		this.lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
	}

	public Emage getNextEmage() {
		try {
			/*
			 * TODO: currently this just outputs an emage with a timewindow of from the last
			 * emage creation time to that time plus the length of the polling time. This should
			 * probably different, with enforcement of not calling getNextEmage until we're at a
			 * new polling window
			 */
			Emage output = this.emageBuilder.buildEmage(lastEmageCreationTime, 
					 new Timestamp(lastEmageCreationTime.getTime()+pollingTimeMS));
			lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
			return output;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setPollingTimeMS(long pollTimeMS) {
		this.pollingTimeMS = pollTimeMS;
	}
	
	public Timestamp getLastCreationTime() {
		return lastEmageCreationTime;
	}
}
