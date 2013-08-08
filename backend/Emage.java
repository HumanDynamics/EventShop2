 package backend;

public class Emage {

	private double[][] valueGrid;
	private final long emageUID;
	private final double timeWindowStart;
	private final double timeWindowEnd;
	private final WrapperParams wrapperParams;
	private final GeoParams geoParams;
	private AuthFields authFields;
	
	//TODO: should we worry about overflow here?
	private static long emageUIDCount = 0; 
	
	
	/**
	 * @param valueGrid
	 * @param timeWindow
	 * @param authFields
	 * @param wrapperParams
	 * @param geoParams
	 * @param emageUID
	 */
	public Emage(double[][] valueGrid, double timeWindowStart, double timeWindowEnd,
			AuthFields authFields, WrapperParams wrapperParams, GeoParams geoParams) {
		this.valueGrid = valueGrid;
		this.timeWindowStart = timeWindowStart;
		this.timeWindowEnd = timeWindowEnd;
		this.wrapperParams = wrapperParams;
		this.geoParams = geoParams;
		this.authFields = authFields;
		this.emageUID = emageUIDCount++;
	}
	
	public double[][] getValueGrid() {
		return this.valueGrid;
	}
}
