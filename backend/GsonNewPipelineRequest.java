package backend;

import java.sql.Timestamp;

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
	public int pointPollingTimeMS;
	public int emageCreationRateMS;
	public int emageWindowLength;
	
	//Variables for use in DBWrapper, otherwise null
	public Timestamp DBstartTime;
	public Timestamp DBendTime;
	public Long DBactiveTimeWindowMS;
	public Long DBrefreshRegenerationRateMS;
	
	public String oauthAccessToken;
	public String oauthAccessTokenSecret;
	public String oauthConsumerKey;
	public String oauthConsumerKeySecret;
	
	
	public GsonNewPipelineRequest() {
	}
}
