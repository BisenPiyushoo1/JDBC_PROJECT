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


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	public static final String URL="jdbc:mysql://localhost:3306/userdb";
    public static final String Password="piyush";
    public static final String username="root";
    Connection connection;
    
    
    public login() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		try {
			connection= DriverManager.getConnection(URL,username,Password);
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter ps=response.getWriter();
		
		String uname=request.getParameter("uname");
		String paswrd=request.getParameter("paswrd");
		
		try {
			PreparedStatement pw= connection.prepareStatement("select * from uinfo where uname=? and paswrd=?");
			pw.setString(1, uname);
			pw.setString(2, paswrd);
			
			ResultSet rs =pw.executeQuery();
			
			ps.println("<html><body bgcolor='red' text='white'><center>");
			
			if(rs.next()) {
				ps.println("<h1> welcome "+uname+ "</h1>");
				
			}
			else {
				ps.println("<h1>invalid user</h1>");
			}
			ps.println("</center></body></html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ps.println("Something went wrong");
		}
		
		
	}

}
