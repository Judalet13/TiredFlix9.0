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
import edu.txstate.cs3320.teamtired.helper.ActorFactory;

public class ActorDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(ActorDAO.class.getName());
	
	private static final int    ACTOR_ID_COLUMN          		= 1;
	private static final int    ACTOR_FIRST_NAME_COLUMN       	= 2;
	private static final int    ACTOR_LAST_NAME_COLUMN 			= 3;
	private static final String ACTOR_TABLE						= ".actor ";
	
	private static final String ACTOR_SELECT_STRING      = "SELECT actor.actor_id, actor.first_name, actor.last_name ";
	
	
	
	
	
	public static List<Actor> findActorsInFilm(Film film){
		List<Actor> actors = new ArrayList<Actor>();
		StringBuilder queryBuilder = new StringBuilder(ACTOR_SELECT_STRING);
		queryBuilder.append("FROM ").append(DAO.getDBName()).append(ACTOR_TABLE).		
					 append("WHERE actor_id in ").
					 append("(SELECT actor_id FROM film_actor WHERE film_id = ").
					 append(film.getFilmID()).append(");");
		String query = queryBuilder.toString();
		LOGGER.info(query);
		
		actors = findActorsHelper(query);
		
		return actors;
	}
	
	private static List<Actor> findActorsHelper (String selectString){
		List <Actor> actors = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(selectString);
			actors = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("ActorDAO.findActorsHelper: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return actors;
	}
	
	private static List<Actor> buildResults (ResultSet results) {
		ArrayList <Actor> actors  = new ArrayList <Actor> ();
		ActorFactory actorFactory = new ActorFactory ();
		try {
			while (results.next()) {
				int    id              =  results.getInt   (ACTOR_ID_COLUMN );
				String firstName       = results.getString(ACTOR_FIRST_NAME_COLUMN );
				String lastName		   = results.getString(ACTOR_LAST_NAME_COLUMN);
				Actor actor = actorFactory.makeActor(id, firstName, lastName);
				actors.add(actor);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return actors;
	}
	
	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}
	
}
