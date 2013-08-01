package backend;

public class WrapperParams {
	
	private final String source;
	
	//TODO(pmarx): How do we use theme and should it be just a string?
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
}
