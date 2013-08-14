package backend;

import java.util.ArrayList;
import java.util.Map;

public class StreamHandler {

	private static Map<Integer, DataPipeline> dataPipelines;
	
	/**
	 * Access point for the front end to get the most recent emage from
	 * a specific datasource
	 * @param id The ID of the datapipeline of the datasource to pull the most recent emage from
	 * @return
	 */
	public Emage getLatestEmageByPipelineID(int id) {
		//TODO: Probably need some sort of access control here or earlier
		return dataPipelines.get(id).emageStream.getMostRecentEmage();
	}
		
	/**
	 * Access point for the front end to get the most recent set of points from
	 * a specific datasource
	 * @param id The ID of the datapipeline of the datasource to pull the most recent points from
	 * @return
	 */
	public ArrayList<STTPoint> getLatestPointsByPipelineID(int id) {
		//TODO: probably need some sort of access control here or earlier
		return dataPipelines.get(id).pointStream.getMostRecentPoints();
	}
	
	/**
	 * TODO: consolidate all these arguments into one json object
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
	protected int buildAndStartNewPipeline(double NWlat, double NWlong, double SElat, double SElong,
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
