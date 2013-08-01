package backend;

/*
 * TODO: This probably needs getters and setters, still deciding what functionality this
 * class should actually have
 */

public class STTPoint {
	private static long uidCounter = 0;
	
	private double latitude;
	private double longitude;
	private double value;
	private WrapperParams wrapperParams;
	private final long uid;
	
	/**
	 * @param uid
	 * @param theme
	 * @param value
	 */
	public STTPoint(double value, double latitude, double longitude, WrapperParams wrapperParams) {
		this.uid = uidCounter++;
		this.value = value;
		this.latitude = latitude;
		this.longitude = longitude;
		this.wrapperParams = wrapperParams;
	}
}
