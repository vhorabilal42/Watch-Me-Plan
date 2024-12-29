import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

public class createEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public createEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String ename = request.getParameter("ename");
		String evenue = request.getParameter("evenue");
		String ecategories = request.getParameter("ecategories");
		String etype= request.getParameter("etype");
		String estarttime = request.getParameter("estarttime");
		String eendtime = request.getParameter("eendtime");
		int eprice = Integer.parseInt(request.getParameter("eprice"));
		String edetails = request.getParameter("edetails");
		String tmp = "No";
		
		// out.print(ename + " " + evenue + " " + ecategories + " " + etype + " " +
		// estarttime + " " + eendtime + " " + edetails + " " + username);

		try {

			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("uname");

			Connection conn = DBConnection.connect();
			PreparedStatement pstmt;
			
			String select = "Select UserID from user where Email = ?";
			pstmt = conn.prepareStatement(select);
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int uid = Integer.parseInt(rs.getString("UserID"));
			out.print(uid);

			String insert = "INSERT INTO events (EventName,UserID,Venue,Category,Type,StartTime,EndTime,Image,Price,Details,Documents, isApproved) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, ename);
			pstmt.setInt(2, uid);
			pstmt.setString(3, evenue);
			pstmt.setString(4, ecategories);
			pstmt.setString(5, etype);
			pstmt.setString(6, estarttime);
			pstmt.setString(7, eendtime);
			pstmt.setString(8, tmp);
			pstmt.setInt(9, eprice);
			pstmt.setString(10, edetails);
			pstmt.setString(11, tmp);
			pstmt.setString(12, tmp);
			
			int x =  pstmt.executeUpdate();
			
			if(x > 0) {
				
				response.sendRedirect("eventSubmit.html");
			}
			else // what if event is not inserted??
				
				out.print("error");
			
			// enter in db then show it to admin after admin approves redirect to servlet get event ID & create event file &
			// show in book tickets



			// response.sendRedirect("index.html");
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
