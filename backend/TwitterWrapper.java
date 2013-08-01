package backend;

import java.util.ArrayList;

public class TwitterWrapper extends AbstractGeoWrapper {

	/**
	 * @param emageParams
	 * @param wrapperParams
	 * @param authFields
	 */
	public TwitterWrapper( WrapperParams wrapperParams,
			AuthFields authFields, GeoParams geoParams) {
		super( wrapperParams, authFields, geoParams);
		// TODO add suport for CSV params
	}

	@Override
	public
	ArrayList<STTPoint> getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

}
