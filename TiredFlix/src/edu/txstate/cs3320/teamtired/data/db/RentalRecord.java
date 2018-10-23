package edu.txstate.cs3320.teamtired.customer;

import java.util.Date;

public class RentalRecord {

	private int		rentalId;
	private Date 	rentalDate;
	private int		filmId;
	private int		customerId;
	private Date 	returnDate;
	
	public RentalRecord(int rentalId, Date rentalDate, int filmId, int customerId, Date returnDate) {
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.filmId = filmId;
		this.customerId = customerId;
		this.returnDate = returnDate;
	}
	
	public RentalRecord(int customerId, int filmId) {
		this.customerId = customerId;
		this.filmId = filmId;
	}
	
	@Override
	public String toString() {
		return "RentalRecord [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", filmId=" + filmId
				+ ", customerId=" + customerId + ", returnDate=" + returnDate + "]";
	}

	public int getRentalId() {
		return rentalId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public int getFilmId() {
		return filmId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public Date getReturnDate() {
		return returnDate;
	}


	
	
}
