package com.pedro.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
		String result = "20 bar = " + psi + " psi.";
		return "<service>" + "<serviceoutput>" + result + "</serviceoutput>" + "</service>";
	}
	
	@Path("{b}")
	@GET
	@Produces("application/xml")
	public String convertBartoPsiFromInput(@PathParam("b") Double b) {
		StartConnection();
		Double psi;
		Double bar = b;
		psi = bar*14.5038;
		DecimalFormat nf = new DecimalFormat("0.##");
		String s = nf.format(psi);
		String result = b+" Bar = " + s + " psi.";
		return "<service>" + "<serviceoutput>" + result + "</serviceoutput>" + "</service>";
	}
	
	public void StartConnection() {
		System.out.println("-------- PostgreSQL JDBC Connection Starting ------------");
		String adress = "postgres://ljzaelfiumsxzz:4598492aa5c3c92ecc4a76b2663bcd20ba7b48ce58381da648b82a840d90a4a6@ec2-54-204-46-236.compute-1.amazonaws.com:5432/dc76ph4rg6gg41";
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("No PostgreSQL JDBC Driver found.");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = getConnection();
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		} catch (URISyntaxException e) {
			System.err.println("probleme d'URI");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    return DriverManager.getConnection(dbUrl, username, password);
	}

	}

