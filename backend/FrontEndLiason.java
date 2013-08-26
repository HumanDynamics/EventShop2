package backend;

import com.google.gson.Gson;

public class FrontEndLiason {

	private Gson gson;
	private StreamHandler streamHandler;
	
	public FrontEndLiason(StreamHandler streamHandler) {
		this.gson = new Gson();
		this.streamHandler = streamHandler;
	}
	
	/**
	 * TODO: so this needs to get filled out more like with a status=success/fail etc
	 * but this is the general gist of it
	 * 
	 * This method will create a new datapipeline given a fully formed json request
	 * @param json
	 * @return Well formed json response for to be send back to the front end, including
	 * a fail message or a success message accompanied by the ID of the new data source
	 */
	public String newDataPipeline(String json) {
		GsonNewPipelineRequest request = gson.fromJson(json, GsonNewPipelineRequest.class);
		
		WrapperFactory.WrapperType type = WrapperFactory.WrapperType.valueOf(request.wrapperType);
		if (type == WrapperFactory.WrapperType.DATABASE) {
			if (request.DBstartTime == null || 
					request.DBendTime == null ||
					request.DBactiveTimeWindowMS == null ||
					request.DBrefreshRegenerationRateMS == null) {
				return "{Whatever the hell we want to say if it's missing fields}";
			}
		}
		
		int id = this.streamHandler.buildAndStartNewPipeline(request);
		return "{success: true, pipelineID:" +id+"}";
	}
	
}
