package backend;

public class Emage {

	private double[][] valueGrid;
	private final long emageUID;
	private final double timeWindow;
	private final double geoBoundNE;
	private final double geoBoundSW;
	private final int geoResolution;
	private final String source;
	private final String theme;
	private AuthFields authFields;
	
	//TODO: should we worry about overflow here?
	private static long emageUIDCount = 0; 
	
	
	/**
	 * @param valueGrid
	 * @param emageUID
	 * @param timeWindow
	 * @param geoBoundNE
	 * @param geoBoundSW
	 * @param geoResolution
	 * @param source
	 * @param theme
	 * @param authFields
	 */
	public Emage(double[][] valueGrid, double timeWindow,
			double geoBoundNE, double geoBoundSW, int geoResolution,
			String source, String theme, AuthFields authFields) {
		this.valueGrid = valueGrid;
		this.timeWindow = timeWindow;
		this.geoBoundNE = geoBoundNE;
		this.geoBoundSW = geoBoundSW;
		this.geoResolution = geoResolution;
		this.source = source;
		this.theme = theme;
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
