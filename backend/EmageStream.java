package backend;

import java.sql.Timestamp;

public class EmageStream {
	private EmageBuilder emageBuilder;
	private Timestamp lastEmageCreationTime;
	private long emageCreationRateMS;
	
	//TODO: do we even need these here? how do we get them?
	private Timestamp timeWindowStart, timeWindowEnd;
	private Emage mostRecentEmage;
	
	/**
	 * polling time defaults to 30000MS, can be set after instantiation
	 * @param emageBuilder
	 */
	public EmageStream(EmageBuilder emageBuilder, int emageCreationRateMS) {
		this.emageBuilder = emageBuilder;
		this.emageCreationRateMS = emageCreationRateMS;
		this.lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 
	 * @return Emage of the most recent points
	 */
	public Emage getNextEmage() {
		long timeSinceLastEmage = System.currentTimeMillis() - this.lastEmageCreationTime.getTime();
		Emage output;
		if (timeSinceLastEmage > this.emageCreationRateMS) {
			output = this.emageBuilder.buildEmage(new Timestamp(0), emageCreationRateMS);
			this.mostRecentEmage = output;
			lastEmageCreationTime = new Timestamp(System.currentTimeMillis());
		return output;
		} else {
			long sleepTime = 5 + this.emageCreationRateMS - (System.currentTimeMillis() - this.lastEmageCreationTime.getTime());
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getNextEmage();
		}
	}
	
	public Emage getMostRecentEmage() {
		return this.mostRecentEmage;
	}
	
	public void setCreationRateMS(long pollTimeMS) {
		this.emageCreationRateMS = pollTimeMS;
	}
	
	public Timestamp getLastCreationTime() {
		return lastEmageCreationTime;
	}
}
