package backend;

import java.util.ArrayList;

public class AbstractGeoWrapper extends AbstractDataWrapper {

	private final int geoResolution;
	private final double geoBoundSW;
	private final double geoBoundNE;
	
	public AbstractGeoWrapper(
			WrapperParams wrapperParams, AuthFields authFields,
			int geoResolution,
			double geoBoundingBoxSouthWest, 
			double geoBoundingBoxNorthEast,
			String source, String theme) {
		super(wrapperParams, authFields, source, theme);
		this.geoResolution = geoResolution;
		this.geoBoundSW = geoBoundingBoxSouthWest;
		this.geoBoundNE = geoBoundingBoxNorthEast;
	}

	@Override
	public
	ArrayList<STTPoint> getWrappedData() {
		// TODO Auto-generated method stub
		return null;
	}

}
