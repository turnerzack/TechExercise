

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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String value = request.getParameter("value");	
	complete(value, response);
	}
	
	void complete(String value, HttpServletResponse response) throws IOException
	{
		Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnectionTurner.getDBConnection();
	         connection = DBConnectionTurner.connection;
	         String selectSQL = "UPDATE ToDo SET COMPLETED = ? WHERE ID LIKE ?";
	         preparedStatement = connection.prepareStatement(selectSQL);
	         preparedStatement.setString(1, "Yes");
	         preparedStatement.setString(2,  value);
	         int rs = preparedStatement.executeUpdate();
	         preparedStatement.close();
	         response.setContentType("text/html");
	         PrintWriter out = response.getWriter();
	         out.println("<a href=/TechExercise/getToDo>See Current Tasks</a> <br>");
	         out.println("<a href=/TechExercise/getCompleted>See Completed Tasks</a> <br>");
	         out.println("<a href=/TechExercise/AddTask.html>Add Task</a> <br>");
	         out.println("<a href=/TechExercise/ToDo.html>Update Tasks</a> <br>");
	         out.println("</body></html>");
	      }catch (SQLException se) {
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
