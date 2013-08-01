package backend;

public class WrapperParams {
	private final int geoResolution;
	
	//TODO: fill this in correctly
	//private final GeoPoint geoPoint;
	
	private final String source;
	
	//TODO(pmarx): Can we make theme an Enum or something similar based off a database list of
	//all the registered datasources we have?
	private final String theme;
	
	/*
	 * @Author pmarx
	 * Basic setup just so it compiles
	 */ 
	public WrapperParams(
			int geoResolution,
			String source,
			String theme
			) {
		this.geoResolution = geoResolution;
		this.source = source;
		this.theme = theme;
	}
}
