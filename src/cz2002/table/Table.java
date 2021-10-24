package table;

/**
 * The Table Class that will be used in Table ArrayList.
 * 
 * @author Samuel Leong
 */
public class Table {
	private TableSeats capacity;
	private int tableNo;
	private boolean occupied;
	
	/** 
	* Class constructor specifying the creation of table object.
	* 
	* @param tableNo  the table Number of the table
	* @param capacity the number of seats of the table
	* @param occupied the occupancy of the table
	*/
	public Table (int tableNo, TableSeats capacity, boolean occupied) {
		this.tableNo=tableNo;
		this.capacity=capacity;
		this.occupied=occupied;
	}
	/**
	 * Return number of seats of the table.
	 * 
	 * @return the number of seats of the table
	 */
	public TableSeats getCapacity() {
		return capacity;
	}
	
	/**
	 * Setting the number of seats of the table.
	 *  
	 * @param capacity the number of seats of the table;
	 */
	public void setCapacity(TableSeats capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Return table number of the table.
	 * 
	 * @return the table number of the table
	 */
	public int getTableNo() {
		return tableNo;
	}
	
	/**
	 * Setting the table number of the table.
	 *  
	 * @param tableNo the table number of the table
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	
	/**
	 * Return occupancy of the table.
	 * 
	 * @return occupancy of the table
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	/**
	 * Setting occupancy of the table.
	 *  
	 * @param ocupied the occupancy of the table
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	

}
