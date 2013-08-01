package backend;

import java.util.ArrayList;

public abstract class AbstractDataWrapper {
	
	private final WrapperParams wrapperParams;
	private final String[] authFields;
	private final int wrapperUID;
	
	private static int uidCounter = 0;
	
	
	public AbstractDataWrapper(
			WrapperParams wrapperParams,
			String[] authFields) {
		this.wrapperParams = wrapperParams;
		this.authFields = authFields;
		this.wrapperUID = uidCounter++;

	}

	/**
	 * Tells a datasource to get more data, process it into a unified stream of STTPoints 
	 * and return it
	 * TODO: dont quite have my finger on why but should this return return STTPoints or just
	 * the actual data
	 * @return PointStream of the processed datasource
	 */
	public abstract ArrayList<STTPoint> getWrappedData();
}
