package edu.txstate.cs3320.teamtired.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.cs3320.teamtired.actor.Actor;
import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.film.FilmCategory;
import edu.txstate.cs3320.teamtired.film.Film.FilmRating;
import edu.txstate.cs3320.teamtired.helper.FilmFactory;

/**
 * @author
 *
 */
public class FilmDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());
	
	private static final int    FILM_ID_COLUMN          = 1;
	private static final int    FILM_TITLE_COLUMN       = 2;
	private static final int    FILM_DESCRIPTION_COLUMN = 3;
	private static final int    FILM_LENGTH_COLUMN      = 4;
	private static final int    FILM_RATING_COLUMN      = 5;
	private static final int    FILM_RELEASE_YEAR       = 6;
	private static final String FILM_TABLE				= ".film ";
	
	private static final String FILM_SELECT_STRING      = "SELECT film.film_id, film.title, film.description," +
			"film.length, film.rating, film.release_year ";
	private static final String CATEGORY_CLAUSES = " FROM film, film_category WHERE film.film_id = film_category.film_id AND film_category.category_id =";
	private static final String ALPHABET_CLAUSES = "From film  where title like '";
	

	
	public static List <Film> findFilmsByAttributes (String title, String description, int length, FilmRating rating) {
		String selectString = buildString (title, description, length, rating);
		return findFilmsHelper(selectString);
	}
	
	public static List <Film> findFilmsByCategory(FilmCategory category){
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(CATEGORY_CLAUSES);
		stringBuilder.append(category.ordinal());
		String selectString = stringBuilder.toString();
		LOGGER.info(selectString);
		return findFilmsHelper(selectString);
		
	}

	public static List <Film> findFilmsAlphabetically(String firstCharacter){
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(ALPHABET_CLAUSES);
		stringBuilder.append(firstCharacter);
		stringBuilder.append ("%';");
		String selectString = stringBuilder.toString();
		LOGGER.info(selectString);
		return findFilmsHelper(selectString);
	}
	
	public static List<Film> findNewestFilms (int maxNew){
		StringBuilder sqlStatementBuilder = new StringBuilder(FILM_SELECT_STRING);
		sqlStatementBuilder.append("FROM ").append(DAO.getDBName()).append(FILM_TABLE).
							append("ORDER BY release_year desc ").
							append("LIMIT ").append(maxNew).append(";");
		String selectString = sqlStatementBuilder.toString();
		LOGGER.info(selectString);
		return findFilmsHelper(selectString);
	}
	
	private static List<Film> findFilmsHelper (String selectString){
		List <Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(selectString);
			films = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findFilmsHelper: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return films;
	}

	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}
	

	
	/**
	 * Build the WHERE clause for this query based on the parameters passed.
	 * If a string parameter is null or the empty string it won't be used.
	 * If an integer parameter is zero, it won't be used.
	 * If rating is null or unrated (UR) it won't be used 
	 * @param titleSubstring matches a substring in the movie's title
	 * @param descriptionSubstring matches a substring in the movie's description
	 * @param lengthMaximum matches any movie of running time lengthMaximum or less
	 * @param ratingMaximum matches any movie of with specified ratingMaximum or less
	 * @return String
	 */
	private static String buildString (String titleSubstring, String descriptionSubstring, int lengthMaximum, FilmRating ratingMaximum) {
		final String OPENING_QUOTED_WILD_CARD = "'%";
		final String CLOSING_QUOTED_WILD_CARD = "%'";
		
		int numberWhereClauses = 0;
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(" FROM ").append(getDBName()).append(FILM_TABLE).append(" WHERE ");
		
		// create the clause to select any movie that contains titleSubstring in its title
		if ((titleSubstring != null) && (titleSubstring != "")) {
			stringBuilder.append("title like ").
			append(OPENING_QUOTED_WILD_CARD).
			append(titleSubstring).
			append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}
		
		// create the clause to select any movie that contains descriptionSubstring in its description
		if ((descriptionSubstring != null) && (descriptionSubstring != "")) {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("description like ").
			append(OPENING_QUOTED_WILD_CARD).
			append(descriptionSubstring).
			append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}
		
		// create the clause to select any movie that has a running time <= lengthMaximum
		if (lengthMaximum > 0)  {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("length <= ").append(String.valueOf(lengthMaximum));
			numberWhereClauses++;
		}
		
		// create the clause to select any movie with a rating <= ratingMaximum
		// Note: Sakila stores movie ratings as enums that can be queried by their ordinal 
		// values. Ordinals for MySQL enum fields are assigned in order of declaration, just
		// as they are in Java; however, MySQL ordinals begin with 1, not 0
		if ((ratingMaximum != null) && (ratingMaximum != FilmRating.UR)){
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			int databaseRatingOrdinal = ratingMaximum.ordinal() + 1;
			stringBuilder.append("rating <= ").append(databaseRatingOrdinal);
			numberWhereClauses++;
		}
		String selectString = stringBuilder.toString();
		LOGGER.info(selectString);
		return selectString;
	}

	private static List<Film> buildResults (ResultSet results) {
		ArrayList <Film> films  = new ArrayList <Film> ();
		FilmFactory filmFactory = new FilmFactory ();
		try {
			while (results.next()) {
				int    id             =  results.getInt   (FILM_ID_COLUMN );
				String filmTitle       = results.getString(FILM_TITLE_COLUMN );
				String filmDescription = results.getString(FILM_DESCRIPTION_COLUMN);
				int    filmLength      = results.getInt   (FILM_LENGTH_COLUMN);
				String filmRating      = results.getString(FILM_RATING_COLUMN);
				String release_year    = results.getString(FILM_RELEASE_YEAR).substring(0, 4);
				Film film = filmFactory.makeFilm(id, filmTitle, filmDescription, release_year, filmLength, filmRating);
				films.add(film);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return films;
	}
	
	public static FilmCategory findFilmCategory(Film film) {
		FilmCategory category = null;
		
		StringBuilder sqlStringBuilder = new StringBuilder("SELECT name FROM ");
		sqlStringBuilder.append(DAO.getDBName()).append(".category JOIN ").append(DAO.getDBName()).append(".film_category ").
		append("ON film_category.category_id = category.category_id ").
		append("WHERE film_category.film_id = ").
		append(film.getFilmID()).append(";");
		String query = sqlStringBuilder.toString();
		LOGGER.info(query);
		
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(query);
			while (results.next()) {
				String categoryString = results.getString(1);
				category = FilmFactory.convertCategory(categoryString);
			}
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.getFilmCategory: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return category;
	}

	public static Film getFilmDetail(int id) {
		StringBuilder sqlStatementBuilder = new StringBuilder(FILM_SELECT_STRING);
		sqlStatementBuilder.append("FROM ").append(DAO.getDBName()).append(FILM_TABLE).
							append("WHERE film.film_id = ").append(id).append(";");
		String selectString = sqlStatementBuilder.toString();
		LOGGER.info(selectString);
		Film film = findFilmsHelper(selectString).get(0);
		
		if (film.getActors().isEmpty()) {
			List<Actor> actors = ActorDAO.findActorsInFilm(film);
			for (Actor actor : actors) {
				film.addActor(actor);
			}
		}
		
		if (film.getCategory() == null) {
			film.setCategory(findFilmCategory(film));
		}
		
		LOGGER.info("Film details: " + film.toString());
		LOGGER.info("Actors: " + film.getActors().toString());
		LOGGER.info("Category: " + film.getCategory().toString());
		
		return film;
	}
}