package backend;

import java.sql.Timestamp;

public class STTPoint {
	private static long uidCounter = 0;
	
	private LatLong latLong;
	private double value;
	private WrapperParams wrapperParams;
	private final long uid;
	private final Timestamp localCreationTime;
	private final Timestamp pullTime;
	
	public STTPoint(double value, Timestamp pullTime, LatLong latLong, WrapperParams wrapperParams) {
		this.uid = uidCounter++;
		this.value = value;
		this.latLong = latLong;
		this.wrapperParams = wrapperParams;
		this.pullTime = pullTime;
		this.localCreationTime = new Timestamp(System.currentTimeMillis());
	}
	
	public LatLong getLatLong() {
		return this.latLong;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public Timestamp getCreationTime() {
		return this.localCreationTime;
	}
	
	public Timestamp getPullTime() {
		return this.pullTime;
	}
}
