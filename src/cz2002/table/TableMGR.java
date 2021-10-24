package table;

import java.util.List;

/**
 * TableMGR can add or remove table from the Table List.
 * It also check if the table is available for reservation.
 * 
 * @author Samuel Leong
 */
public class TableMGR {

	/**
	 * This will add new table to the Table List.
	 * 
	 * @param tableNo	the table number assigned to the table
	 * @param capacity	the table capacity assigned to the table
	 * @param reserved  the table occupancy assigned to the table
	 * @param tables	the Table List from MainApp.java
	 */
	public static void addTable(int tableNo, TableSeats capacity, boolean occupied, List<Table> tables) {
		Table newTable = new Table(tableNo, capacity, occupied);
		tables.add(newTable);
	}
	
	/**
	 * This will remove new table to the Table List.
	 * 
	 * @param tableNo	the table number assigned to the table
	 * @param tables	the Table List from MainApp.java
	 */
	public static void removeTable(int tableNo, List<Table> tables) {
		for(int i=0; i<tables.size(); i++) {
			if(tableNo == tables.get(i).getTableNo())
				tables.remove(i);
		}
	}
	
	/**
	 * This will check the table availability in regards to 
	 * table taken and pax limit of the table from the Table List.
	 * 
	 * @param pax			the pax input
	 * @param tables		the Table List from Mainapp.java
	 * @param tableNoTaken	the Table Taken from ReservationMGR.java
	 * @return Integer		the table No that is either available
	 */
	public static int checkTableAvail(TableSeats pax, List<Table> tables, List<Integer> tableNoTaken) {
		if(tables.size() == 0)
			System.out.println("No table found in list...");
		for(Table table: tables) {
			if(!tableNoTaken.contains(table.getTableNo()) &&
					table.getCapacity().label >= pax.label)
				return table.getTableNo();
		}
		return -1;
	}
}
