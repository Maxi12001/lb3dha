package com.people.can.statarter;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DbInterface.opreation;
@Path("myresource/CategoryFeed")
public class CtegoryFeed {
    ////get request
    @Path("/GetCategory")
    @GET
    public Response returnCat(){
    	try {
			opreation myop= new opreation();
			String json=jsonConvertir.convertResultSet(myop.GetApealCat()).toJSONString();
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
    @Path("/GetPostByCategory")
    @GET
    public Response returnpostCat(@QueryParam("CatID") int catid){
    	try {
			opreation myop= new opreation();
			String json=jsonConvertir.convertResultSet(myop.GetApealByCat(catid)).toJSONString();
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
