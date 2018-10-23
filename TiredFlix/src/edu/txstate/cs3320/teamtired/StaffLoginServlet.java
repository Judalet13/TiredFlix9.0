package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.cs3320.teamtired.user.Staff;
import edu.txstate.cs3320.teamtired.data.db.StaffDAO;
import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.CannotPerformOperationException;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.InvalidHashException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/StaffLoginServlet")
public class StaffLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(StaffLoginServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailAddress = request.getParameter("email-address");
		String password = request.getParameter("password");
		
		Staff staff = StaffDAO.findStaff(emailAddress);
		if (staff == null) {
			request.setAttribute("message", "Invalid Username");
			request.getRequestDispatcher("StaffLogin.jsp").forward(request, response);	
			
		} 
		try {
			if (PasswordStorage.verifyPassword(password, staff.getPasswordHash())) {
				request.setAttribute("message", "");	
				HttpSession session = request.getSession();
				LOGGER.info("User " + staff.getId() + " logged in");				
				
				session.setAttribute("userId", String.valueOf(staff.getId()));
				request.getRequestDispatcher("staffHome.jsp").forward(request, response);
				
			} else {
				staff = null;
				request.setAttribute("message", "Invalid Password");	
				request.getRequestDispatcher("StaffLogin.jsp").forward(request, response);			
			}
		} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidHashException e) {
			// TODO Auto-generated catch block
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
