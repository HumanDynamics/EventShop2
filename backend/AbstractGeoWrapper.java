package backend;

import java.util.ArrayList;

public class AbstractGeoWrapper extends AbstractDataWrapper {
	
	private GeoParams geoParams;
	
	public AbstractGeoWrapper(
			WrapperParams wrapperParams, AuthFields authFields,
			GeoParams geoParams ) {
		super(wrapperParams, authFields);
		this.geoParams = geoParams;
	}

	@Override
	public
	ArrayList<STTPoint> getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

	public GeoParams getGeoParams() {
		return this.geoParams;
	}
}
