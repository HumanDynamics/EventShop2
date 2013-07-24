package backend;

public abstract class AbstractDataWrapper {
	
	private final EmageParams emageParams;
	private final WrapperParams wrapperParams;
	private final AuthFields authFields;
	
	public AbstractDataWrapper(
			EmageParams emageParams,
			WrapperParams wrapperParams,
			AuthFields authFields  ) {
		this.emageParams = emageParams;
		this.wrapperParams = wrapperParams;
		this.authFields = authFields;
	}

	/**
	 * Tells a datasource to get more data, process it into a unified stream of STTPoints 
	 * and return it
	 * @return PointStream of the processed datasource
	 */
	abstract PointStream getWrappedData();
}
