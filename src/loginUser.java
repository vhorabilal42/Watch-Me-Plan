import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

/**
 * Servlet implementation class loginUser
 */
public class loginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginUser() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String role = request.getParameter("usertype");
		String lEmail = request.getParameter("lemail");
		String lPassword = request.getParameter("lpassword");

		out.print(role + " " + lEmail );

		try {
			
			Connection conn = DBConnection.connect();
			Statement stmt = conn.createStatement(); ;
			ResultSet rs;

			
			HttpSession session = request.getSession(true);
			
			if (role.equals("User")) {
				
				String select = "SELECT * FROM user where Email ='"+lEmail+"'and Password = '"+lPassword+"'";
				rs = stmt.executeQuery(select);

				out.print(rs);
				
				if (rs.next()) {
					
					
					session.setAttribute("uname", lEmail);
					session.setAttribute("uuid", rs.getInt("UserID"));
					response.sendRedirect("indexUser.jsp");
				}

				else {
					
					session.setAttribute("invalidUser",true);
					response.sendRedirect("loginUser.jsp");
					
				}

			}

			if (role.equals("Admin")) {

				String select = "SELECT * FROM admin where Email ='"+lEmail+"' and Password = '"+lPassword+"'";
				rs = stmt.executeQuery(select);

				if (rs.next()) {
					
					session.setAttribute("uname", lEmail);
					response.sendRedirect("indexAdmin.jsp");
				}

				else {

					session.setAttribute("invalidUser",true);
					response.sendRedirect("loginUser.jsp");
				}

			}
			
			else {
				
				out.print("Kindly select role");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
