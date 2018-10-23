package edu.txstate.cs3320.teamtired.film;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.txstate.cs3320.teamtired.actor.Actor;

public class Film {
	public enum FilmRating {
		G, PG, PG_13, R, NC_17, X, UR
	}
	
	private int filmID;
	private String title;
	private String description;
	private String releaseYear;
	private int length;
	private FilmRating rating;
	private List <Actor> actors;
	private String posterImage;
	private FilmCategory category;

	public Film(int filmID, String title, String description,
			String releaseYear, int length, FilmRating rating) {
		super();
		this.filmID = filmID;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.length = length;
		this.rating = rating;
		this.actors = new ArrayList <Actor> ();
		
		int rand = (new Random()).nextInt(10);
		switch (rand) {
		case 0: this.posterImage = "poster0.jpg"; break;
		case 1: this.posterImage = "poster1.jpg"; break;
		case 2: this.posterImage = "poster2.jpg"; break;
		case 3: this.posterImage = "poster3.jpg"; break;
		case 4: this.posterImage = "poster4.jpg"; break;
		case 5: this.posterImage = "poster5.jpg"; break;
		case 6: this.posterImage = "poster6.jpg"; break;
		case 7: this.posterImage = "poster7.jpg"; break;
		case 8: this.posterImage = "poster8.jpg"; break;
		default: this.posterImage = "poster9.jpg"; break;
		}
	}
	
	public void addActor (Actor anActor) {
		if (!actors.contains(anActor)) actors.add(anActor);
	}
	
	public List <Actor>getActors () {
		return actors;
	}

	public String getDescription() {
		return description;
	}

	public int getFilmID() {
		return filmID;
	}

	public int getLength() {
		return length;
	}

	public FilmRating getRating() {
		return rating;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setRating(FilmRating rating) {
		this.rating = rating;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPosterImage() {
		return posterImage;
	}

	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	
	@Override
	public String toString() {
		return "Film [filmID=" + filmID + ", title=" + title + ", description="
				+ description + ", releaseYear=" + releaseYear + ", length="
				+ length + ", rating=" + rating + "]";
	}

	public FilmCategory getCategory() {
		return category;
	}

	public void setCategory(FilmCategory category) {
		this.category = category;
	}
}
