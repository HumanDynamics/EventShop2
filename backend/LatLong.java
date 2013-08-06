package backend;


/**
 * Class to keep lat/long's more organized, and allow for possibility of helper methods
 * @author patrickmarx
 *
 */
public class LatLong {

	public final double latitude;
	public final double longitude;
	
	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
