package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.GuestbookDAO;
import edu.csula.storage.mysql.*;
import edu.csula.models.*;

import java.util.List;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GuestbookDAO dao = new GuestbookDAOImpl(getServetContext());
		GuestbookDAO dao = new GuestbookDAOImpl(new Database());
		List<Guestbook> entries = dao.getAll();
		request.setAttribute("guestbookEntries", entries);
		request.getRequestDispatcher("WEB-INF/guestbook.jsp")
			.forward(request, response);
	}

	public void doPost ( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GuestbookDAO dao = new GuestbookDAOImpl(getServetContext());
		GuestbookDAO dao = new GuestbookDAOImpl(new Database());

		String username = request.getParameter("username");
		String comment = request.getParameter("comment");
		Guestbook entry = new Guestbook();
		entry.username = username;
		entry.comment = comment;
		dao.add(entry);

		response.sendRedirect("guestbook");
	}
}
