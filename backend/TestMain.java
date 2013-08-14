package backend;

public class TestMain {
	
	public static void main(String[] args) {	
		StreamHandler s = new StreamHandler();
		final int ID = s.buildAndStartNewPipeline(3,-3,-3,3,1,1,"Source","Theme", "TWITTER", "COUNT");
	}
}
