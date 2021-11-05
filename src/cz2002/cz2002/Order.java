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
	 * 
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
	 * 
	 * @param orderItems
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		// TODO - implement Order.setOrderItems
		this.orderItems = orderItems; 
	}

	public int getTableno() {
		return this.tableno;
	}

	/**
	 * 
	 * @param tableno 
	 */
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}

	public boolean getPaid() {
		return this.paid;
	}

	/**
	 * 
	 * @param paid
	 */
	public void setPaid(boolean paid) {
		this.paid = true;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}