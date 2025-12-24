package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/Register")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL="jdbc:mysql://localhost:3306/userdb";
    public static final String Password="piyush";
    public static final String username="root";
    Connection connection;
       
   
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		try {
			connection= DriverManager.getConnection(URL,username,Password);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Login servlet is working (GET)</h2>");
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String uname=request.getParameter("uname");
		String paswrd=request.getParameter("paswrd");
		try {
			PreparedStatement pw= connection.prepareStatement("insert into userinfo values(?,?,?,?)");
			pw.setString(1, fname);
			pw.setString(2, lname);
			pw.setString(3, uname);
			pw.setString(4, paswrd);
	        pw.executeUpdate();
			PrintWriter ps=response.getWriter();
			ps.println("<html><body bgcolor=red text=white><center>");
			ps.println("<h3>User Registered successfully</h3>");
			ps.println("<a href=login.html>login</a>");
			ps.println("</center></body></html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
