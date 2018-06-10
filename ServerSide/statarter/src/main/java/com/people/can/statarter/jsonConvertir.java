package com.people.can.statarter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.sql.ResultSet;
import java.sql.SQLException;
public class jsonConvertir {

	public static JSONArray convertResultSet(ResultSet rs) throws SQLException{
		JSONArray json = new JSONArray();
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
		  int numColumns = rsmd.getColumnCount();
		  JSONObject obj = new JSONObject();
		  for (int i=1; i<=numColumns; i++) {
		    String column_name = rsmd.getColumnName(i);
		    obj.put(column_name, rs.getObject(column_name));
		  }
		   json.add(obj);
		}
		return json;
	}
}
