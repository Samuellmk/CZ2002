package invoice;

import java.util.ArrayList;
import cz2002.MenuItem;

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

	public String getEmployeeID() {
		return this.employeeID;
	}

	/**
	 * 
	 * @param staff
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public ArrayList<MenuItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * 
	 * @param orderItems
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
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

	public String getTimestamp() {
		return this.timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getServicechrg() {
		return this.servicechrg;
	}

	/**
	 * 
	 * @param servicechrg
	 */
	public void setServicechrg(double servicechrg) {
		this.servicechrg = servicechrg;
	}

	public double getGST() {
        return this.GST;
	}

	/**
	 * 
	 * @param GST
	 */
	public void setGST(double GST) {
        this.GST = GST;
	}

	public boolean getDiscount() {
		return this.discount;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotal() {
		return this.total;
	}

}