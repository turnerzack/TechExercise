

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String startDate = request.getParameter("startDate");
		String toDo = request.getParameter("toDo");
		String dueDate = request.getParameter("dueDate");
		String address = request.getParameter("address");
		String person = request.getParameter("person");
		Connection connection = null;
		String insertSql = "INSERT INTO ToDo (id, TODO, STARTDATE, ADDRESS, DUEDATE, COMPLETED, PERSON) values (default, ?, ?, ?, ?, ?, ?)";
	
		try {
	         DBConnectionTurner.getDBConnection();
	         connection = DBConnectionTurner.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, startDate);
	         preparedStmt.setString(2, toDo);
	         preparedStmt.setString(3, dueDate);
	         preparedStmt.setString(4, address);
	         preparedStmt.setString(5, "No");
	         preparedStmt.setString(6, person);
	         preparedStmt.execute();
	      } 
		catch (Exception e) 
		{
	         e.printStackTrace();
	    }
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    String title = "Insert Data to DB table";
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	    out.println(docType + //
	          "<html>\n" + //
	          "<head><title>" + title + "</title></head>\n" + //
	          "<body bgcolor=\"#f0f0f0\">\n" + //
	          "<h2 align=\"center\">" + title + "</h2>\n" + //
	          "<ul>\n" + //
            "  <li><b>Start Date</b>: " + startDate + "\n" + //
            "  <li><b>ToDo</b>: " + toDo + "\n" + //
            "  <li><b>Due Date</b>: " + dueDate + "\n" + //
            "  <li><b>Address</b>: " + address + "\n" + //
            "  <li><b>Person</b>: " + person + "\n" + //
            "</ul>\n");
      out.println("<a href=/TechExercise/getToDo>See current tasks</a> <br>");
      out.println("</body></html>");
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
