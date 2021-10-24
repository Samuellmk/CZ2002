package reservation;

import cz2002.Customer;
import table.TableSeats;

/**
 * The Reservation consists of customer object, and reservation
 * will be used in Reservation ArrayList in MainApp.java.
 * 
 * @author Samuel Leong
 */
public class Reservation {
	private String dateTime;
	private TableSeats pax;
	private int tableNo;
	private Customer customer;
	

	/**
	 * Class constructor specifying the creation of Reservation object.
	 * The objects will be added to Reservation ArrayList created 
	 * in MainApp.java.
	 * 
	 * @param dateTime 	 the date and time of this reservation object
	 * @param pax		 the pax of this customer object that belongs 
	 * 					 to this reservation object
	 * @param name		 the name of this customer object that belongs 
	 * 					 to this reservation object
	 * @param contact	 the contact number of this reservation object
	 * @param tableNo	 the tableNo allocated for this reservation object
	 * @param membership the membership of the customer that belongs to 
	 * 					 this reservation object
	 */
	public Reservation(String dateTime, TableSeats pax, String name, String contact, int tableNo, boolean membership) {
		customer = new Customer(membership, name, contact);
		this.tableNo = tableNo;
		this.pax = pax;
		this.dateTime = dateTime;
	}
	
	/**
	 * Return the table number of this reservation.
	 * 
	 * @return the tableNo allocated to this specified reservation
	 */
	public int getTableNo() {
		return tableNo;
	}
	
	/**
	 * Setting the table number to this reservation.
	 *  
	 * @param tableNo the table number to be set to this reservation
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/**
	 * Return the date and time of this reservation.
	 * 
	 * @return the date and time of this specified reservation
	 */
	public String getDateTime() {
		return dateTime;
	}
	
	/**
	 * Setting the date and time to this reservation.
	 *  
	 * @param dateTime the date and time to be set to this reservation
	 */
	public void setDatetime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	/**
	 * Return pax of this reservation.
	 * 
	 * @return TableSeats the pax of this specified reservation
	 */
	public TableSeats getPax() {
		return pax;
	}
	
	/**
	 * Setting the pax to this reservation.
	 *  
	 * @param pax the pax to be set to this reservation
	 */
	public void setPax(TableSeats pax) {
		this.pax = pax;
	}
	
	/**
	 * Return Customer of this reservation.
	 * 
	 * @return Customer the Customer class of this specified reservation
	 */
	public Customer getCustomer() {
		return customer;
	}
		
	/**
	 * Setting the Customer to this reservation.
	 *  
	 * @param customer the Customer class to be set to this reservation
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
