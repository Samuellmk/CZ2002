package cz2002;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import reservation.Reservation;
import reservation.ReservationIOMGR;
import reservation.ReservationMGR;
import reservation.ReservationUI;
import table.Table;
import table.TableIOMGR;

/**
 * This is the Main application class program will start from here
 * Staff will use this menu to handle restaurant functions
 * 
 */

public class MainApp {
	public static void main(String[] args) {
		ArrayList<Food> foodItems = new ArrayList<Food>();
		ArrayList<PromoPackage> promoItems = new ArrayList<PromoPackage>();
		ArrayList<Staff> staff = new ArrayList<Staff>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		ArrayList<Table> tables = new ArrayList<Table>();
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		foodItems = FoodIOMGR.readFromFile();
		promoItems = PromoIOMGR.readFromFile();
		staff = StaffIOMGR.readFromFile();
		customers = CustIOMGR.readFromFile();
		reservations = ReservationIOMGR.readFromFile();
		tables = TableIOMGR.readFromFile();
		
		// Setting up Reservation Expiring Scheduler
		ReservationMGR.checkExpiry(reservations);
		
		Scanner sc=new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println("\n----------------------");
			System.out.println("Main Menu");	
			System.out.println("----------------------");
			System.out.println("1. Food Menu UI");
			System.out.println("2. Reservation UI");
			System.out.println("3. Order UI");
			System.out.println("4. Save to file");
			System.out.println("Enter -1, to exit");
			System.out.println("----------------------");
			System.out.print("Please selection an option: ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				FoodMenuUI foodMenuUI = new FoodMenuUI(foodItems,promoItems);
				break;
			case 2:
				ReservationUI reservationUI = new ReservationUI(reservations, customers, tables);
				break;
			case 3:
				OrderUI orderUI = new OrderUI(foodItems, promoItems,orderList,staff,reservations, tables);
				break;
			case 4:
				FoodIOMGR.writeToFile(foodItems);
				PromoIOMGR.writeToFile(promoItems);
				ReservationIOMGR.writeToFile(reservations);
				TableIOMGR.writeToFile(tables);
				System.out.println("Saved!");
				break;
			case -1:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("That is not a valid choice!");
				break;
			}
		
		}while(choice != -1);
		
		
		FoodIOMGR.writeToFile(foodItems);
		PromoIOMGR.writeToFile(promoItems);
		StaffIOMGR.writeToFile(staff);
		CustIOMGR.writeToFile(customers);
		ReservationIOMGR.writeToFile(reservations);
		TableIOMGR.writeToFile(tables);
	}
}
