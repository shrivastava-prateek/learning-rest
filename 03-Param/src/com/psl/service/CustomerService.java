package com.psl.service;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;

import com.psl.bean.AtomLink;
import com.psl.bean.Customer;
import com.psl.bean.Customers;
import com.psl.dao.CustomerDAO;

@Path("/")
public class CustomerService {
	@Context
	private UriInfo uriInfo;
	@Context
	private HttpHeaders headers;
	/*@GET
	@Path("/fetch")
	@Produces(value=MediaType.TEXT_PLAIN)
	public String getCustomerByIdQueryParam(@QueryParam("id")int customerId,
			@Context HttpHeaders Headers){
		
		Customer customer = new CustomerDAO().getCustomerById(customerId);
		return customer.toString();
	}*/
	@GET
	@Path("/fetch/{id}")
	@Produces(value=MediaType.TEXT_PLAIN)
	public String getcustomerByIdPathParam(@PathParam("id") int customerId) {
		
		Customer customer = new CustomerDAO().getCustomerById(customerId);
		return customer.toString();
	}

	/*@POST
	@Path("/update/{id}")
	public void updateCustomerFormParam(@PathParam("id") int customerId,
			@DefaultValue("--no-fn--") @FormParam("fn") String firstName,
			@DefaultValue("--no-ln--") @FormParam("ln")String lastName){
		
		Customer customer= new Customer(customerId,firstName,lastName);
		new CustomerDAO().updateCustomer(customer);
	}*/
	
	@PUT
	@Path("/update/{id}")
	public Response updateCustomerFormParam(@PathParam("id") int customerId,
			@DefaultValue("--no-fn--") @FormParam("fn") String firstName,
			@DefaultValue("--no-ln--") @FormParam("ln")String lastName){
		
		Customer customer= new Customer(customerId,firstName,lastName);
		new CustomerDAO().updateCustomer(customer);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deleteCustomer(@PathParam("id") int customerId){
		new CustomerDAO().delete(new Customer(customerId,"",""));
		return Response.status(Status.ACCEPTED).header("Deleted", "Customer Deleted").build();
	}
	
	@GET
	@Path("/remove/{id: .+}")
	public void removeCustomers(@PathParam("id") List<PathSegment> segments){
		
		for (PathSegment pathSegment : segments) {
			System.out.println(pathSegment.getPath());
			System.out.println(pathSegment.getMatrixParameters().get("mark"));
			System.out.println(pathSegment.getMatrixParameters().get("delete"));
		}
	}
	
	/*@GET
	@Path("/fetch/{id}")
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Customer getCustomerById(@PathParam("id") int customerId){
		Customer customer= new CustomerDAO().getCustomerById(customerId);
		return customer;
	}
	*/
	@GET
	@Path("/fetch/{id}")
	@Produces(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getCustomerById(@PathParam("id") int customerId){
		Customer customer= new CustomerDAO().getCustomerById(customerId);
		return Response.ok(customer).build();
	}
	
	
	/*@POST
	@Path("/new")
	@Consumes(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void createCustomer(Customer customer){
		new CustomerDAO().save(customer);
	}*/
	
	@POST
	@Path("/new")
	@Consumes(value={MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response createCustomer(Customer customer){
		new CustomerDAO().save(customer);
		return Response.status(Status.CREATED).header("location", uriInfo.getAbsolutePath()).build();
	}
	
	@GET
	@Path("/simple")
	public Response simpleService(@Context Request request){
		List<Variant> variants;
		variants = Variant.languages(new Locale(Locale.ENGLISH.getLanguage(),Locale.US.getCountry()),
				new Locale(Locale.FRENCH.getLanguage(),Locale.FRANCE.getCountry()),
				new Locale(Locale.ENGLISH.getLanguage(),Locale.UK.getCountry()))
				.mediaTypes(MediaType.APPLICATION_JSON_TYPE,MediaType.APPLICATION_XML_TYPE)
				.add()
				.build();
		
		Variant selectedVariant = request.selectVariant(variants);
		System.out.println(selectedVariant.getLanguage());
		System.out.println(selectedVariant.getMediaType());
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/fetch")
	@Produces(value=MediaType.APPLICATION_XML)
	public Response getCustomers(@DefaultValue("0") @QueryParam("start") int start,
			         @DefaultValue("5")@QueryParam("size") int size){
		
		Customers customers= new CustomerDAO().getCustomers(start,size);
		UriBuilder base= uriInfo.getBaseUriBuilder();
		base.path(CustomerService.class,"getCustomers");
		base.queryParam("start", start+size);
		base.queryParam("size", size);
		
		customers.setNext(new AtomLink(base.build().toString(),
				            "next",MediaType.APPLICATION_XML));
		return Response.ok(customers).build();
	}
}
