package backend;

public class WrapperParams {
	
	private final String source;
	private final String theme;
	
	/*
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
}
