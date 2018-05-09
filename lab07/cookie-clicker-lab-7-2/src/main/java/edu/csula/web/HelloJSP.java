package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hellojsp")
public class HelloJSP extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("test", "Hello");
		Collection<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/sum.jsp")
			.forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a = parseIntSafe(request.getParameter("a"));
		int b = parseIntSafe(request.getParameter("b"));
		int sum = a + b;

		request.setAttribute("a", a);
		request.setAttribute("b", b);
		request.setAttribute("sum", sum);

		request.getRequestDispatcher("WEB-INF/hello.jsp")
			.forward(request, response);
	}

	private int parseIntSafe(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return 0;
		}
	}
}
