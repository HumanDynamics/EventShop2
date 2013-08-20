package backend;

public class WrapperParams {
	
	/*
	 * Need to add fields startTime endTime activeTimeWindow refreshRegenerationRate
	 * These are for the database wrapper and will be null for all other wrappers. supa hax
	 */
	
	private final String source;
	private final String theme;
	
	/**
	 * @Author pmarx
	 * Basic setup just so it compiles
	 */ 
	public WrapperParams(
			String source,
			String theme
			) {
		this.source = source;
		this.theme = theme;
	}
	public String getSource(){
	    return this.source;
	}
	public String getTheme(){
	    return this.theme;
	}
	public String toString(){
	    return "Source: "+this.source+"\n Theme: "+this.theme;
	}
}
