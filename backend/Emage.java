 package backend;

public class Emage {

	private double[][] valueGrid;
	private final long emageUID;
	private final double timeWindow;
	private final WrapperParams wrapperParams;
	private final GeoParams geoParams;
	private AuthFields authFields;
	
	//TODO: should we worry about overflow here?
	private static long emageUIDCount = 0; 
	
	
	/**
	 * @param valueGrid
	 * @param timeWindow
	 * @param authFields
	 * @param wrapperParams TODO
	 * @param geoParams TODO
	 * @param emageUID
	 */
	public Emage(double[][] valueGrid, double timeWindow,
			AuthFields authFields, WrapperParams wrapperParams, GeoParams geoParams) {
		this.valueGrid = valueGrid;
		this.timeWindow = timeWindow;
		this.wrapperParams = wrapperParams;
		this.geoParams = geoParams;
		this.authFields = authFields;
		this.emageUID = emageUIDCount++;
	}
	
	public STTPoint getPoint(int xcoord, int ycoord) {
		return null;
	}
	
	public double[][] getValueGrid() {
		return null;
	}
}
