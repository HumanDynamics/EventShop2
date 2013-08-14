package backend;


/**
 * This is for keeping a linked emagestream and pointstream together until I
 * figure out a better way to do that
 * @author patrickmarx
 *
 */
public class DataPipeline {
	
	private static int idCounter=0;
	
	public final PointStream pointStream;
	public final EmageStream emageStream;
	public final int pipelineID;
	
	public DataPipeline(PointStream pointStream, EmageStream emageStream) {
		this.pointStream = pointStream;
		this.emageStream = emageStream;
		this.pipelineID = idCounter++;
	}
}
