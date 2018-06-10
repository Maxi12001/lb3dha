package com.people.can.statarter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;

import DbInterface.*;
import entities.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
@Path("myresource/Signing")
public class Signing {
	
	@Path("/NewUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(final User n){
		try {
			opreatioCU myop=new opreatioCU();
			//System.out.println(n.blood+ n.name+ n.pass+ n.regMail+n.phoneno);
			
			int id= myop.insertUser(n.blood, n.name, n.pass, n.regMail,n.Bdate,n.City,n.state,n.phoneno);
			JSONObject js=new JSONObject();
			if (id!=0){
				Mail mail =new Mail();
				extandUser eU=new extandUser(n);
				eU.UID=id;
				String Code = randomCode.getSaltString();
				toActivate.addToAct(Code, eU);
				mail.setRecip(n.regMail,Code);
				mail.run();
				js.put("Massage", "Check your Emil and insert the code");
			}
			else{
				js.put("Massage","Badinput");
			}
			 return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return Response.status(Response.Status.NOT_FOUND).entity("error").build();
	}
	
	@Path("/signIn")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signin(signIn n){
		try {
			opreation myop=new opreation();
			ResultSet rs=myop.getUser(n.pass, n.email);
			
			if (rs!=null){
			JSONObject obj=new JSONObject();
			rs.next();
			opreatioCU myop1=new opreatioCU();
			String Xx=Sessions.setNewSession(rs.getString("id"));
			obj.put("SID",Xx);
			int y= myop1.insertToSession(Xx, String.valueOf(rs.getString("id")));
			rs.previous();
			JSONArray mJson=jsonConvertir.convertResultSet(rs);

			mJson.add(obj);

			String json=mJson.toJSONString();
			 return Response.ok(json, MediaType.APPLICATION_JSON).build();}
			else{
				return Response.ok("error:notFound",MediaType.APPLICATION_JSON).build();
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;}
	 	@POST
	    @Path("/Complate")
	    @Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
		@Produces(MediaType.APPLICATION_JSON)
	    public Response ComplateSignUp(String Code){
	    	
	    	String []p=Code.split(":");
	    	p[1]=p[1].replace("\"","").replace("}", "");
	    	extandUser ex=toActivate.ReturnextandUser(p[1]);
	    	JSONObject js=new JSONObject();
	    	try {
				opreatioCU myop=new opreatioCU();
		    	if (ex != null){
		    		String sid=Sessions.setNewSession(String.valueOf(ex.UID));
		    		int x=myop.ComplatteSignUp(ex.UID);
		    		if (x!=0){
		    		js.put("Massage", "Avtivate Complate");	
					js.put("name", ex.name);
					js.put("ID", ex.UID);
					js.put("SID", sid);
					int y= myop.insertToSession(sid, String.valueOf(ex.UID));
				
					return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();}
		    	}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			js.put("Massage", "Somthing went wrong");
			return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();
	    }
		@Path("/signInAdmin")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response signinAdmin(signIn n){
			try {
				opreation myop=new opreation();
				ResultSet rs=myop.getFound(n.pass, n.email);
				if (rs!=null){
				JSONObject obj=new JSONObject();
				rs.next();
				String Xx=Sessions.setNewSession(rs.getString("id"));
				obj.put("SID",Xx);
				rs.previous();
				JSONArray mJson=jsonConvertir.convertResultSet(rs);

				mJson.add(obj);

				String json=mJson.toJSONString();
				 return Response.ok(json, MediaType.APPLICATION_JSON).build();}
				else{
					return Response.ok("error:notFound",MediaType.APPLICATION_JSON).build();
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;}
		
		@Path("/CheckSession")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response checkSession(SessionInfo n){
			try {
				opreation myop=new opreation();
				String UID =Sessions.GetSession(n.Sid);
				System.out.println(UID+"I am here");
				JSONObject obj=new JSONObject();
				if (UID.equals("")){
					int x =myop.getSeeasion(n.Sid, n.Uid);
					if (x==0)
						obj.put("Active State", "0");
					else
						obj.put("Active State", "1");
				}
				else{
					obj.put("Active State", "1");
				}
				 return Response.ok(obj.toJSONString(), MediaType.APPLICATION_JSON).build();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;}

}
