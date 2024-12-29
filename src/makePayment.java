

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class makePayment
 */
public class makePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public makePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int price = Integer.parseInt(request.getParameter("tPrice"));
		int qty = Integer.parseInt(request.getParameter("tQty"));
		int total = price*qty;
		String payMethod = request.getParameter("payment");
		
		HttpSession session = request.getSession(true);

		if(payMethod.equals("cash")) {
			session.setAttribute("pay", "cash");
			session.setAttribute("qty", qty);
			session.setAttribute("bill", total);
			response.sendRedirect("bookMsg.jsp?");
		}
		
		else if (payMethod.equals("upi")) {
			session.setAttribute("bill", total);
			session.setAttribute("qty", qty);
			session.setAttribute("pay", "upi");
			response.sendRedirect("upiDetails.jsp");
			
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
