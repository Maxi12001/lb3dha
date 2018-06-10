package entities;

public class extandUser extends User {
	public int UID;
	public extandUser(String Name, String Pass, String RMail, String Blood,
			String Bdate, String Pno, String State, String city,int uid) {
		super(Name, Pass, RMail, Blood, Bdate, Pno, State, city);
		this.UID=uid;
		
	}

	public extandUser(User u) {
		super(u);
		
	}

		
}
