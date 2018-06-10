package com.people.can.statarter;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import DbInterface.opreation;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource/profile")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!22";
    }

    @Path("/GetUserApeal")
    @GET
    public Response reutrnUserPost(@QueryParam("uid") int uid){
    	{
        	try {
    			opreation myop= new opreation();
    			String json=jsonConvertir.convertResultSet(myop.GetUserApeal(uid)).toJSONString();
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
    @Path("/GetTrustedBy")
    @GET
    public Response returnTrusted(@QueryParam("uid")int uid){
    	try {
			opreation myop= new opreation();
			String json=jsonConvertir.convertResultSet(myop.GetTrustedBy(uid)).toJSONString();
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
    @Path("/info")
    @GET
    public Response returnInfo(@QueryParam("uid")int uid) {
    	try {
			opreation myop= new opreation();
			String json=jsonConvertir.convertResultSet(myop.GetUserConnection(uid)).toJSONString();
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
   @Path("/profileInfo")
   public Response ReturnProfileInfo(@QueryParam("uid") String uid){
	   try {
   			opreation myop= new opreation();
			String json=jsonConvertir.convertResultSet(myop.getProfileInfo(uid)).toJSONString();
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
