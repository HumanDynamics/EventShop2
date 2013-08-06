package backend;

import java.util.ArrayList;

public class CSVWrapper extends AbstractGeoWrapper {
	@Override
	public
	ArrayList<STTPoint> getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param wrapperParams
	 * @param authFields
	 * @param geoResolution
	 * @param geoBoundingBoxSouthWest
	 * @param geoBoundingBoxNorthEast
	 * @param source
	 * @param theme
	 */
	public CSVWrapper(WrapperParams wrapperParams, AuthFields authFields,
			GeoParams geoParams) {
		super(wrapperParams, authFields, geoParams);
	}

}
