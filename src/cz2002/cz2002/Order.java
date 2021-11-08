package cz2002;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Staff staff;
	private List<MenuItem> orderItems;
	private int tableno;
	private boolean paid;
	private Customer customer;
	
	
	/** 
	* Class constructor.
	*/
	public Order(Staff staff, List<MenuItem> orderItems, int tableno, boolean paid, Customer customer) 
	{
		this.staff = staff;
		this.orderItems = orderItems;
		this.tableno = tableno;
		this.paid = paid;		
		this.customer = customer;
	}
	
	
	public Staff getStaff() {
		return this.staff;
	}

	/**
	 * Setter method
	 * @param staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public ArrayList<MenuItem> getOrderItems()
	{
		return (ArrayList<MenuItem>) this.orderItems;
	}

	/**
	 * Setter Method
	 * @param orderItems items ordered
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		// TODO - implement Order.setOrderItems
		this.orderItems = orderItems; 
	}
	
	/**
	 * GetterMethod
	 */
	public int getTableno() {
		return this.tableno;
	}

	/**
	 * Setter Method
	 * @param tableno tableno given
	 */
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}
	

	/**
	 * GetterMethod whether the order has been paid for
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
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Setter Method customer who made this order
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}