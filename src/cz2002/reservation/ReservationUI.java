package reservation;

import java.util.List;
import java.util.Scanner;

import cz2002.Customer;
import table.Table;
import table.TableSeats;

public class ReservationUI {

	private Scanner sc = new Scanner(System.in);
	
	/**
	 * Class Constructor specifying the creation of reservation UI.
	 * Items here will be modified through call by reference.
	 * 
	 * @param reservation the staff from Main Application
	 * @param customer 	  the customer from Main Application
	 * @param table 	  the table from Main Application
	 */
	public ReservationUI(List<Reservation> reservation, List<Customer> customer, List<Table> table) {
		int choice = 0;
		while(choice != -1) {
			displayReservationUI();
			System.out.print("Choose an option: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					createReservationUI(reservation, table);
					break;
				case 2:
					removeReservationUI(reservation);
					break;
				case -1:
					System.out.println("Exiting Reservation Menu UI...");
					break;
				default:
					System.out.println("That is not a valid choice!");
					break;
			}
		}
	}
	/**
	 * Items here will be modified through call by reference.
	 * This is the remove reservation UI.
	 * 
	 * @param reservation the reservation from Main Application
	 */ 
	public void removeReservationUI(List<Reservation> reservation) {
		String contact = "";
		boolean validPhone= false;
		while(!validPhone) {
			System.out.print("Enter customer contact: ");
			contact = sc.next();
			validPhone= ReservationMGR.checkValidPhone(contact);
		}
		
		ReservationMGR.removeReservation(contact, reservation);
	}

	/**
	 * Items here will be modified through call by reference.
	 * This is the create reservation UI.
	 * 
	 * @param reservation the reservation from Main Application
	 * @param tables the table from Main Application
	 */ 
	public void createReservationUI(List<Reservation> reservation, List<Table> table) {
		String time = "";
		String date = "";
		int tableNo = -1;
		String dateTime = "";
		TableSeats pax = null;
		
		while(tableNo == -1) {
			boolean validDate = false;
			while(!validDate) {
				System.out.print("\nEnter the date (dd/MM/yyyy) or -1 to exit: ");
				date = sc.next();
				if(date.equals("-1")) {
					System.out.println("Exiting...");
					return;
				}
				validDate = ReservationMGR.checkValidDate(date);
			}
			
			boolean validTime = false;
			while(!validTime) {
				System.out.print("\nEnter the Time (HH:mm) or -1 to exit: ");
				time = sc.next();
				
				if(time.equals("-1")) {
					System.out.println("Exiting...");
					return;
				}
				validTime = ReservationMGR.checkValidTime(time);
				
			}
			dateTime = date + " " + time;
			
			System.out.println("\nEnter number of pax or -1 to exit");
			System.out.println("1)TWO 2)FOUR 3)SIX 4)EIGHT 5)TEN");
			int choice = sc.nextInt();
			if(choice == -1) {
				System.out.println("Exiting...");
				return;
			}
			while(choice < 1 || choice > 5) {
				System.out.println("Invalid pax entry...");
				choice = sc.nextInt();
			}
			for(int i=0; i<TableSeats.values().length;i++) {
				if(choice == i+1) {
					pax = TableSeats.values()[i];
					break;
				}
			}
		
			tableNo = ReservationMGR.checkReservation(pax, dateTime, reservation, table);
		}
		
		System.out.print("\nEnter customer name or -1 to exit: ");
		String name = sc.next();
		if(name.equals("-1")) {
			System.out.println("Exiting...");
			return;
		}
		while(name.matches(".*\\d.*")) {
			System.out.println("Invalid name entry...");
			System.out.print("Enter customer name: ");
			name = sc.next();
		}
			
		String contact = "";
		boolean validPhone= false;
		while(!validPhone) {
			System.out.print("\nEnter customer contact or -1 to exit: ");
			contact = sc.next();
			if(contact.equals("-1")) {
				System.out.println("Exiting...");
				return;
			}
			validPhone = ReservationMGR.checkValidPhone(contact);
			for(Reservation items: reservation) {
				if(items.getCustomer().getContact().contains(contact)) {
					System.out.println("Booking for this phone number exists - Try again");
					validPhone = false;
				}
			}
		}
		
		System.out.print("\nIs customer member (Y/N), enter -1 to exit: ");
		String choice = sc.next();
		if(choice.equals("-1")) {
			System.out.println("Exiting...");
			return;
		}
		char membershipCheck = Character.toLowerCase(choice.charAt(0));
		while((membershipCheck != 'y' || membershipCheck == 'n') && 
			  (membershipCheck == 'y' || membershipCheck != 'n')) {
			System.out.println("Invalid entry...");
			System.out.print("Is customer member (Y/N): ");
			membershipCheck = sc.next().charAt(0);
		}
		boolean membership = Character.toLowerCase(membershipCheck) == 'y' 
				? true: false;
		
		ReservationMGR.createReservation(dateTime, pax, name, contact, tableNo, membership, reservation);
		System.out.println("Reservation made under the name of " + name + 
				" for " + pax.label + " pax at " + dateTime);
	}

	
	
	/**
	 * This is to display reservation UI.
	 */
	private void displayReservationUI() {
		System.out.println("\n----------------------");
		System.out.println("Reservation Menu");	
		System.out.println("----------------------");
		System.out.println("1. Create Reservation");
		System.out.println("2. Remove Reservation");
		System.out.println("----------------------");
		System.out.println("Enter -1, to return to Main Menu");
		
	}
	
}