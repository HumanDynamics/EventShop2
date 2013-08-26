package backend;

import java.sql.Timestamp;

public class WrapperParams {
	
	/*
	 * Need to add fields startTime endTime activeTimeWindow refreshRegenerationRate
	 * These are for the database wrapper and will be null for all other wrappers. supa hax
	 */
	
	private final String source;
	private final String theme;
	private Timestamp DBstartTime;
	private Timestamp DBendTime;
	private long DBactiveTimeWindowMS;
	private long DBrefreshRegenerationRateMS;
	

	public WrapperParams(
			String source,
			String theme
			) {
		this.source = source;
		this.theme = theme;
	}
	

	/**
	 * @param source
	 * @param theme
	 * @param startTime
	 * @param endTime
	 * @param activeTimeWindowMS
	 * @param refreshRegenerationRateMS
	 */
	public WrapperParams(String source, String theme, Timestamp startTime,
			Timestamp endTime, long activeTimeWindowMS,
			long refreshRegenerationRateMS) {
		this.source = source;
		this.theme = theme;
		this.DBstartTime = startTime;
		this.DBendTime = endTime;
		this.DBactiveTimeWindowMS = activeTimeWindowMS;
		this.DBrefreshRegenerationRateMS = refreshRegenerationRateMS;
	}
	
	
	public String getSource(){
	    return source;
	}
	public String getTheme(){
	    return theme;
	}
	public String toString(){
	    return "Source: "+source+"\n Theme: "+theme;
	}
	public Timestamp getStartTime() {
		return DBstartTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.DBstartTime = startTime;
	}
	public Timestamp getEndTime() {
		return DBendTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.DBendTime = endTime;
	}
	public long getActiveTimeWindowMS() {
		return DBactiveTimeWindowMS;
	}
	public void setActiveTimeWindowMS(long activeTimeWindowMS) {
		this.DBactiveTimeWindowMS = activeTimeWindowMS;
	}
	public long getRefreshRegenerationRateMS() {
		return DBrefreshRegenerationRateMS;
	}
	public void setRefreshRegenerationRateMS(long refreshRegenerationRateMS) {
		this.DBrefreshRegenerationRateMS = refreshRegenerationRateMS;
	}
}
