package backend;

public class WrapperParams {
	
	private final String source;
	
	//TODO(pmarx): Can we make theme an Enum or something similar based off a database list of
	//all the registered datasources we have?
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
