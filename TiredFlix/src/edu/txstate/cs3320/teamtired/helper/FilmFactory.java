package edu.txstate.cs3320.teamtired.helper;

import edu.txstate.cs3320.teamtired.film.Film;
import edu.txstate.cs3320.teamtired.film.FilmCategory;

public class FilmFactory {
	
	public FilmFactory () {	
	}

	public Film makeFilm (String filmID, String title, String description,
			String releaseYear, String length, String rating) {
		int id = Integer.valueOf(filmID);
		int filmLength = Integer.valueOf(length);
		return new Film (id, title, description, releaseYear, filmLength, FilmFactory.convert(rating));
	}
	
	public Film makeFilm (int filmID, String title, String description,
			String releaseYear, int length, Film.FilmRating rating) {
				return new Film (filmID, title,description,releaseYear,length,rating);
		
	}

	public Film makeFilm (int filmID, String title, String description,
			String releaseYear, int length, String rating) {
		return new Film (filmID, title, description, releaseYear, length, FilmFactory.convert(rating));
	}
	
	public static Film.FilmRating convert (String rating) {
		switch (rating) {
		case "G": return Film.FilmRating.G;
		case "PG": return Film.FilmRating.PG;
		case "PG-13": return Film.FilmRating.PG_13;
		case "R": return Film.FilmRating.R;
		case "NC-17": return Film.FilmRating.NC_17;
		case "X": return Film.FilmRating.X;
		case "UR": return Film.FilmRating.UR;
		default: return Film.FilmRating.UR;
		}
	}
	
	public static FilmCategory convertCategory (String category) {
		category = category.toUpperCase();
		switch (category) {
		case "ACTION": return FilmCategory.ACTION;
		case "ANIMATION": return FilmCategory.ANIMATION;
		case "CHILDREN": return FilmCategory.CHILDREN;
		case "CLASSICS": return FilmCategory.CLASSICS;
		case "COMEDY": return FilmCategory.COMEDY;
		case "DOCUMENTARY": return FilmCategory.DOCUMENTARY;
		case "DRAMA": return FilmCategory.DRAMA;
		case "FAMILY": return FilmCategory.FAMILY;
		case "FOREIGN": return FilmCategory.FOREIGN;
		case "GAMES": return FilmCategory.GAMES;
		case "HORROR": return FilmCategory.HORROR;
		case "MUSIC": return FilmCategory.MUSIC;
		case "NEW": return FilmCategory.NEW;
		case "SCI_FI": return FilmCategory.SCI_FI;
		case "SPORTS": return FilmCategory.SPORTS;
		case "TRAVEL": return FilmCategory.TRAVEL;
		default: return null;
		}
	}
}
