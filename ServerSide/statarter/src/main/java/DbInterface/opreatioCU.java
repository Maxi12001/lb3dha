package DbInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class opreatioCU {
	private  Connection conn;
	private static PreparedStatement sql_statement;
	
	public opreatioCU() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		conn=adapter.getcon();
	}

	public int insertUser(String btype,String name,String pass,String regmail,String Bdate,String City,String State,String Phone){
		Integer ID=IDGenrator.GenId();
		String sql="INSERT INTO `user`(`ID`, `name`, `pass`, `regDate`, `bloodtype`, `regMail`, `BirthDate`, `State`, `City`, `Phone No`,`Active`) VALUES (?,?,?,now(),?,?,?,?,?,?,0) ";
		try {
			sql_statement=conn.prepareStatement(sql);
			sql_statement.setString(4, btype);
			sql_statement.setInt(1,ID);
			sql_statement.setString(2, name);
			sql_statement.setString(3, pass);
			sql_statement.setString(5, regmail);
			sql_statement.setString(6,Bdate);
			sql_statement.setString(7,State);
			sql_statement.setString(8,City);
			sql_statement.setString(9,Phone);
			//System.out.println(sql_statement.toString());
			if(sql_statement.executeUpdate()==1)
			return ID;
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//	System.out.println(sql_statement.toString());
		}
		
		return 0;
	}
	public int[] insertApeal(int uid,String Content,String title,String Description,int CatID){
		Integer ID=IDGenrator.GenId();
		int [] returnV= new int [2];
		System.out.println(Content);
		String sql ="INSERT INTO `apeal`(`AID`, `UID`, `Title`, `Content`, `Description`, `Date`,`CatID`) VALUES (?,?,?,?,?,NOW(),?)";
		try{
		sql_statement=conn.prepareStatement(sql);
		sql_statement.setInt(1,ID);
		sql_statement.setInt(2,uid);
		sql_statement.setString(3, title);
		sql_statement.setString(4, Content);
		sql_statement.setString(5, Description);
		sql_statement.setInt(6,CatID);
		returnV[0]=sql_statement.executeUpdate();
		returnV[1]=ID;
		return returnV;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(sql_statement.toString());
		
	}
		return new int[]{0,0};
	
	
	}
	public int insertApealCat(int Aid,int Cid) throws SQLException{
		String sql="insert into apealcategory (AID,CATID) VALUES (?,?)";
		
			sql_statement=conn.prepareStatement(sql);
			sql_statement.setInt(1, Aid);
			sql_statement.setInt(2, Cid);
			return sql_statement.executeUpdate();

		
	}
    public int insertContactInfo(int uid,int coid,String detail){
		String sql="INSERT INTO contactinfo(UID, COID, detail) VALUES (?,?,?)";
		try {
			sql_statement=conn.prepareStatement(sql);
			sql_statement.setInt(1, uid);
			sql_statement.setInt(2, coid);
			sql_statement.setString(3, detail);
			return sql_statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    	
    }
	public int insertToReminder(int aid,int uid) throws SQLException{
		String sql="INSERT INTO `reminder`(`UID`, `AID`) VALUES (?,?)";
		
			sql_statement=conn.prepareStatement(sql);
			sql_statement.setInt(1, uid);
			sql_statement.setInt(2, aid);
			System.out.println(sql_statement.toString());
			return sql_statement.executeUpdate();

		
	}
   // public int isertBankingInfo(int uid,int bid,String detail){}
	public int ComplatteSignUp(int Uid){
		String sql = "UPDATE `user` SET `Active` = '1' WHERE `user`.`ID` = ?;";
		try {
			sql_statement =conn.prepareStatement(sql);
			sql_statement.setInt(1,Uid);
			return sql_statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	public int insertToSession(String Sid,String Uid) throws SQLException{
		String sql="INSERT INTO `seassion`(`SID`, `UID`) VALUES (?,?)";
		
			sql_statement=conn.prepareStatement(sql);
			sql_statement.setString(1, Sid);
			sql_statement.setString(2, Uid);
			System.out.println(sql_statement.toString());
			return sql_statement.executeUpdate();

		
	}
}
