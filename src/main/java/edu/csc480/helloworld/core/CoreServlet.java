package edu.csc480.helloworld.webpage;

import javax.ws.rs.*;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class CoreServlet {
	
	@QueryParam("query") String q;
	@GET
	public String queryDatabase() {
		String results = "";
		String connectionUrl = "jdbc:mysql://localhost:3306/database_name";

		try (Connection con = DriverManager.getConnection(connectionUrl, "username", "password");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(q)) {
			 
			List<String> where = new ArrayList<String>();
		
			while (rs.next()) {
				results += rs.getString(1) + "<br>";
			}
			return results;
		
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(CoreServlet.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
			return "failed";
	}
}
