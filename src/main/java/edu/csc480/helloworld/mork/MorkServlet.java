package edu.csc480.helloworld.mork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@Path("/")
public class MorkServlet {

	@GET
  public String doAThing() {
		Connection con = null;
		Statement query = null;
		ResultSet res = null;

		try{
			con = mysqlUtil.getConnection();
			query = con.createStatement();
			res = query.executeQuery("select * from test");

			while (res.next()) {
				System.out.println("id: " + res.getInt("id"));
				System.out.println("description: " +  res.getString("description"));
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
			// handle any errors
			if(ex instanceof SQLException){
				System.out.println("SQLException: " + ((SQLException)ex).getMessage());
				System.out.println("SQLState: " + ((SQLException)ex).getSQLState());
				System.out.println("VendorError: " + ((SQLException)ex).getErrorCode());
			}
		}

		mysqlUtil.cleanup(con, query, res);
    return "Look MA, IM DOIN A THING!";
	}
}
