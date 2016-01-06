package com.psl.service;

import java.sql.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloService { // resource class

	//@Path("/greet")
	@GET
	@Produces(value=MediaType.TEXT_PLAIN)
	public Date sayHello(){// resource method
		return Date.valueOf("2011-11-11");
	}
	
	@GET
	@Produces(value=MediaType.TEXT_HTML)
	public String anotherHello(){
		return "Today is Monday";
	}
}
