package backend;

public class TwitterWrapper extends AbstractDataWrapper {

	/**
	 * @param emageParams
	 * @param wrapperParams
	 * @param authFields
	 */
	public TwitterWrapper(
			WrapperParams wrapperParams,
			AuthFields authFields
			) {
		super(wrapperParams, authFields);
		
	}

	@Override
	PointStream getWrappedData() {
	    FilterQuery query= new FilterQuery();
        //query.track(keywords);
        //query.locations(locations);
        TwitterStream twitterStream= new TwitterStreamFactory().getInstance();
        twitterStream.setOAuthAccessToken(authFields.getAccessToken(),authFields.getAccessTokenSecret());
        
        twitterStream.setOAuthConsumer(authFields.getConsumerKey(),authFields.getConsumerKeySecret());
        
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
                STTPoint point = new STTPoint(**value**,time,new LatLong(latitude,longitude),params);
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

}
