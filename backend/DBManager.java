package backend;

import java.util.Map;

public class DBManager {
	
	/*
	 * TODO: There's definitely a better way of storing the connections params,
	 * for right now I'm just saying username->usernameValue pass->passValue etc. just
	 * to get something down.
	 * It may even be possible we don't need to store the params after the connection is made idk.
	 */
	Map<String, String> connectionParams;
	
	//TODO pmarx: Initialize connection to SCIDB and access variables here
	public DBManager(Map<String, String> connectionParams) {
		this.connectionParams = connectionParams;
	}
	
	
	
	/*TODO pmarx: We might want these point and emage return methods to have a couple variants so
	 * that we can access by time or id or both?
	 */
	/*
	 * TODO: Need's arguments to specify which points are desired
	 *  (data source, bounding box, time window?)
	 */
	public PointStream getPointStream() {
		return null;
	}
	
	/*
	 * TODO: Need's arguments to specify which Emages are desired
	 *  (data source, time window?)
	 */
	public EmageStream getEmageStream() {
		return null;
	}
	
	/*
	 * TODO: Method for adding a single STTPoint to the DB, possibly only needed for testing
	 * or as a helper method, not sure.
	 */
	public void putPoint(STTPoint sttPoint) {
		
	}
	
	/*
	 * TODO: Method for adding points to the database for storage and retrieval
	 */
	public void putPoints(PointStream pointStream) {
		
	}
	
	/*
	 * TODO: Method to add a single Emage to the DB, possibly this could just be a helper?
	 */
	public void putEmage(Emage emage) {
		
	}
	
	/*
	 * TODO: Method for adding a bunch of Emages, probably immediately after their creation
	 */
	public void putEmages(EmageStream emageStream) {
		
	}
	
}
