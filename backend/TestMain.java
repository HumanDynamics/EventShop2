package backend;

public class TestMain {

	public static void main(String[] args) {
		
		LatLong boundingBoxNE = new LatLong(30,30);
		LatLong boundingBoxSW = new LatLong(10,50);
		
		GeoParams geoParams = new GeoParams(1, 1, boundingBoxNE, boundingBoxSW);
		AuthFields authFields = new AuthFields();
		WrapperParams wrapperParams = new WrapperParams("Source", "Theme");
		
		AbstractDataWrapper cw = new CSVWrapper(wrapperParams, authFields, geoParams);
		
		PointStream ps = new PointStream(cw);
		EmageBuilder eb = new EmageBuilder(ps, EmageBuilder.Operator.COUNT);
		EmageStream es = new EmageStream(eb);
		
		for (int i=0; i<100; i++) {
			System.out.println(ps.getNextPoint());
			if (i%25 == 0) {
				System.out.println(es.getNextEmage());
			}
		}
		
	}

}
