package backend;

import com.google.gson.Gson;

public class TestMain {
	
	public static void main(String[] args) throws InterruptedException {	
		StreamHandler s = new StreamHandler();
		//final int ID = s.buildAndStartNewPipeline(3,-3,-3,3,1,1,"Source","Theme", "TWITTER", "COUNT");
		String json = "{'NWlat':'3',"
				+ "'NWlong':'-3',"
				+ "'SElat':'-3',"
				+ "'SElong':'3',"
				+ "'resolutionX':'1',"
				+ "'resolutionY':'1',"
				+ "'source':'source',"
				+ "'theme':'theme',"
				+ "'wrapperType':'TWITTER',"
				+ "'operatorType':'COUNT'}";
		Gson gson = new Gson();
		GsonNewPipelineRequest request = gson.fromJson(json, GsonNewPipelineRequest.class);
		
		final int ID = s.buildAndStartNewPipeline(request);
		
		Thread.sleep(16000);
		
		System.out.println("MOST RECENT EMAGE FROM PIPELINE 0:");
		Emage recentEmage = s.getLatestEmageByPipelineID(0);
		System.out.println(recentEmage);
	}
}