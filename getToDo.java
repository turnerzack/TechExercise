

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getToDo
 */
@WebServlet("/getToDo")
public class getToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getToDo() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Database Result";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");

	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnectionTurner.getDBConnection();
	         connection = DBConnectionTurner.connection;
	         String selectSQL = "SELECT * FROM ToDo WHERE COMPLETED LIKE ?";
	         String completed = "No";	         
	            preparedStatement = connection.prepareStatement(selectSQL);
	            preparedStatement.setString(1, completed);
	         ResultSet rs = preparedStatement.executeQuery();

	         while (rs.next()) {
	            out.println("ID: " + rs.getInt("id") + ", ");
	            out.println("ToDo: "+ rs.getString("todo").trim() + ", ");
	            out.println("Start Date: " + rs.getString("startdate").trim() + ", ");
	            out.println("Address: " + rs.getString("address").trim() + "<br>");
	            out.println("Due Date: " + rs.getString("duedate").trim() + "<br>");
	            out.println("Person: " + rs.getString("person").trim() + "<br>");
	            }
	         out.println("<a href=/TechExercise/getCompleted>See Completed Tasks</a> <br>");
	         out.println("<a href=/TechExercise/AddTask.html>Add Task</a> <br>");
	         out.println("<a href=/TechExercise/ToDo.html>Update Tasks</a> <br>");
	         out.println("</body></html>");
	         rs.close();
	         preparedStatement.close();
	      } catch (SQLException se) {
	         se.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
