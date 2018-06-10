package com.people.can.statarter;
import java.util.HashMap;
import java.util.UUID;
import DbInterface.IDGenrator;
public  class  Sessions {

private static  HashMap<String,String> sessionid=new HashMap<String,String> ();
public static String setNewSession(String uid){
	Integer x =IDGenrator.GenId();
	sessionid.put(x.toString(), uid);
	return x.toString();
}
public static String GetSession(String SID){
		if (sessionid.containsKey(SID))
			return sessionid.get(SID);
		return "";
}
public static int delSession(String SID){
	if (sessionid.containsKey(SID)){
		sessionid.remove(SID);
		return 1;
	}
	return 0;
		
}
}
