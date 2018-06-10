package com.people.can.statarter;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DbInterface.opreation;
@Path("myresource/Feed")
public class Feed {
	  @GET
	   @Path("/newsfeed")
	   	public Response returnNewsFeed(@QueryParam("sid") String sid){
	    	try {
	    		String session=Sessions.GetSession(sid);
	    		
	    		if(session.equals("")){
	    			return Response.ok("error:Sign in",MediaType.APPLICATION_JSON).build();
	    		}
				opreation myop= new opreation();
				String json=jsonConvertir.convertResultSet(myop.GetNewsFeed()).toJSONString();
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
	   @GET
	   @Path("/GetDsc")
	   public Response ReturnDsc(@QueryParam("AID") String aid){
		   try {
	   			opreation myop= new opreation();
				String json=jsonConvertir.convertResultSet(myop.GetDsc(aid)).toJSONString();
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
	   
	  @GET
	  @Path("/GetLast24")
	  public Response Return24(){
		   try {
	  			opreation myop= new opreation();
				String json=jsonConvertir.convertResultSet(myop.GetLast24()).toJSONString();
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
	  @GET
	  @Path("/count")
	  public Response Return24count(){
		   try {
	 			opreation myop= new opreation();
				String json=jsonConvertir.convertResultSet(myop.Get24Count()).toJSONString();
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
