package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.cs3320.teamtired.data.db.CustomerDAO;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.CannotPerformOperationException;
import edu.txstate.cs3320.teamtired.user.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String emailAddress = request.getParameter("email-address");
		String phoneNumber = request.getParameter("phone-number");
		phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
		String password = request.getParameter("password");
		String passwordHash = null;
		try {
			passwordHash = PasswordStorage.createHash(password);
		} catch (CannotPerformOperationException e) {
			e.printStackTrace();
		}
		
		Customer testCustomer = CustomerDAO.findCustomer(emailAddress);
		if (testCustomer != null) {
			request.setAttribute("message", "User Already Exists");
			request.getRequestDispatcher("login.jsp").forward(request, response);	
		}
		
		Customer customer = null;
		try {
			customer = CustomerDAO.addEntry(firstName, lastName, emailAddress, phoneNumber, passwordHash);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		if (customer == null) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}		
		
		HttpSession session = request.getSession();
		LOGGER.info("User " + customer.getId() + " registered: " + customer.toString());
		
		session.setAttribute("userId", String.valueOf(customer.getId()));
		request.getRequestDispatcher("index").forward(request, response);
	}

}
