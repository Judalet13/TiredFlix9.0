package edu.txstate.cs3320.teamtired.film;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class FilmCatalog {

	private ArrayList <Film> films;
	private static FilmCatalog instance = null;

	public static FilmCatalog getInstance () {
		if (instance == null) {
			instance = new FilmCatalog ();
		}
		return instance;
	}

	private FilmCatalog () {
		films = new ArrayList <Film> ();
	}

	public void add (Film aFilm) {
		films.add(aFilm);
	}
	public void addAll (Collection<Film> newFilms) {
		films.addAll(newFilms);
	}
	public Film get (int id) {
		if ((id >= 1) && (id <= films.size())) return films.get(id-1);
		return null;
	}



}
