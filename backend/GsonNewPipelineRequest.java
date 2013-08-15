package backend;

/**
 * Class used by Gson to create a java object from a new-data-json-request 
 * @author patrickmarx
 *
 */
public class GsonNewPipelineRequest {
	public double NWlat; 
	public double NWlong;
	public double SElat; 
	public double SElong;
	public double resolutionX; 
	public double resolutionY; 
	public String source;
	public String theme;
	public String wrapperType;
	public String operatorType;
	
	public GsonNewPipelineRequest() {
	}
}
