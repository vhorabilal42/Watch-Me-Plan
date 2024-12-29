
import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

public class confirmEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public confirmEvent() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String eid = request.getParameter("eid");
		String uid = request.getParameter("uid");
		String bool = request.getParameter("bool");

		int intEID =  Integer.parseInt(eid);
		
		try {

			if (bool.equalsIgnoreCase("yes")) {

				Connection conn = DBConnection.connect();
				PreparedStatement pstmt;

				String update = "UPDATE events set isApproved = ? where EventID=?";
				pstmt = conn.prepareStatement(update);
				pstmt.setString(1, "Yes");
				pstmt.setInt(2, intEID);
				pstmt.executeUpdate();
				HttpSession session = request.getSession(true);
				session.setAttribute("esession", intEID);
				response.sendRedirect("approveEvents.jsp");

			}

			else if (bool.equalsIgnoreCase("no"))

			{

				Connection conn = DBConnection.connect();
				PreparedStatement pstmt;

				String update = "DELETE from events where EventID=?";
				pstmt = conn.prepareStatement(update);
				pstmt.setInt(1, intEID);
				pstmt.executeUpdate();
				response.sendRedirect("approveEvents.jsp");

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

//		File file = new File("'"+eid+"'.jsp");
//		
//		if (!file.exists()) {
//		    file.createNewFile();
//		}
//		
//		FileOutputStream fos = new FileOutputStream(file);
//		response.sendRedirect("'"+eid+"'.jsp");
	}

}
