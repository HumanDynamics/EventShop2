package backend;

public class TestMain {

	public static void main(String[] args) {
		
		//USER INPUT
		LatLong boundingBoxNE = new LatLong(30,30);
		LatLong boundingBoxSW = new LatLong(10,50);
		
		//1 1 USER INPUT
		GeoParams geoParams = new GeoParams(1, 1, boundingBoxNE, boundingBoxSW);
		AuthFields authFields = new AuthFields("", "", "", "");
		
		//USER INPUT
		WrapperParams wrapperParams = new WrapperParams("Source", "Theme");
		
		//USER INPUT STRING->ENUM->Wrapper
		String userWrapperInput = "CSV";
		WrapperFactory.WrapperType type = WrapperFactory.WrapperType.valueOf(userWrapperInput);
		AbstractDataWrapper cw = WrapperFactory.getWrapperInstance(type, wrapperParams, authFields, geoParams);
		
		//USER INPUT, modifiable at runtime
		long pointPollingTimeMS = 100;
		long emagePollingTimeMS = 10000;
		
		PointStream ps = new PointStream(cw);
		ps.setPollingTimeMS(pointPollingTimeMS);
		
		//USER CHOOSES OPERATOR
		EmageBuilder eb = new EmageBuilder(ps, EmageBuilder.Operator.COUNT);
		EmageStream es = new EmageStream(eb);
		es.setPollingTimeMS(emagePollingTimeMS);
		
		//THIS IS WHERE SCHEDULING COMES IN
		for (int i=0; i<1000; i++) {
			System.out.println(ps.getNextPoint());
		}
		System.out.println(es.getNextEmage());
	}
}
