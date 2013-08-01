package backend;

public class GeoParams {

	private final int geoResolution;
	
	//TODO: Are these right? is these actually just a lat/long?
	private final double geoBoundSW;
	private final double geoBoundNE;
	
	/**
	 * @param geoResolution
	 * @param geoBoundSW
	 * @param geoBoundNE
	 */
	public GeoParams(int geoResolution, double geoBoundSW, double geoBoundNE) {
		this.geoResolution = geoResolution;
		this.geoBoundSW = geoBoundSW;
		this.geoBoundNE = geoBoundNE;
	}
}
