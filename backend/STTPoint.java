package backend;

/*
 * TODO: This probably needs getters and setters, still deciding what functionality this
 * class should actually have
 */

public class STTPoint {
	private int uid;
	//TODO: should this be a geolocation or a coordinate in an integer system?
	//private GeoCoordinate geoCoordinate;
	//TODO: How to we make theme stronger enforced than a string? needs to be fixed
	private String theme;
	//TODO: At this point just assuming that the value is numerical, could be false though
	private double value;
	
	/**
	 * @param uid
	 * @param theme
	 * @param value
	 */
	public STTPoint(int uid, String theme, double value) {
		this.uid = uid;
		this.theme = theme;
		this.value = value;
	}
}
