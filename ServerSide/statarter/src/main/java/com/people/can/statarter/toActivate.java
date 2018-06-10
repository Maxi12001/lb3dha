package com.people.can.statarter;

import entities.extandUser;
import java.util.HashMap;
public class toActivate {
	private static HashMap<String, extandUser> toAct=new HashMap<String,extandUser>();
		
	public static void addToAct (String code,extandUser u){
		toAct.put(code, u);
	}
	public static extandUser ReturnextandUser(String Code){
		extandUser x;
		if(toAct.containsKey(Code)){
			x=toAct.get(Code);
			toAct.remove(Code);
			return x;
		}
		return null; 
	}
}
