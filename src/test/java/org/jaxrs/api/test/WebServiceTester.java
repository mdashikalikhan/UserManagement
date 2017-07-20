package org.jaxrs.api.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class WebServiceTester {

	private Client client;
	private String REST_SERVICE_URL="http://localhost:7001/UserManagement/rest/userService/users";
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	public static void main(String[] args) {
		
		WebServiceTester tester = new WebServiceTester();
		
		tester.init();
		tester.testAddUser();

	}
	
	private void init()
	{
		client = ClientBuilder.newClient();		
	}
	
	private void testAddUser() {
		Form form = new Form();
		form.param("id", "10");
		form.param("name", "Rafiq");
		form.param("profession", "TELLER");
		
		String callRequest = client.target(REST_SERVICE_URL)
									.request(MediaType.APPLICATION_XML)
									.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		String result = PASS;
		
		if(!SUCCESS_RESULT.equals(callRequest))
		{
			result = FAIL;
		}
		
		System.out.println("Test case name: testAddUser, Result: " + result);
		System.out.println(callRequest);
		
	}
	
	

}
