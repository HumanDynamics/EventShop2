package backend;


public class TestMain {

	public static void main(String[] args) {		
		final DataPipeline pipeline = buildNewPipeline(3,-3,-3,3,1,1,"Source","Theme", "TWITTER", "COUNT");
		
		Thread pointThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					System.out.println(pipeline.pointStream.getNextPoint());
				}
			} 	
		});
		
		Thread emageThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					System.out.println(pipeline.emageStream.getNextEmage());
				}
			}
		});
		
		pointThread.start();
		emageThread.start();
		
	}
	
	//TODO: this signature will change when this actually supports json, for now will use all primitives
	private static DataPipeline buildNewPipeline(double NWlat, double NWlong, double SElat, double SElong,
			double resolutionX, double resolutionY, String source, String theme, String wrapperType,
			String operatorType) {
		
		LatLong boundingBoxNW = new LatLong(NWlat, NWlong);
		LatLong boundingBoxSE = new LatLong(SElat, SElong);
		
		GeoParams geoParams = new GeoParams(resolutionX, resolutionY, boundingBoxNW, boundingBoxSE);
		AuthFields authFields = new AuthFields("", "", "", "");
		
		WrapperParams wrapperParams = new WrapperParams(source, theme);
		
		WrapperFactory.WrapperType type = WrapperFactory.WrapperType.valueOf(wrapperType);
		AbstractDataWrapper tw = WrapperFactory.getWrapperInstance(type, wrapperParams, authFields, geoParams);
		
		PointStream ps = new PointStream(tw);
		
		EmageBuilder eb = new EmageBuilder(ps, EmageBuilder.Operator.valueOf(operatorType));
		EmageStream es = new EmageStream(eb);
		
		return new DataPipeline(ps, es);
	}
}
