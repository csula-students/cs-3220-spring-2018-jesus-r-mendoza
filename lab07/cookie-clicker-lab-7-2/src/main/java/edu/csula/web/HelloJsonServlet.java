package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/hellojson")
public class HelloJsonServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		List<User> users = new ArrayList<>();
		User u = new User(0, "username", "password");
		User u2 = new User(1, "eric", "notmypassword");
		users.add(u);
		users.add(u2);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonString = gson.toJson(users);

		request.setAttribute("state", jsonString);

		request.getRequestDispatcher("WEB-INF/json.jsp")
			.forward(request, response);
	}
}
