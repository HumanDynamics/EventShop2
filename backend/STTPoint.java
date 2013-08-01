package backend;

/*
 * TODO: This probably needs getters and setters, still deciding what functionality this
 * class should actually have
 */

public class STTPoint {
	private int uid;
	
	//TODO: will we know these coordinates upon STTPoint creation?
	private double latitude;
	private double longitude;
	//TODO: How to we make theme stronger enforced than a string? needs to be fixed
	private String theme;
	private double value;
	
	/**
	 * @param uid
	 * @param theme
	 * @param value
	 */
	public STTPoint(int uid, String theme, double value, double latitude, double longitude) {
		this.uid = uid;
		this.theme = theme;
		this.value = value;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
