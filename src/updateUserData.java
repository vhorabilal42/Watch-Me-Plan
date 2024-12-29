
import java.io.*;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

/**
 * Servlet implementation class updateUserData
 */
public class updateUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateUserData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession(false);
		int uid = (int) session.getAttribute("uuid");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int contact = Integer.parseInt(request.getParameter("contact"));
		String dob = request.getParameter("dob");
		Date date = Date.valueOf(dob);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String favourites[] = request.getParameterValues("favourites");

		String fav = "";
		for (String string : favourites) {
			fav += string + " ";
		}

	
		

		out.print(uid + " " + fname + " " + lname + " " + contact + " " + dob + " " + email + " " + password + " "
				+ fav + " " + date);

		try {

			Connection conn = DBConnection.connect();
			PreparedStatement pstmt;

			String update = "UPDATE user set FirstName=?,LastName=?,Contact=?,DOB=?,Email=?,Password=?,Favourites=? where UserID=?";
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setInt(3, contact);
			pstmt.setDate(4, date);
			pstmt.setString(5, email);
			pstmt.setString(6, password);
			pstmt.setString(7, fav);
			pstmt.setInt(8, uid);
			
			pstmt.executeUpdate();
			response.sendRedirect("updateMsg.html");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
