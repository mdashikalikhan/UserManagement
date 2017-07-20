package org.jaxrs.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jaxrs.utility.UtilityMessages;

import com.google.gson.Gson;

@Path("/userService")
public class UserService {
	
	UserDao userDao = new UserDao();
	
	
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getAllUsers() {
		System.out.println("entring <getAllUsers> method...");
		return userDao.getAllIsers();
	}
	
	@GET
	@Path("/users/format")
	/*@Produces(MediaType.APPLICATION_JSON)*/
	
	public Response getAllUsersJSON(@QueryParam("type")String type) {
	
		System.out.println("entring <getAllUsersJSON> method...");
		if (type==null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("type parameter is mandatory").build());
		}
		else if(type.equalsIgnoreCase("JSON")) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(userDao.getAllIsers());
			return Response
					.status(200)
					.entity(jsonString).build();
		}
		else
			return Response
					.status(404)
					.entity("Incompaible Format type").build();
	}
	
	@GET
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUser(@PathParam("userid") int userid) {
		System.out.println("entring <getUser> method...");
		return userDao.getUser(userid);
	}
	
	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("profession") String profession) throws IOException {
		System.out.println("entring <createUser> method...");
		User user = new User(id, name, profession);
		int result = userDao.addUser(user);
		System.out.println(result);
		return getSuccessFailureInXML(result);
	}
	
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("profession") String profession)throws IOException {
		System.out.println("entring <updateUser> method...");
		User user = new User(id, name, profession);
		int result = userDao.updateUser(user);
		System.out.println(result);
		return getSuccessFailureInXML(result);
	}
	
	@DELETE
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteUser(@PathParam("userid") int userid)
	{
		System.out.println("entring <deleteUser> method...");
		int result = userDao.deleteUser(userid);
		System.out.println(result);
		return getSuccessFailureInXML(result);
	}
	
	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations()
	{
		System.out.println("entring <getSupportedOperations> method...");
		return UtilityMessages.SUPPORTED_OPERATIONS_IN_XML;
	}
	
	private String getSuccessFailureInXML(int result)
	{
		if(result==1)
			return UtilityMessages.SUCCESS_XML;
		else
			return UtilityMessages.FAILURE_XML;
	}

}
