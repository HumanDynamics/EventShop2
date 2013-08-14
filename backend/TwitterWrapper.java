package backend;

import java.sql.Timestamp;
import java.util.ArrayList;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.GeoLocation;

public class TwitterWrapper extends AbstractGeoWrapper {

	/**
	 * @param wrapperParams
	 * @param authFields
	 */
    ArrayList<STTPoint> pointList;
    
	public TwitterWrapper(final WrapperParams wrapperParams, AuthFields authFields, GeoParams geoParams){
		super(wrapperParams, authFields, geoParams);
		pointList = new ArrayList<STTPoint>();
        FilterQuery query= new FilterQuery();
        query.track(new String[]{wrapperParams.getTheme()});
        //query.locations(locations);
        TwitterStream twitterStream= new TwitterStreamFactory().getInstance();
        //twitterStream.setOAuthAccessToken(new AccessToken(authFields.getAccessToken(),authFields.getAccessTokenSecret()));
        twitterStream.setOAuthConsumer("HbzFVHFA5NGqcXgGfn2w", "VPtqjXE0WQeQI0ao0FFMhR3wshaD8rLIZN3bfPGslE"); //hardcoded (this is my Twitter that I never use(d))
        //twitterStream.setOAuthConsumer(authFields.getConsumerKey(),authFields.getConsumerKeySecret());
        twitterStream.setOAuthAccessToken(new AccessToken("24302602-Fuukj26lTLqQcAASJyQa3MlgrcXhml0J6eGHSFOPx", "vwlI15Hx1rfz5GHLLh7OQhjGS8eKxK8jclezN8vXoo")); //hardcoded (this is my Twitter that I never use(d))
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                //System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                GeoLocation tweetLocation=status.getGeoLocation();
                double latitude = tweetLocation.getLatitude();
                double longitude = tweetLocation.getLongitude();
                String source = status.getSource();
                Timestamp time = new Timestamp((status.getCreatedAt()).getTime());
                WrapperParams params = new WrapperParams(source,wrapperParams.getTheme());
                STTPoint point = new STTPoint(1,time,new LatLong(latitude,longitude),params);
                pointList.add(point);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.filter(query);
	}

	@Override
	public ArrayList<STTPoint> getWrappedData() {
	    ArrayList<STTPoint> newList = new ArrayList<STTPoint>();
	    for(STTPoint point:pointList){
	        newList.add(point);
	    }
	    pointList.clear();
	    return newList;
	}

}
