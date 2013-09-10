package backend;

public class TestMain {
	
	public static void main(String[] args) throws InterruptedException {	
		StreamHandler s = new StreamHandler();
		String twitter_json = "{'NWlat':'89.99',"
				+ "'NWlong':'-179.99',"
				+ "'SElat':'-89.99',"
				+ "'SElong':'179.99',"
				+ "'resolutionX':'60',"
				+ "'resolutionY':'30',"
				+ "'source':'source',"
				+ "'theme':'obama',"
				+ "'wrapperType':'TWITTER',"
				+ "'operatorType':'COUNT',"
				+ "'pointPollingRateMS':'500',"
				+ "'emageCreationRateMS':'7000',"
				+ "'emageWindowLength':'0',"
				+ "'oauthAccessToken':'HbzFVHFA5NGqcXgGfn2w',"
				+ "'oauthAccessTokenSecret':'VPtqjXE0WQeQI0ao0FFMhR3wshaD8rLIZN3bfPGslE',"
				+ "'oauthConsumerKey':'24302602-Fuukj26lTLqQcAASJyQa3MlgrcXhml0J6eGHSFOPx',"
				+ "'oauthConsumerKeySecret':'vwlI15Hx1rfz5GHLLh7OQhjGS8eKxK8jclezN8vXoo'}";
		FrontEndLiason liason = new FrontEndLiason(s);
		
		final String response = liason.newDataPipeline(twitter_json);
	}
}

//curl --data 'NWlat=89.99&NWlong=-179.99&SElat=-89.99&SElong=179.99&resolutionX=60&resolutionY=30&source=source&theme=obama&wrapperType=TWITTER&operatorType=COUNT&pointPollingRateMS=500&emageCreationRateMS=7000&emageWindowLength=0' localhost:8080/myapp/myresource


