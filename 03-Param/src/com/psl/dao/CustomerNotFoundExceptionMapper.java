package com.psl.dao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class CustomerNotFoundExceptionMapper implements
		ExceptionMapper<CustomerNotFoundException> {

	@Override
	public Response toResponse(CustomerNotFoundException arg0) {
		return Response.status(Status.BAD_REQUEST).build();
	}

}
