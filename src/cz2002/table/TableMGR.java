package table;

import java.util.ArrayList;
import java.util.List;

/**
 * TableMGR can add or remove table from the Table List.
 * It also check if the table is available for reservation.
 * 
 * @author Samuel Leong
 */
public class TableMGR {
	
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
	
	/**
	 * This will set the table occupancy with respect to table No
	 * 
	 * @param tables		the Table List from Mainapp.java
	 * @param tableNo		the Table No of the table
	 * @param occupied		the table occupancy
	 */
	public static void setTableOccupancy(List<Table> tables, int tableNo, boolean occupied) {
		for(int i=0;i<tables.size() ;i++) {
			if(tables.get(i).getTableNo()==tableNo){
                tables.get(i).setOccupied(occupied);
            }
		}
	}
}
