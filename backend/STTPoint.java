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
	
	/**
	 * @param wrapperParams
	 * @param value
	 * @param latitude
	 * @param longitude
	 */
	public STTPoint(double value, Timestamp pullTime, LatLong latLong, WrapperParams wrapperParams) {
		this.uid = uidCounter++;
		this.value = value;
		this.latLong = latLong;
		this.wrapperParams = wrapperParams;
		this.pullTime = pullTime;
		this.localCreationTime = new Timestamp(System.currentTimeMillis());
	}
	
	public String toString(){
	    String output = "UID: "+this.uid+"\n Location: "+this.latLong.toString()+"\n Value: "+
	    this.value+"\n Pull Time: "+this.pullTime.toString()+"\n Creation Time: "+this.localCreationTime.toString()+"\n "
	    this.wrapperParams.toString();
	    return output;
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
