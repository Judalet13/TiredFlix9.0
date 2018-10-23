package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.cs3320.teamtired.data.db.CustomerDAO;
import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.CannotPerformOperationException;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.InvalidHashException;
import edu.txstate.cs3320.teamtired.user.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailAddress = request.getParameter("email-address");
		String password = request.getParameter("password");
		
		Customer customer = CustomerDAO.findCustomer(emailAddress);
		if (customer == null) {
			request.setAttribute("message", "Invalid Username");
			request.getRequestDispatcher("login.jsp").forward(request, response);	
			
		} 
		try {
			if (PasswordStorage.verifyPassword(password, customer.getPasswordHash())) {
				request.setAttribute("message", "");	
				HttpSession session = request.getSession();
				LOGGER.info("User " + customer.getId() + " logged in");				
				
				session.setAttribute("userId", String.valueOf(customer.getId()));
				request.getRequestDispatcher("index").forward(request, response);
				
			} else {
				customer = null;
				request.setAttribute("message", "Invalid Password");	
				request.getRequestDispatcher("login.jsp").forward(request, response);			
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
