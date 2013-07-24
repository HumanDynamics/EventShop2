package backend;

public class EmageBuilder {
	
	/*
	 * TODO: This definitely needs to be better, probably an enum or something an actual
	 * operator type. By operator I mean how are we aggregating the points into their buckets
	 */
	private String operator;

	/*
	 * TODO: Takes a pointstream (and possibly a timewindow or some limiting factor) and
	 * returns an emage worthy of forwarding and adding to the DB
	 */
	public Emage buildEmage(PointStream pointStream) {
		return null;
	}
}
