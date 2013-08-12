package backend;


/**
 * This is for keeping a linked emagestream and pointstream together until I
 * figure out a better way to do that
 * @author patrickmarx
 *
 */
public class DataPipeline {
	
	public final PointStream pointStream;
	public final EmageStream emageStream;
	
	public DataPipeline(PointStream pointStream, EmageStream emageStream) {
		this.pointStream = pointStream;
		this.emageStream = emageStream;
	}
}
