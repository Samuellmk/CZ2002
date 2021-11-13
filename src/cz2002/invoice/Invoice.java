package invoice;

import java.util.ArrayList;
import cz2002.MenuItem;

/**
* Invoice class that will be used for generating an invoice and sales revenue report.
*
* @author Tejas Rajagopal
*/ 

public class Invoice {
	
	private String employeeID;
	private ArrayList<MenuItem> orderItems;
	private int tableno;
	private String timestamp;
	private double servicechrg;
	private double GST;
	private boolean discount;
	private double total;

    public Invoice(String employeeID, ArrayList<MenuItem> orderItems, int tableno, String timestamp, double servicechrg, double GST, boolean discount, double total){
        this.employeeID = employeeID;
        this.orderItems = orderItems;
        this.tableno = tableno;
        this.timestamp = timestamp;
        this.servicechrg = servicechrg;
        this.GST = GST;
        this.discount = discount;
        this.total = total;
    }

	/**
	 * Return employeeID of staff
	 * 
	 * @return employeeID of the staff
	 */
	public String getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * Set EmployeeID of Staff
	 * 
	 * @param employeeID the staff who took the order
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * Return menu items in the order
	 * 
	 * @return list of menu items
	 */
	public ArrayList<MenuItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * Set menu items in order
	 * 
	 * @param orderItems the list of menu items
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * Return table number of order
	 * 
	 * @return the table number of order
	 */
	public int getTableno() {
		return this.tableno;
	}

	/**
	 * Set table number 
	 * 
	 * @param tableno the table number of the order
	 */
	public void setTableno(int tableno) {
		this.tableno = tableno;
	}

	/**
	 * Return timestamp of invoice
	 * 
	 * @return the timestamp of the invoice
	 */
	public String getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Set timestamp
	 * 
	 * @param timestamp the date and time at which the invoices is generated
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Return service charge 
	 * 
	 * @return service charge of order 
	 */
	public double getServicechrg() {
		return this.servicechrg;
	}

	/**
	 * Set service charge 
	 * 
	 * @param servicechrg the service charge of order 
	 */
	public void setServicechrg(double servicechrg) {
		this.servicechrg = servicechrg;
	}

	/**
	 * return GST
	 * 
	 * @return the GST amount of invoice
	 */
	public double getGST() {
        return this.GST;
	}

	/**
	 * Set GST
	 * 
	 * @param GST the amount of GST
	 */
	public void setGST(double GST) {
        this.GST = GST;
	}

	/**
	 * Returns true or false based on whether the customer is a member
	 * 
	 * @return true or false 
	 */
	public boolean getDiscount() {
		return this.discount;
	}
	
	/**
	 * Set the total amount
	 * 
	 * @param total the total amount of the order
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Return the total amount of the order 
	 * 
	 * @return total amount of order
	 */
	public double getTotal() {
		return this.total;
	}

}