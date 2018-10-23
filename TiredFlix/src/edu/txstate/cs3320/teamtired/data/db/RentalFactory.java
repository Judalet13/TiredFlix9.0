package edu.txstate.cs3320.teamtired.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import edu.txstate.cs3320.teamtired.customer.RentalRecord;
import edu.txstate.cs3320.teamtired.user.Customer;

public class RentalFactory {

	public RentalFactory() {
	}
	
	public ArrayList<RentalRecord> makeRentalRecords(int customerId, ArrayList<Integer> filmIds){
		ArrayList<RentalRecord> rentalRecords = new ArrayList<RentalRecord>();
		
		for (Integer filmId : filmIds) {
			RentalRecord record = new RentalRecord(customerId, filmId);
			rentalRecords.add(record);
		}
		
		return rentalRecords;
	}
	
	public static HashMap<Integer, ArrayList<RentalRecord>> sortRecordsByCustomer(List<RentalRecord> rentalRecords){
		HashMap<Integer, ArrayList<RentalRecord>> sortedRecords = new HashMap<Integer, ArrayList<RentalRecord>>();
		
		for (RentalRecord record : rentalRecords) {
			int customerId = record.getCustomerId();
			if (!sortedRecords.containsKey(customerId)) {
				sortedRecords.put(customerId, new ArrayList<RentalRecord>());
			}
			sortedRecords.get(customerId).add(record);
		}
		
		for (ArrayList<RentalRecord> records : sortedRecords.values()) {
			records.sort(compareRecords());
		}
		
		return sortedRecords;
	}
	
	public static Comparator<RentalRecord> compareRecords() {
		Comparator<RentalRecord> rentalRecordComparator = Comparator.comparing(RentalRecord::getReturnDate, Comparator.nullsFirst(Comparator.naturalOrder()));
		return rentalRecordComparator;
	}
}
