package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/hellojdbc")
public class HelloJDBCServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// load driver
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		} catch( ClassNotFoundException e ) {
			throw new ServletException( e );
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost/cs3220_lab";
			String username = "root";
			String password = "";

			// CS3 server example
			// String url = "jdbc:mysql://localhost/cs3220xstu25";
			// String username = "cs3220xstu25";
			// String password = "password";

			c = DriverManager.getConnection( url, username, password );
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM employees" );

			while( rs.next() ) {
				out.println( rs.getString( 1 ) );
				out.println( rs.getString( 2 ) );
				out.println( rs.getString( 3 ) );
				out.println( rs.getFloat( 4 ) );
				out.println( "<br>" );
			}

			c.close();
		} catch( SQLException e ) {
			throw new ServletException( e );
		} finally {
			try
			{
				if( c != null ) c.close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}
		}

		out.println(
			String.format("<h1>%s!</h1>", "Hello World")
		);
	}
}
