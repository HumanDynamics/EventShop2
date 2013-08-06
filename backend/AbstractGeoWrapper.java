package backend;

public abstract class AbstractGeoWrapper extends AbstractDataWrapper {
	
	private GeoParams geoParams;
	
	public AbstractGeoWrapper(
			WrapperParams wrapperParams, AuthFields authFields,
			GeoParams geoParams ) {
		super(wrapperParams, authFields);
		this.geoParams = geoParams;
	}

	public GeoParams getGeoParams() {
		return this.geoParams;
	}
}
