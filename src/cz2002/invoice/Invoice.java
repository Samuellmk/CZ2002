public class Invoice {

	private Staff staff;
	private ArrayList<OrderItem> orderItems;
	private int tableno;
	private Date timestamp;
	private double servicechrg;
	private double GST;
	private boolean discount;

    public Invoice(Staff staff, ArrayList<OrderItem> orderItems, int tableno, String timestamp, double servicechrg, double GST, boolean discount){
        this.staff = staff;
        this.orderItems = orderItems;
        this.tableno = tableno;
        this.timestamp = timestamp;
        this.servicechrg = servicechrg;
        this.GST = GST;
        this.discount = discount;
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

	public ArrayList<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * 
	 * @param orderItems
	 */
	public void setOrderItems(ArrayList<OrderItem> orderItems) {
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

	public Date getTimestamp() {
		return this.timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(Date timestamp) {
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

}