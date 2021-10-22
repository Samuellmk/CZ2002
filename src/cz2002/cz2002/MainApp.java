package cz2002;

import java.util.ArrayList;
import java.util.Scanner;

/*
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
		
		foodItems = FoodIOMGR.readFromFile();
		promoItems = PromoIOMGR.readFromFile();
		staff = StaffIOMGR.readFromFile();
		customers = CustIOMGR.readFromFile();
		
		
		Scanner sc=new Scanner(System.in);
		int choice = -1;
		do {
		System.out.println("----------------------");
		System.out.println("1. Food Menu UI");
		System.out.println("2. Reservation UI");
		System.out.println("3. Order UI");
		System.out.println("4. Save to file");
		System.out.println("To exit enter -1");
		System.out.println("----------------------");
		System.out.print("Please selection an option: ");
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			FoodMenuUI foodMenuUI = new FoodMenuUI(foodItems,promoItems);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			FoodIOMGR.writeToFile(foodItems);
			PromoIOMGR.writeToFile(promoItems);
			break;
		case -1:
			System.out.println("Exiting...");
			break;
		default:
			System.out.println("That is not a valid choice!");
			break;
		}
		
		}while(choice!=-1);
		
		
		FoodIOMGR.writeToFile(foodItems);
		PromoIOMGR.writeToFile(promoItems);
		StaffIOMGR.writeToFile(staff);
		CustIOMGR.writeToFile(customers);
	}
}
