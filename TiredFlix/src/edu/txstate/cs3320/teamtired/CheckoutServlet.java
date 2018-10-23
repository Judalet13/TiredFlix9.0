package edu.txstate.cs3320.teamtired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.cs3320.teamtired.data.db.RentalDAO;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.helper.RentalFactory;
import edu.txstate.cs3320.teamtired.user.RentalRecord;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Film> cart = (ArrayList<Film>) session.getAttribute("cart");
		ArrayList<RentalRecord> rentalRecords = RentalFactory.makeRentalRecords((Integer.parseInt((String)session.getAttribute("userId"))), cart);
		
		RentalDAO rentalDAO = new RentalDAO();
		for (RentalRecord record : rentalRecords) {
			rentalDAO.save(record);
		}
		
		session.setAttribute("cart", new ArrayList<Film>());
		request.getRequestDispatcher("index").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
