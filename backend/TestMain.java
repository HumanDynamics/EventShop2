package backend;


public class TestMain {

	public static void main(String[] args) {		
		DataPipeline pipeline = buildNewPipeline(50,50,30,70,1,1,"Source","Theme", "CSV", "COUNT");
		for (int i=0;i<100;i++) {
			System.out.println(pipeline.pointStream.getNextPoint());
		}
		System.out.println(pipeline.emageStream.getNextEmage());
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
		
		//USER CHOOSES OPERATOR
		EmageBuilder eb = new EmageBuilder(ps, EmageBuilder.Operator.valueOf(operatorType));
		EmageStream es = new EmageStream(eb);
		
		return new DataPipeline(ps, es);
	}
}
