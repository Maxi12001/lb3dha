package DbInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
class adapter {

	static Connection getcon() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		String url="jdbc:mysql://localhost:3306/can?characterEncoding=UTF-8";
		Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		Connection conn = DriverManager.getConnection (url, "root","");
		return conn;
	
	}

	
}
