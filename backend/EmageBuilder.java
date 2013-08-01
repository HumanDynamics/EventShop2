package backend;

public class EmageBuilder {
	
	/*TODO: Can't decide if we should have an operator class or just build that functionality
	 *  into the EmageBuilder
	 */	
	private Operator operator;
	/*
	 * TODO: Takes a pointstream (and possibly a timewindow or some limiting factor) and
	 * returns an emage worthy of forwarding and adding to the DB
	 */
	
	public Emage buildEmage(PointStream pointStream, int timeWindow) {
		return null;
	}
}
