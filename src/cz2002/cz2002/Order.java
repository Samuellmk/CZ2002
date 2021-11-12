package cz2002;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the order class, each order contains the staff, orderItems, paid status and customer of the order
 * @author JunHeng
 *
 */

public class Order {

	private Staff staff;
	private List<MenuItem> orderItems;
	private int tableno;
	private boolean paid;
	private Customer customer;
	
	
	/** 
	* Class constructor.
	* @param staff staff taking this order
	* @param orderItems the items ordered in this order
	* @param tableno the tableno that made this order 
	* @param paid the status of payment for this order
	* @param customer the customer that made this order 
	*/
	public Order(Staff staff, List<MenuItem> orderItems, int tableno, boolean paid, Customer customer) 
	{
		this.staff = staff;
		this.orderItems = orderItems;
		this.tableno = tableno;
		this.paid = paid;		
		this.customer = customer;
	}
	
	/**
	 *Getter method 
	 *@return The staff taking the order 
	 */
	public Staff getStaff() {
		return this.staff;
	}

	/**
	 * Setter method
	 * @param staff set the staff taking the order 
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	/**
	 *Getter method 
	 *@return the list of items being ordered in the order 
	 */
	public ArrayList<MenuItem> getOrderItems()
	{
		return (ArrayList<MenuItem>) this.orderItems;
	}

	/**
	 * Setter Method
	 * @param orderItems items ordered to the order
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		// TODO - implement Order.setOrderItems
		this.orderItems = orderItems; 
	}
	
	/**
	 * GetterMethod 
	 *@return the tableno for this order
	 */
	public int getTableno() {
		return this.tableno;
	}

	/**
	 * Setter Method
	 * @param tableno set tableno given for this order 
	 */
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}
	

	/**
	 * GetterMethod whether the order has been paid for
	 * @return the payment status of the order, true or false 
	 */
	public boolean getPaid() {
		return this.paid;
	}

	/**
	 * Setter Method
	 * @param paid set paid to true 
	 */
	public void setPaid(boolean paid) {
		this.paid = true;
	}


	/**
	 * Getter Method customer who made this order
	 * @return the customer that made this order
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Setter Method customer 
	 * @param customer customer who made this order
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}