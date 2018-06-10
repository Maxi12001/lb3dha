package com.people.can.statarter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
@Path("myresource/Reminding")
public class Reminding {
	@POST
    @Path("/AddtoRemind")
    @Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON)
    public Response AddToRemind(Reminder n){
    	JSONObject js=new JSONObject();
    	try {
			opreatioCU myop=new opreatioCU();
			//System.out.println(n.blood.toString()+ n.name+ n.pass+ n.regMail);
			int returned =myop.insertToReminder(n.aid,n.uid);
			System.out.println(n.aid+""+n.uid);
			if(returned>=0){
				js.put("Massage", "The Apeal Saved");
				 return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();
			}
			
			else{
				 return Response.status(Response.Status.NOT_FOUND).entity("error").build();
			}
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
			if (e instanceof MySQLIntegrityConstraintViolationException) {
				js.put("Massage", "Sorry you are not allowed");
				return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		js.put("Massage", "Somthing went wrong");
		return Response.ok(js.toJSONString(),MediaType.APPLICATION_JSON).build();
    }
	  @GET
	   @Path("/Mylist")
	   public Response ReturnMyList(@QueryParam("sid") String sid){
		   try {
	   		String session=Sessions.GetSession(sid);
	   		
	   		if(session.equals("")){
	   			return Response.ok("error:Sign in",MediaType.APPLICATION_JSON).build();
	   		}
				opreation myop= new opreation();
				String json=jsonConvertir.convertResultSet(myop.getRemaindList(Sessions.GetSession(sid))).toJSONString();
				 return Response.ok(json, MediaType.APPLICATION_JSON).build();
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
	   	return Response.status(Response.Status.NOT_FOUND).entity("Empty Category").build();
	   }

}
