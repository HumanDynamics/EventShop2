package backend;

import java.sql.Timestamp;

public class STTPoint {
	private static long uidCounter = 0;
	
	private LatLong latLong;
	private double value;
	private WrapperParams wrapperParams;
	private final long uid;
	private final Timestamp time;
	
	/**
	 * @param wrapperParams
	 * @param value
	 * @param latitude
	 * @param longitude
	 */
	public STTPoint(double value, Timestamp time, LatLong latLong, WrapperParams wrapperParams) {
		this.uid = uidCounter++;
		this.value = value;
		this.latLong = latLong;
		this.wrapperParams = wrapperParams;
		this.time = time;
	}
	
	public LatLong getLatLong() {
		return this.latLong;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public Timestamp getTimestamp() {
		return this.time;
	}
}
