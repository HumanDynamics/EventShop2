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
	private double emagePollingTimeMS;
	private Timestamp timeWindowStart, timeWindowEnd;
	
	public EmageStream(EmageBuilder emageBuilder) {
		this.emageBuilder = emageBuilder;
	}

	public Emage getNextEmage() {
		try {
			return this.emageBuilder.buildEmage(timeWindowStart, timeWindowEnd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
