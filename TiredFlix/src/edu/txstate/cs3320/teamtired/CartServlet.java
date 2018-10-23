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

import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.CannotPerformOperationException;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.InvalidHashException;
import edu.txstate.cs3320.teamtired.user.Customer;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println("CartServlet film_id: " + request.getParameter("film_id"));

		int FilmID = Integer.parseInt(request.getParameter("film_id"));
		Film film = FilmDAO.getFilmDetail(FilmID);

		
		ArrayList<Film> cart = (ArrayList<Film>) session.getAttribute("cart");
		cart.add(film); // adds the film to the cart associated with the session.


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
