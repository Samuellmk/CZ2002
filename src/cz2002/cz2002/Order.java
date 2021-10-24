package cz2002;

import java.util.ArrayList;

public class Order {

	private Staff staff;
	private ArrayList<MenuItem> orderItems;
	private int tableno;
	private boolean paid;
	
	
	/** 
	* Class constructor.
	*/
	public Order(Staff staff, ArrayList<MenuItem> orderItems, int tableno, boolean paid) 
	{
		this.staff = staff;
		this.orderItems = orderItems;
		this.tableno = tableno;
		this.paid = paid;		
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
		return this.orderItems;
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

}