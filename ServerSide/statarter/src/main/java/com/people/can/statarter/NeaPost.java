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
@Path("myresource/Posting")
public class NeaPost {
	@POST
	@Path("/newِApeal")
	@Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewApeal(final apeal n){
		JSONObject js=new JSONObject();
		try {
			opreatioCU myop=new opreatioCU();
			//System.out.println(n.blood.toString()+ n.name+ n.pass+ n.regMail);
			int returned []=myop.insertApeal(n.uid,n.content,n.title,n.Decription,n.catID);
				if(returned[0]>0){
				js.put("Massage", "The Apeal published");
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

}
