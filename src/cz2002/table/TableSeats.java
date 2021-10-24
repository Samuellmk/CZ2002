package table;

/**
 * The table seats ranging from 2 to 10 
 * with interval of 2.
 * 
 * @author Samuel Leong
 */
public enum TableSeats {
	TWO(2),
	FOUR(4),
	SIX(6),
	EIGHT(8),
	TEN(10);
	
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
