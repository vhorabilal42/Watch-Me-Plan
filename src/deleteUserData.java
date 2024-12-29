

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBConnection;

/**
 * Servlet implementation class deleteUserData
 */
public class deleteUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUserData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false);
		int uid = (int) session.getAttribute("uuid");

		int id = Integer.parseInt(request.getParameter("id"));
		String f = request.getParameter("f");
		try {

			Connection conn = DBConnection.connect();
			PreparedStatement pstmt;

			String delete = "DELETE FROM user where UserID=?";
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			if(f.equals("jsp"))
				response.sendRedirect("listofusers.jsp");
			else
				response.sendRedirect("indexUser.jsp");

		} catch (Exception e) {
			e.printStackTrace();
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
