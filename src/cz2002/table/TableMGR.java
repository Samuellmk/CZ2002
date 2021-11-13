package table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import reservation.Reservation;

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
	 * @return Integer		the table No that is either available or
	 * 						not
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
	 * Printing all available tables with respect to date and time
	 * 
	 * @param dateTime		the date and time inputted
	 * @param tables		the Table List from Mainapp.java
	 * @param reservations	the Reservation List from Mainapp.java
	 */
	
	public static void printTableAvail(String dateTime, List<Table> tables, List<Reservation> reservations) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTimeInput = LocalDateTime.parse(dateTime, formatter);
		LocalDateTime itemDateTime;
		
		ArrayList<Table> tablesTaken = new ArrayList<>();
		
		// itemDateTime = 1300
		// dateTimeInput = 1345
		// 1300+45 is after 1345
		// 1345+45 is after 1345
		for(Reservation item : reservations) {
			itemDateTime = LocalDateTime.parse(item.getDateTime(), formatter);
			for(Table table: tables) {
				if(table.getTableNo() == item.getTableNo() && 
						   itemDateTime.plusMinutes(45).isAfter(dateTimeInput)
						&& dateTimeInput.plusMinutes(45).isAfter(itemDateTime))
					tablesTaken.add(table);
			}
		}
		
		List<Table> tempTable = new ArrayList<>(tables);
		tempTable.removeAll(tablesTaken);
		
		if(tempTable.size() == 0) {
			System.out.println("No tables Available at this date and time...");
			return;
		}
		
		for(Table table: tempTable)
			System.out.println("Table " + table.getTableNo() + " for " 
				+ table.getCapacity().label + " pax");
		
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
