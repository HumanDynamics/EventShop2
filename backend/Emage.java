package backend;

public class Emage {

	//TODO: is there a better datastructure for this? 
	STTPoint[][] valueGrid;
	/*
	 * TODO: I think we need more params here but I'm not sure how to store them...
	 * Maybe we should just pass the instance of WrapperParams and EmageParams to the Emage itself?
	 * will that be a probably when building an emage from the DB though?
	 */
	
	/**
	 * @param valueGrid
	 */
	public Emage(STTPoint[][] valueGrid) {
		this.valueGrid = valueGrid;
	}
	
	public EmageParams getEmageParams(){
		return null;
	}
	
	public STTPoint getPoint(int xcoord, int ycoord) {
		return null;
	}
	
	//TODO: Should value be a double?
	public void setPoint(int xcoord, int ycoord, int value) {

	}
	
	public STTPoint[][] getValueGrid() {
		return null;
	}
}
