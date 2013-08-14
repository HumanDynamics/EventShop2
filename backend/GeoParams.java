package backend;

public class GeoParams {

	//TODO: THIS IS HOW LARGE A CELL IS, IN UNITS OF lat/long
	public final double geoResolutionX;
	public final double geoResolutionY;
	
	public final LatLong geoBoundSE;
	public final LatLong geoBoundNW;
	
	/**
	 * @param geoResolution
	 * @param geoBoundSW
	 * @param geoBoundNE
	 */
	public GeoParams(double geoResolutionX, double geoResolutionY, LatLong geoBoundNW, LatLong geoBoundSE) {
		this.geoResolutionX = geoResolutionX;
		this.geoResolutionY = geoResolutionY;
		this.geoBoundSE = geoBoundSE;
		this.geoBoundNW = geoBoundNW;
	}
}
