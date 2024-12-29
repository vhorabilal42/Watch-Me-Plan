
import java.io.*;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public registerUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
	
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int contact = Integer.parseInt(request.getParameter("contact"));
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String favourites[] =  request.getParameterValues("favourites");
		
		  
	    Date date=Date.valueOf(dob);//converting string into sql date  
		
	    String fav = "";
		
		for (String string : favourites) {
			fav += string + " ";
		}
		
		out.print(fname + " " + lname + " " + contact + " " + dob + " " + email + " " + password + " " + fav);
			
		try {
			Connection conn = DBConnection.connect();
			String insert = "INSERT INTO user(FirstName,LastName,Contact,DOB,Email,Password,Favourites) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setInt(3, contact);
			pstmt.setDate(4, date);
			pstmt.setString(5, email);
			pstmt.setString(6, password);
			pstmt.setString(7, fav);
			
			pstmt.executeUpdate();
			out.print("data added");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("username", email);
			
			//int t  = (int)session.getAttribute("uuid");
			
			response.sendRedirect("indexUser.jsp");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
