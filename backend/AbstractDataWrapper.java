package backend;

import java.util.ArrayList;

public abstract class AbstractDataWrapper {
	
	private int wrapperId;
	private final WrapperParams wrapperParams;
	private final AuthFields authFields;
	private final int wrapperUID;
	
	private static int idCount = 0;
	
	
	public AbstractDataWrapper(
			WrapperParams wrapperParams,
			AuthFields authFields ) {
		this.wrapperParams = wrapperParams;
		this.authFields = authFields;
		this.wrapperUID = idCount++;

	}

	/**
	 * Tells a datasource to get more data, process it into a unified stream of STTPoints 
	 * and return it
	 * @return PointStream of the processed datasource
	 */
	public abstract ArrayList<STTPoint> getWrappedData();
}
