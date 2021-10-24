package reservation;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cz2002.Customer;
import table.Table;
import table.TableMGR;
import table.TableSeats;
/**
* ReservationMGR can create or remove reservation based on certain inputs.
* It can also check if the reservation has the valid inputs.
*
* @author Samuel Leong
*/
public class ReservationMGR {
	
	private final static int EXPIRY_MINS = 15;
	private final static int ADV_BOOKING_DAYS = 7;
	private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	
	/**
	 * createReservation will takes inputs and create a reservation object
	 * and add it to the reservationItems List.
	 * 
	 * @param dateTime 			the date & time of the reservation
	 * @param pax				the pax of the reservation
	 * @param name				the name of the customer that belongs to 
	 * 							the reservation
	 * @param contact			the contact of the customer that belongs to
	 * 							the reservation
	 * @param tableNo			the tableNo of the reservation
	 * @param membership		the membership of the customer
	 * @param reservationItems	the List that is passed by reference
	 */
	public static void createReservation(String dateTime, TableSeats pax, String name, String contact, int tableNo, boolean membership, List<Reservation> reservationItems) {
		Reservation newReservation = new Reservation(dateTime, pax, name, contact, tableNo, membership);
		reservationItems.add(newReservation);
	}
	
	
	/**
	 * checkReservation ensures reservations made are blocked out for the 
	 * next 45 mins, giving customers ample time to dine. 
	 * However, customers are allow to book on another day or time if
	 * no slot on given date and time.
	 * 
	 * @param pax				the pax of the reservation
	 * @param dateTime			the date & time of the reservation
	 * @param reservationItems  the resevation List that is passed by 
	 * 							reference
	 * @param tables			the tables List that is passed by reference
	 * @return
	 */
	public static int checkReservation(TableSeats pax, String dateTime, List<Reservation> reservationItems, List<Table> tables) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime inputDateTime = LocalDateTime.parse(dateTime, formatter);
		
		ArrayList<Integer> tableNoTaken = new ArrayList<Integer>();
		for(Reservation item: reservationItems) {
			LocalDateTime bookedTimeDate = LocalDateTime.parse(item.getDateTime(), formatter);
			if(inputDateTime.plusMinutes(1).isAfter(bookedTimeDate) && 
			   bookedTimeDate.plusMinutes(45).isAfter(inputDateTime)) {
				tableNoTaken.add(item.getTableNo());
				System.out.println(item.getTableNo());
			}
		}
		int tableNo = TableMGR.checkTableAvail(pax, tables, tableNoTaken);
		if(tableNo == -1) {
			System.out.println("No table for "+pax+" pax at "+dateTime);
			System.out.println("Either try another day or time");
			return -1;
		} 
		else
			return tableNo;
		
	}

	
	/**
	 * removeReservation removes reservation based on the inputs.
	 * 
	 * @param contact			the contact of the customer
	 * @param reservationItems	the List that is passed by reference
	 */
	public static void removeReservation(String contact, List<Reservation> reservationItems) {
		for(int i=0; i<reservationItems.size(); i++) {
			Customer customer = reservationItems.get(i).getCustomer();
			if(contact.equals(customer.getContact())) {
				System.out.println("***"+customer.getName()+
						"\'s reservation removed ("+customer.getContact()+
						")***");
				reservationItems.remove(i);
				return;
			}
		}
		System.out.println("No Entry Found/Removed");
	}
	
	
	/**
	 * checkValidDate ensures the booking made is one day in 
	 * advance and also not past the advance days of 7.
	 * For Example, today is 20th Oct, bookable dates are from
	 * 21st Oct to 27th Oct.
	 * 
	 * @param date		the date input
	 * @return boolean	the boolean determines validity
	 */
	public static boolean checkValidDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ltDate = LocalDate.parse(date, formatter);
			if(ltDate.isAfter(LocalDate.now()) && 
			   ltDate.isBefore(LocalDate.now().plusDays(ADV_BOOKING_DAYS)))
				return true;
			else {
				System.out.println("Please enter booking day one day after today");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(date+" is not a valid date");
			return false;
		}
	}
	
	/**
	 * checkValidTime ensures the booking time made is between
	 * 18:30 and 20:45.
	 * 
	 * @param time		the time input
	 * @return boolean	the boolean determines validity
	 */
	public static boolean checkValidTime(String time) {
		try {
			LocalTime bookingTime= LocalTime.parse(time);
			if(bookingTime.isAfter(LocalTime.parse("18:29")) &&
			   bookingTime.isBefore(LocalTime.parse("20:46")))
				return true;
			else {
				System.out.println(time+" is not between 18:30 and 20:45");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(time+" is not a valid time");
			return false;
		}
	}
		
	/**
	 * checkValidPhone ensures the phone number is 8 digits
	 * long with initial digit of 8 and 9.
	 * 
	 * @param contact	the contact input
	 * @return boolean	the boolean determines validity
	 */
	public static boolean checkValidPhone(String contact) {
		while(contact.length() != 8 ||
				(contact.charAt(0) < '8' || contact.charAt(0) > '9')) {
				System.out.println("Invalid phone number - Try Again");
				System.out.print("Enter phone number: ");
				return false;
			}
		return true;
	}
	
	
	/**
	 * checkExpiry will invokes scheduler for each reservation in
	 * reservations Array List. It will displays all the reservation
	 * with expiring time.
	 * 
	 * @param reservationItems  the reservations ArrayList from 
	 * 							MainApp.java
	 */
	public static void checkExpiry(List<Reservation> reservationItems) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime itemDateTime;
    	LocalDateTime dateTimeNow = LocalDateTime.now(); 
    	
		System.out.println("--------------------------------------------");
		System.out.println("Reservations List");
		System.out.println("--------------------------------------------");
    	
		for(Reservation item : reservationItems) {
			itemDateTime = LocalDateTime.parse(item.getDateTime(), formatter);
			
			LocalDateTime tempDateTime = LocalDateTime.from( dateTimeNow );
			long days = tempDateTime.until( itemDateTime, ChronoUnit.DAYS );
			tempDateTime = tempDateTime.plusDays( days );

			long hours = tempDateTime.until( itemDateTime, ChronoUnit.HOURS );
			tempDateTime = tempDateTime.plusHours( hours );

			long minutes = tempDateTime.until( itemDateTime, ChronoUnit.MINUTES );
			tempDateTime = tempDateTime.plusMinutes( minutes );
			
			long diffMins = ChronoUnit.MINUTES.between(dateTimeNow, itemDateTime) + EXPIRY_MINS;
			System.out.println("Reservation for "+item.getCustomer().getName()+
					" ("+item.getCustomer().getContact()+") is expiring in "+
					days+" days "+hours+" hours "+minutes+" mins ("+diffMins+" mins)");
			
			final Runnable dateTime = new Runnable() { 
				/**
				 * This will remove reservation once the time is up.
				 */
				public void run() {
	    	        // Write code here that you want to execute periodically.
					removeReservation(item.getCustomer().getContact(), reservationItems);
					ArrayList<Reservation> tempArrayList = new ArrayList<Reservation>(reservationItems);
					ReservationIOMGR.writeToFile(tempArrayList);
	    	    }
			};
		
			scheduler.schedule(dateTime, diffMins, TimeUnit.MINUTES);
		}
		System.out.println("--------------------------------------------");
	}
		
}
