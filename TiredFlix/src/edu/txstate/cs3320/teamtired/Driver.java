package edu.txstate.cs3320.teamtired;

import java.util.List;

import edu.txstate.cs3320.teamtired.data.db.CustomerDAO;
import edu.txstate.cs3320.teamtired.data.db.FilmDAO;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.film.Film.FilmRating;
import edu.txstate.cs3320.teamtired.film.FilmCategory;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.CannotPerformOperationException;
import edu.txstate.cs3320.teamtired.hash.PasswordStorage.InvalidHashException;
import edu.txstate.cs3320.teamtired.user.Customer;

/**
 * 
 * @author Shelly Smith (s_s598), Christopher Judalet (crj58) , Dylan Olgin (dao54)
 *
 */
public class Driver {

	public static void main(String[] args) {
//		Use Case 1: Land on CyberFlix Splash Page
		System.out.println("Use Case 1: Land on CyberFlix Splash Page");
		System.out.println("8 latest movies:");
		List<Film> newestFilms = FilmDAO.findNewestFilms(8);
		for (Film film : newestFilms) {
			System.out.println(film.toString());
		}
//		Use Case 2: Search for Films by Attribute
		System.out.println("\nUse Case 2: Search for Films by Attribute");
		System.out.println("Searched with title = 'dino'");
		List<Film> searchResults = FilmDAO.findFilmsByAttributes("Dino", "", 0, null);
		for (Film film : searchResults) {
			System.out.println(film.toString());
		}

		System.out.println("\nSearched with description = 'robot'");
		searchResults = FilmDAO.findFilmsByAttributes("", "robot", 0, null);
		for (Film film : searchResults) {
			System.out.println(film.toString());
		}

		System.out.println("\nSearched with length = 50");
		searchResults = FilmDAO.findFilmsByAttributes("", "", 50, null);
		for (Film film : searchResults) {
			System.out.println(film.toString());
		}

		System.out.println("\nSearched with rating = G");
		searchResults = FilmDAO.findFilmsByAttributes("", "", 0, FilmRating.G);
		for (Film film : searchResults) {
			System.out.println(film.toString());
		}

		System.out.println("\nSearched with title = 'Cab', description = 'man', time = 90, rating = NC-17");
		searchResults = FilmDAO.findFilmsByAttributes("Cab", "man", 90, FilmRating.NC_17);
		for (Film film : searchResults) {
			System.out.println(film.toString());
		}
		
//		Use Case 3: Browse Films by Category
		System.out.println("\nUse Case 3: Browse Films by Category");
		System.out.println("Searched with Category = Classics");
		List<Film> browseCategory = FilmDAO.findFilmsByCategory(FilmCategory.CLASSICS);
		for (Film film : browseCategory) {
			System.out.println(film.toString());
		}
		
//		Use Case 4: Browse Films Alphabetically
		System.out.println("\nUse Case 4: Browse Films Alphabetically");
		System.out.println("Browsing the B's");
		List<Film> browseAlphabetically = FilmDAO.findFilmsAlphabetically("b");
		for (Film film : browseAlphabetically) {
			System.out.println(film.toString());
		}	
		
//		Use Case 5: View Film Detail
		System.out.println("\nUse Case 5: View Film Detail");
		Film getFilmDetails  = FilmDAO.getFilmDetail(3);
		System.out.println(getFilmDetails.toString());			
		
//		Use Case 7: User Login
		System.out.println("\nUse Case 7: User Login");
		System.out.println("Getting a customer from email Mary.Smith@SakilaCustomer.org");
		Customer user = CustomerDAO.findCustomer("Mary.Smith@SakilaCustomer.org");
		System.out.println(user.toString());	
		
		try {
			if (PasswordStorage.verifyPassword("password1", user.getPasswordHash())) {
				System.out.println("password1 is the correct password for that user");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
