package backend;

public class AuthFields {
    
    private String accessToken;
    private String accessTokenSecret;
    private String consumerKey;
    private String consumerKeySecret;
    
    public AuthFields(String accessToken,String accessTokenSecret,String consumerKey,String consumerKeySecret){
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.consumerKey = consumerKey;
        this.consumerKeySecret = consumerKeySecret;
    }
    
    public String getAccessToken(){
        return this.accessToken;
    }
    public String getAccessTokenSecret(){
        return this.accessTokenSecret;
    }
    public String getConsumerKey(){
        return this.consumerKey;
    }
    public String getConsumerKeySecret(){
        return this.consumerKeySecret;
    }
}
