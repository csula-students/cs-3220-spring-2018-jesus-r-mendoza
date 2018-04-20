package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
		out.println("<h1>Hello events servlet!</h1>");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Incremental Game</title>");
		out.println("<h1>Incremental Game FrameWork</h1>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<div class=\"form\">");
		out.println("<form method='POST'>");
		out.println("<div>");
		out.println("<label>Event Name</label>");
		out.println("<input name='name' type=\"text\">");
		out.println("</div>");
		out.println("<div>");
    	out.println("<label>Event Description</label>");
		out.println("<textarea name='desc'></textarea>");
	    out.println("</div>");
    	out.println("<div>");
	    out.println("<label>Trigger At</label>");
		out.println("<input name ='triggerAt' type=\"text\">");
		out.println("</div>");
		out.println("<input type=\"submit\" value=\"Add\">");
		out.println("</form>");
		out.println("</div>");
		out.println("<div class=\"table\">");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Rate</th>");
		out.println("<th>Cost</th>");
		out.println("<th>Unlock At</th>");
		out.println("</tr>");

        for(Event entry: events){
            out.println("<tr>");
            out.println("<td>" + entry.getName() + "</td><td>"
                    + entry.getDescription() + "</td><td>"
                    + entry.getTriggerAt() + "</td><td>" +
                    "<a href=''> edit  |</a>" +
                    "<a href=''>  delete</a>" +
                    "</td>");
            out.println("</tr>");
        }

		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
        EventsDAO entry = new EventsDAOImpl(getServletContext());
        List<Event> entries = entry.getAll();
        String name = request.getParameter("name");
        String description = request.getParameter("desc");
        try {
            int triggerAt = Integer.parseInt(request.getParameter("triggerAt"));
            Event event = new Event(entries.size(), name, description, triggerAt);
            entry.add(event);
            response.sendRedirect("events");
        } catch (NumberFormatException e) {
            response.getWriter().println("<script> alert(\"ERROR: Make Sure You Input a number\"); </script>");
            response.sendRedirect("events");
        } catch (IOException e) {
            response.getWriter().println("<script> alert(\"ERROR: Make Sure You Input correct values\"); </script>");
            response.sendRedirect("events");
        } catch (Exception e) {
            response.getWriter().println("<script> alert(\"ERROR: Some error occured. Try again.\"); </script>");
            response.sendRedirect("events");
        }
    }

}
