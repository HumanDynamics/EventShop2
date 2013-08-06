package backend;

public class STTPoint {
	private static long uidCounter = 0;
	
	private LatLong latLong;
	private double value;
	private WrapperParams wrapperParams;
	private final long uid;
	
	/**
	 * @param wrapperParams
	 * @param value
	 * @param latitude
	 * @param longitude
	 */
	public STTPoint(double value, LatLong latLong, WrapperParams wrapperParams) {
		this.uid = uidCounter++;
		this.value = value;
		this.latLong = latLong;
		this.wrapperParams = wrapperParams;
	}
	
	public LatLong getLatLong() {
		return this.latLong;
	}
	
	public double getValue() {
		return this.value;
	}
}
