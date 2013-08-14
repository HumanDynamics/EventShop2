package backend;

import java.util.Map;


public class TestMain {

	private static Map<Integer, DataPipeline> dataPipelines;
	
	public static void main(String[] args) {		
		final int ID = buildAndStartNewPipeline(3,-3,-3,3,1,1,"Source","Theme", "TWITTER", "COUNT");
	}
	

	/**
	 * Constructs a new data pipeline and adds it to our storage map indexed by it's ID, and then
	 * Starts the PointStream and EmageStream instances on their own threads to start processing Data
	 * @param NWlat
	 * @param NWlong
	 * @param SElat
	 * @param SElong
	 * @param resolutionX
	 * @param resolutionY
	 * @param source
	 * @param theme
	 * @param wrapperType
	 * @param operatorType
	 * @return ID of the newly created pipeline
	 */
	private static int buildAndStartNewPipeline(double NWlat, double NWlong, double SElat, double SElong,
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
		
		//Add the pipeline to our collection by it's index so we can reaccess it
		final DataPipeline p = new DataPipeline(ps, es);
		dataPipelines.put(p.pipelineID, p);
		
		//Start the streams
		Thread pointThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					System.out.println(p.pointStream.getNextPoint());
				}
			} 	
		});	
		Thread emageThread = new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					System.out.println(p.emageStream.getNextEmage());
				}
			}
		});
		pointThread.start();
		emageThread.start();
		
		return p.pipelineID;
	}
}
