package backend;

public class TwitterWrapper extends AbstractDataWrapper {

	/**
	 * @param emageParams
	 * @param wrapperParams
	 * @param authFields
	 */
    private
    
	public TwitterWrapper(
			EmageParams emageParams, 
			WrapperParams wrapperParams,
			String[] authfields
			) {
		super(emageParams, wrapperParams, authFields);
		
	}

	@Override
	PointStream getWrappedData() {
	    FilterQuery query= new FilterQuery();
        //query.track(keywords);
        //query.locations(locations);
        TwitterStream twitterStream= new TwitterStreamFactory().getInstance();
        twitterStream.setOAuthConsumer(authFields[0],authFields[1]);
        
        twitterStream.setOAuthConsumer(authFields[2],authFields[3]);
        
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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
