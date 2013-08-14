package backend;


public class TestMain {

	public static void main(String[] args) {		
		final DataPipeline pipeline = buildNewPipeline(50,50,30,70,1,1,"Source","Theme", "TWITTER", "COUNT");
		
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
	private static DataPipeline buildNewPipeline(double NElat, double NElong, double SWlat, double SWlong,
			double resolutionX, double resolutionY, String source, String theme, String wrapperType,
			String operatorType) {
		
		LatLong boundingBoxNE = new LatLong(NElat, NElong);
		LatLong boundingBoxSW = new LatLong(SWlat, SWlong);
		
		GeoParams geoParams = new GeoParams(resolutionX, resolutionY, boundingBoxNE, boundingBoxSW);
		AuthFields authFields = new AuthFields("", "", "", "");
		
		WrapperParams wrapperParams = new WrapperParams(source, theme);
		
		WrapperFactory.WrapperType type = WrapperFactory.WrapperType.valueOf(wrapperType);
		AbstractDataWrapper cw = WrapperFactory.getWrapperInstance(type, wrapperParams, authFields, geoParams);
		
		PointStream ps = new PointStream(cw);
		
		EmageBuilder eb = new EmageBuilder(ps, EmageBuilder.Operator.valueOf(operatorType));
		EmageStream es = new EmageStream(eb);
		
		return new DataPipeline(ps, es);
	}
}
