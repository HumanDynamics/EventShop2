package backend;

/*
 * TODO: Emage stream should have two submethods, one to get the points from Point Stream,
 * One to get call Emagebuilder to build the emage from those points at the correct time
 */


public class EmageStream {
	private EmageBuilder emageBuilder;
	
	public EmageStream(EmageBuilder emageBuilder) {
		this.emageBuilder = emageBuilder;
	}

	/*
	 * TODO: Method to get the next emage in the queue...this class might need some re-thinking
	 */
	public Emage getNextEmage() {
		return null;
	}
}
