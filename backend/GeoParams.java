package backend;

public class GeoParams {

	//TODO: THIS IS HOW LARGE A CELL IS, IN UNITS OF lat/long
	public final double geoResolutionX;
	public final double geoResolutionY;
	//use rounding for cells that don't quite fit the box
	
	public final LatLong geoBoundSE;
	public final LatLong geoBoundNW;
	
	/**
	 * @param geoResolution
	 * @param geoBoundSW
	 * @param geoBoundNE
	 */
	public GeoParams(double geoResolutionX, double geoResolutionY, LatLong geoBoundSW, LatLong geoBoundNE) {
		this.geoResolutionX = geoResolutionX;
		this.geoResolutionY = geoResolutionY;
		this.geoBoundSE = geoBoundSW;
		this.geoBoundNW = geoBoundNE;
	}
}
