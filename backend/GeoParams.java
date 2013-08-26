package backend;

public class GeoParams {

	//THIS IS HOW LARGE A CELL IS, IN UNITS OF lat/long
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
	public String toString(){
	    return "SouthEast Bound: "+this.geoBoundSE.toString()+", NorthWest Bound: "+this.geoBoundNW.toString()+
	            ", GeoResolution (X,Y): ("+this.geoResolutionX+","+this.geoResolutionY+")";
	}
}
