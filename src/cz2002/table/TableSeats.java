package table;

/**
 * The table seats ranging from 2 to 10 
 * with interval of 2.
 * 
 * @author Samuel Leong
 */
public enum TableSeats {
	/**
	 * 	Table for 2 pax
	 */
	TWO(2),
	
	/**
	 * 	Table for 4 pax
	 */
	FOUR(4),
	
	/**
	 * 	Table for 6 pax
	 */
	SIX(6),
	
	/**
	 * 	Table for 8 pax
	 */
	EIGHT(8),
	
	/**
	 * 	Table for 10 pax
	 */
	TEN(10);
	
	/**
	 * 	Label is the actual value
	 */
	public final int label;
	
	/**
	 * This is to get the value of 
	 * each enumeration.
	 * 
	 * @param label the value of 
	 * 		  each enumeration
	 */
	private TableSeats(int label) {
		this.label = label;
	}
}
