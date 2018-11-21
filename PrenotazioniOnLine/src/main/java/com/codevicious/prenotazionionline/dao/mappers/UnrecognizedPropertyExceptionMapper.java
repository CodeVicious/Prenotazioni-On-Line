package com.codevicious.prenotazionionline.dao.mappers;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;




public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

	public Response toResponse(UnrecognizedPropertyException exception) {
		return Response
                .status(Response.Status.BAD_REQUEST)
                .entity( "'" + exception.getPropertyName() + "' is an unrecognized field.")
                .type( MediaType.TEXT_PLAIN)
                .build();
	}

}
