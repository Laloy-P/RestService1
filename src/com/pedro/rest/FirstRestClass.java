package com.pedro.rest;

import java.text.DecimalFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/FirstRestClass")
public class FirstRestClass {
	@GET
	@Produces("application/xml")
	public String bar2Psi () {
		Double psi, bar = 20d;
		psi = bar*14.5038;
		String result = "@Produces(\"application/xml\") Output: \n\n bar to psi Converter Output: \n\n" + psi;
		return "<service>" + "<bar>" + bar + "</bar>" + "<serviceoutput>" + result + "</serviceoutput>" + "</service>";
	}
	
	@Path("{b}")
	@GET
	@Produces("application/xml")
	public String convertBartoPsiFromInput(@PathParam("b") Double b) {
		Double psi;
		Double bar = b;
		psi = bar*14.5038;
		DecimalFormat nf = new DecimalFormat("0.##");
		String s = nf.format(psi);
		String result = "@Produces(\"application/xml\") Output: \n\n bar to psi Converter Output: \n\n" + s;
		return "<service>" + "<bar>" + bar + "</bar>" + "<serviceoutput>" + result + "</serviceoutput>" + "</service>";
	}
}
