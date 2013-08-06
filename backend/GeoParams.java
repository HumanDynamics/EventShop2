package backend;

public class GeoParams {

	//TODO: THIS IS HOW LARGE A CELL IS, IN UNITS OF lat/long
	private final double geoResolutionX;
	private final double geoResolutionY;
	//use rounding for cells that don't quite fit the box
	
	private final LatLong geoBoundSW;
	private final LatLong geoBoundNE;
	
	/**
	 * @param geoResolution
	 * @param geoBoundSW
	 * @param geoBoundNE
	 */
	public GeoParams(double geoResolutionX, double geoResolutionY, LatLong geoBoundSW, LatLong geoBoundNE) {
		this.geoResolutionX = geoResolutionX;
		this.geoResolutionY = geoResolutionY;
		this.geoBoundSW = geoBoundSW;
		this.geoBoundNE = geoBoundNE;
	}
}
