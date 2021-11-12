package cz2002;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import invoice.Invoice;
import invoice.InvoiceIOMGR;
import invoice.InvoiceMGR;
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
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		
		FoodIOMGR foodIOMGR = new FoodIOMGR();
		PromoIOMGR promoIOMGR = new PromoIOMGR();
		StaffIOMGR staffIOMGR = new StaffIOMGR();
		CustIOMGR custIOMGR = new CustIOMGR();
		ReservationIOMGR reservationIOMGR = new ReservationIOMGR();
		TableIOMGR tableIOMGR = new TableIOMGR();
		InvoiceIOMGR invoiceIOMGR = new InvoiceIOMGR();
		
		foodItems = foodIOMGR.readFromFile();
		promoItems = promoIOMGR.readFromFile();
		staff = staffIOMGR.readFromFile();
		customers = custIOMGR.readFromFile();
		reservations = reservationIOMGR.readFromFile();
		tables = tableIOMGR.readFromFile();
		invoices = invoiceIOMGR.readFromFile();
		
		// Setting up Reservation Expiring Scheduler
		ReservationMGR.checkExpiry(reservations, orderList, invoices, tables);
		
		Scanner sc=new Scanner(System.in);
		int choice = -1;
		do {
			System.out.println("----------------------");
			System.out.println("Main Menu");	
			System.out.println("----------------------");
			System.out.println("1. Food Menu UI");
			System.out.println("2. Reservation UI");
			System.out.println("3. Order UI");
			System.out.println("4. Print Revenue Report");
			System.out.println("5. Save to file");
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
				OrderUI orderUI = new OrderUI(foodItems, promoItems,orderList,staff,reservations, tables,invoices);
				break;
			case 4:
				System.out.println("1. Monthly");
				System.out.println("2. Daily");
				int revenueChoice = sc.nextInt();
				String dummy = sc.nextLine();
				if(revenueChoice==1){
					try{
						System.out.println("Enter period in mm/yyyy format:");
						String timestamp = sc.nextLine();
						int len = timestamp.length();
						int m = Integer.parseInt(timestamp.substring(0,2));
						int y = Integer.parseInt(timestamp.substring(3,7));
						if(len==7 && m>=1 && m<=12 && y>=2000 && y<=3000)
							InvoiceMGR.printSalesRevenueReport("month",invoices,timestamp,foodItems,promoItems);
						else
							System.out.println("Invalid Date");
					}
					catch(NumberFormatException e){
						System.out.println("Invalid Input");
					}
				}
				else if(revenueChoice==2){
					try{
						System.out.println("Enter period in dd/mm/yyyy format:");
						String timestamp = sc.nextLine();
						int len = timestamp.length();
						int d = Integer.parseInt(timestamp.substring(0,2));
						int m = Integer.parseInt(timestamp.substring(3,5));
						int y = Integer.parseInt(timestamp.substring(6,10));
						if(len==10 && d>=1 && d<=31 && m>=1 && m<=12 && y>=2000 && y<=3000)
							InvoiceMGR.printSalesRevenueReport("day",invoices,timestamp,foodItems,promoItems);
						else
							System.out.println("Invalid Date");
					}
					catch(NumberFormatException e){
						System.out.println("Invalid Input");
					}
				}
				else{
					System.out.println("Invalid Choice");
				}
				break;
			case 5:
				FoodIOMGR.writeToFile(foodItems);
				PromoIOMGR.writeToFile(promoItems);
				ReservationIOMGR.writeToFile(reservations);
				TableIOMGR.writeToFile(tables);
				InvoiceIOMGR.writeToFile(invoices);
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
		InvoiceIOMGR.writeToFile(invoices);
	}
}
