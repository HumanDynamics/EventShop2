package backend;


/**
 * Class to keep lat/long's more organized, and allow for possibility of helper methods
 * @author patrickmarx
 *
 */
public class LatLong {

	public final double latitude;
	public final double longitude;
	
	/**
	 * Representation of a single point latitude/longitude. Fails if outside of reasonable values
	 * @param latitude  Latitudes plus (North) and minus (south) degrees from the equator -90<x<90
	 * @param longitude Longitudes plus (East) and minus (West) degrees from the equator -180<x<180
	 */
	public LatLong(double latitude, double longitude) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude outside of acceptable values (-90<x<90)");
		} else if (longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException("Longitude outside of acceptable values (-180<x<180)");
		}
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String toString() {
		return Double.toString(this.latitude)+","+Double.toString(this.longitude);
	}
}
