package DbInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
public class opreation {
private  Connection conn;
//private static PreparedStatement sql_statement;
//CallableStatement stmt;
private static ResultSet rs;
public opreation() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
	conn=adapter.getcon();
}
public void closeConnection(){
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public ResultSet GetUserConnection(int uid){
	try{
	
	String Sql1= "select CD.detail,C.typeName From allowedconnection as C join contactinfo as CD on CD.COID=C.COID where CD.UID="+uid;
	PreparedStatement sql_statement = conn.prepareStatement(Sql1);
	 rs=sql_statement.executeQuery();
	return rs;
	}
	catch (Exception ex){
		return null;
	}
}
public ResultSet GetUserApeal(int uid){	try{
	String Sql1= "SELECT user.name,user.ID,apeal.AID,apeal.Title,DATE_FORMAT(apeal.Date,\"%M %d %Y\")date,apeal.Title,apeal.Content,apeal.closed FROM apeal JOIN user on apeal.UID=USER.ID where user.ID =? ORDER by Date";
	PreparedStatement sql_statement = conn.prepareStatement(Sql1);
	sql_statement.setInt(1, uid);
	rs=sql_statement.executeQuery();
	return rs;
	}
	catch (Exception ex){
		return null;
	}}
public ResultSet GetTrustedBy(int uid){
	try{
	String Sql="select name,id from user join trust on id=byid where trustedid=?"; 
	PreparedStatement sql_statement = conn.prepareStatement(Sql);
	sql_statement.setInt(1, uid);
	rs=sql_statement.executeQuery();
	return rs;
	}
	catch (Exception ex){
		return null;
	}
}

public ResultSet GetApealByCat(int CatID){
	try{
	String Sql1= "SELECT user.name,user.ID,apeal.AID,apeal.Title,DATE_FORMAT(apeal.Date,\"%M %d %Y\")date,apeal.Title,apeal.Content,apeal.closed FROM apeal JOIN user on apeal.UID=USER.ID where apeal.CatID =? ORDER by Date";
	PreparedStatement sql_statement = conn.prepareStatement(Sql1);
	sql_statement.setInt(1, CatID);
	rs=sql_statement.executeQuery();
	return rs;
	}
	catch (Exception ex){
		return null;
	}
	
}
public ResultSet GetApealCat(){
	try{
	String Sql1= "SELECT apealcat.CID,apealcat.CatName,apealcat.dscript,(SELECT COUNT(*)FROM apeal WHERE apeal.CatID=apealcat.CID) as Count FROM apealcat";
	PreparedStatement sql_statement = conn.prepareStatement(Sql1);
	rs=sql_statement.executeQuery();
	return rs;
	}
	catch (Exception ex){
		return null;
	}
}
 public ResultSet GetNewsFeed(){
		try{
			String Sql1= "SELECT user.name,user.ID,apeal.AID,apeal.Title,DATE_FORMAT(apeal.Date,\"%M %d %Y\")date,apeal.Title,apeal.Content,apeal.closed FROM apeal JOIN user on apeal.UID=USER.ID ORDER by Date";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			rs=sql_statement.executeQuery();
			return rs;
			}
			catch (Exception ex){
				return null;
			}
	 
 }
 private int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    try {
	        resultSet.last();
	        return resultSet.getRow();
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        try {
	            resultSet.beforeFirst();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        }
	    }
	    return 0;
	}
 public ResultSet getUser(String pass,String email){
	 try{
		String Sql1= "Select name,id from user where regMail=? and pass=?";
		PreparedStatement sql_statement = conn.prepareStatement(Sql1);
		sql_statement.setString(1, email);
		sql_statement.setString(2, pass);
		
		rs=sql_statement.executeQuery();
		int z=getRowCount(rs);
		if (z==1)
		return rs;
		else 
			return null;
		}
		catch (Exception ex){
			return null;
		}}
 public ResultSet getFound(String pass,String email){
	 try{
		String Sql1= "select user.name, user.ID From user join verified on user.ID=verified.UID where user.regMail=? and user.pass=?";
		PreparedStatement sql_statement = conn.prepareStatement(Sql1);
		sql_statement.setString(1, email);
		sql_statement.setString(2, pass);
		
		rs=sql_statement.executeQuery();
		int z=getRowCount(rs);
		if (z==1)
		return rs;
		else 
			return null;
		}
		catch (Exception ex){
			return null;
		}}
 public ResultSet getRemaindList(String uid){
		
		try {
			String Sql1= "SELECT user.name,DATE_FORMAT(apeal.Date,\"%M %d %Y\")date,apeal.AID,apeal.Title,apeal.Content,apeal.closed from apeal JOIN user on apeal.UID=user.ID join reminder on apeal.AID=reminder.AID where reminder.UID=?";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			sql_statement.setString(1, uid);
			rs=sql_statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
 }
 public ResultSet getProfileInfo(String uid){
		
		try {
			String Sql1= "select user.name,user.bloodtype,(SELECT count(*) from trust WHERE trust.TrustedID=?)as trustedBy,(SELECT count(*) from trust where trust.ByID=?) as trustedin,(SELECT COUNT(*) from apeal where apeal.UID=?) as noOfapeal from user WHERE user.ID=?";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			sql_statement.setString(1, uid);
			sql_statement.setString(2, uid);
			sql_statement.setString(3, uid);
			sql_statement.setString(4, uid);
			rs=sql_statement.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
}
 public ResultSet GetDsc(String Aid){
		try{
			String Sql1= "SELECT Description,UID FROM `apeal` WHERE AID=?";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			sql_statement.setString(1, Aid);
			rs=sql_statement.executeQuery();
			return rs;
			}
			catch (Exception ex){
				return null;
			}
		
	 
}
 		public ResultSet GetLast24(){
 			try{
			String Sql1= "SELECT user.name,user.ID,apeal.AID,apeal.Title,DATE_FORMAT(apeal.Date,\"%M %d %Y\")date,apeal.Title,apeal.Content,apeal.closed FROM apeal JOIN user on apeal.UID=USER.ID where DATEDIFF(NOW(),apeal.Date)<=1 ORDER by Date";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			rs=sql_statement.executeQuery();
			return rs;
			}
			catch (Exception ex){
				return null;
			}}
 		
 		public ResultSet Get24Count(){

 			try{
			String Sql1= "SELECT Count(*) as c from apeal where DATEDIFF(NOW(),apeal.Date)<=1";
			PreparedStatement sql_statement = conn.prepareStatement(Sql1);
			rs=sql_statement.executeQuery();
			return rs;
			}
			catch (Exception ex){
				return null;
			}
 			
 		}
 		
 		public int getSeeasion(String SID,String UID){

 			try{
 				String callString="{?=call CheckSeasstion(?,?)}";
 				
 				CallableStatement stmt=conn.prepareCall(callString);
 				
 				System.out.println(stmt);
 				stmt.setString(2, SID);
 				System.out.println(stmt);
 				stmt.setString(3, UID);
 				System.out.println(stmt);
 				stmt.registerOutParameter(1, Types.INTEGER);
 				stmt.execute();
 				return stmt.getInt(1);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
 			return 0;
 			
 		}
}
