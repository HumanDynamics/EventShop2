package backend;

import java.util.ArrayList;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.scidb.jdbc.IResultSetWrapper;

public class DBWrapper extends AbstractGeoWrapper {
    
    String dbName;
    /**
     * @param wrapperParams
     * @param authFields
     */
    
	public DBWrapper(WrapperParams wrapperParams, AuthFields authFields,
			GeoParams geoParams) {
		super(wrapperParams, authFields, geoParams);
		
	}

	@Override
	public ArrayList<STTPoint> getWrappedData() {
	    //SQL, not sciDB
	    try{
	        Class.forName("com.mysql.jdbc.Driver");
	    }
	    catch (ClassNotFoundException e){
	        System.out.println("Driver is not in the CLASSPATH -> " + e);
	    }
	    try{
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName);
	        ArrayList<STTPoint> pointList = new ArrayList<STTPoint>();
	        Statement st = conn.createStatement();
	        ResultSet res = st.executeQuery("select VALUE, PULLTIME, LAT, LON, SOURCE, THEME from "+dbName+" where THEME = "+this.wrapperParams.getTheme();
	        while (rs.next()){
	            String value = rs.getString("VALUE");
	            Timestamp pullTime = Timestamp.valueOf(rs.getString("PULLTIME"));
	            double lat = rs.getFloat("LAT").doubleValue();
	            double lon = rs.getFloat("LON").doubleValue();
	            String source = rs.getString("SOURCE");
	            String theme = rs.getString("THEME");
	            STTPoint point = new STTPoint(value,pullTime,new LatLong(lat,lon),new WrapperParams(source,theme));
	            pointList.add(point);
	        }
	    }catch (SQLException e){
	        System.out.println(e);
	    }finally{
	        st.close();
	    }
	    return pointList;
	}
	
}
