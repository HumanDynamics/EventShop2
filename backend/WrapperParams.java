package backend;

public class WrapperParams {
	private final int geoResolution;
	
	//TODO(pmarx): Should these be some sort of location data-structure?
	private final double southWestCorner;
	private final double northEastCorner;
	
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
			double southWestCorner,
			double northEastCorner,
			String source,
			String theme
			) {
		this.geoResolution = geoResolution;
		this.southWestCorner = southWestCorner;
		this.northEastCorner = northEastCorner;
		this.source = source;
		this.theme = theme;
	}
}
