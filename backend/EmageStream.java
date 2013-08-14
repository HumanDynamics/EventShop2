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
	private long emagePollingTimeMS;
	private Timestamp timeWindowStart, timeWindowEnd;
	
	/**
	 * polling time defaults to 30000MS, can be set after instantiation
	 * @param emageBuilder
	 */
	public EmageStream(EmageBuilder emageBuilder) {
		this.emageBuilder = emageBuilder;
		
		//TODO: is it wise to have this be a default? should there be a separate constructor? 
		this.emagePollingTimeMS = 20000;
		this.lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 
	 * @return Emage of the most recent points
	 */
	public Emage getNextEmage() {
		long timeSinceLastEmage = System.currentTimeMillis() - this.lastEmageCreationTime.getTime();
		Emage output;
		if (timeSinceLastEmage > this.emagePollingTimeMS) {
			output = this.emageBuilder.buildEmage(new Timestamp(0), new Timestamp(System.currentTimeMillis()));
			lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
		return output;
		} else {
			long sleepTime = 5 + this.emagePollingTimeMS - (System.currentTimeMillis() - this.lastEmageCreationTime.getTime());
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getNextEmage();
		}
	}
	
	public void setPollingTimeMS(long pollTimeMS) {
		this.emagePollingTimeMS = pollTimeMS;
	}
	
	public Timestamp getLastCreationTime() {
		return lastEmageCreationTime;
	}
}
