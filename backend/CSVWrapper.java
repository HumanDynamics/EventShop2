package backend;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Timestamp;

public class CSVWrapper extends AbstractGeoWrapper {
	@Override
	public ArrayList<STTPoint> getWrappedData() {
	    ArrayList<STTPoint> pointList = new ArrayList<STTPoint>();
	    try{
	        URL url = new URL(getWrapperParams().getSource());
	        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String fileLine;
	        while((fileLine = in.readLine()) != null){
	            if (fileLine.contains(",")){
	                String[] filePieces = fileLine.split(",");
	                //assuming the file is in this <lat, long, timestamp, theme, value> specified format
	                LatLong location = new LatLong(Double.parseDouble(filePieces[0]),Double.parseDouble(filePieces[1]));
	                Timestamp time = new Timestamp(Timestamp.valueOf(filePieces[2])); //this depends on how the time is formatted as a string, here I'm assuming JDBC timestamp escape format for simplicity
	                double value = Double.parseDouble(filePieces[4]);
	                WrapperParams params = new WrapperParams(getWrapperParams().getSource,filePieces[3]);
	                STTPoint point = new STTPoint(value,time,location,params);
	                pointList.add(point);
	            }else{
	                throw new IllegalArgumentException("The file is not in CSV format.");
	            }
	        }
	        in.close();
	    }catch(IllegalArgumentException e){
	        System.out.println("Cannot open this kind of file.");
	    }catch(Exception ex){
	        System.out.println("The file could not be opened. The URL may be bad or busy.");
	    }
	    return pointList;
	}

	/**
	 * @param wrapperParams
	 * @param authFields
	 * @param geoResolution
	 * @param geoBoundingBoxSouthWest
	 * @param geoBoundingBoxNorthEast
	 * @param source
	 * @param theme
	 */
	public CSVWrapper(WrapperParams wrapperParams, AuthFields authFields,
			GeoParams geoParams) {
		super(wrapperParams, authFields, geoParams);
		// TODO Auto-generated constructor stub
	}

}
