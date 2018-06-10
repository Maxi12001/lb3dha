package entities;

public class User {
 public String name,pass,regMail,blood,Bdate,phoneno,state,City;
 public User(){}
 public User (String Name, String Pass,String RMail,String Blood,String Bdate,String Pno,String State,String city){
	 this.name=Name;
	 this.pass=Pass;
	 this.regMail=RMail;
	 this.blood=Blood;
	 this.Bdate=Bdate;
	 this.phoneno=Pno;
	 this.state=State;
	 this.City=city;
 }
 public User (User u){
	 this(u.name,u.pass,u.regMail,u.blood,u.Bdate,u.phoneno,u.state,u.City);
 }

}
