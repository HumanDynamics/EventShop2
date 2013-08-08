package backend;

public class WrapperFactory {

	public enum WrapperType {
		TWITTER, CSV, DATABASE, PERSONALDATA
	}
	
	public static AbstractDataWrapper getWrapperInstance(WrapperType type, WrapperParams wrapperParams,
			AuthFields authFields, GeoParams geoParams) {
		
		AbstractDataWrapper wrapper = null;
		switch (type) {
			case TWITTER:
				wrapper = new TwitterWrapper(wrapperParams, authFields, geoParams);
			case CSV:
				wrapper = new CSVWrapper(wrapperParams, authFields, geoParams);
			case DATABASE:
				wrapper = new DBWrapper(wrapperParams, authFields, geoParams);
			case PERSONALDATA:
				//TODO: add personalDataWrapper here
		}
		return wrapper;
	}

}
