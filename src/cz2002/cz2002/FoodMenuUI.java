package cz2002;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the Food Menu UI where the staff will interact with to create, edit, or remove any food or promopackage items in the restaurant
 * @author xingwei1
 *
 */
public class FoodMenuUI {

	public Scanner sc=new Scanner(System.in);
	
	/** 
	* Class constructor.
	* Items here will be modified through call by reference
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	*/
	public FoodMenuUI(List<Food> foodItems, List<PromoPackage> promoItems) {
		int choice = -1;
		do {
			displayUI();
			System.out.print("Choose an option: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				viewMenuItemsUI((ArrayList<Food>)foodItems,(ArrayList<PromoPackage>)promoItems);
				break;
			case 2:
				try {
					createNewFoodUI(foodItems);
				}catch(Exception e) {
					//e.printStackTrace();
					System.out.println("Error occured when typing in values!");
				}
				break;
			case 3:
				try {
					createPromoPackageUI(promoItems,(ArrayList<Food>)foodItems);
				}catch(Exception e) {
					//e.printStackTrace();
					System.out.println("Error occured when typing in values!");
				}
				
				break;
			case 4:
				try {
					updateFoodUI(foodItems);
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("Error occured when typing in values!");
				}
				
				break;
			case 5:
				try {
					updatePromoPackageUI(promoItems,(ArrayList<Food>) foodItems);
				}catch(Exception e) {
					//e.printStackTrace();
					System.out.println("Error occured when typing in values!");
				}
				
				break;
			case 6:
				removeFoodUI(foodItems);
				break;
			case 7:
				removePromoPackageUI(promoItems);
				break;
			case -1:
				System.out.println("Exiting Food Menu UI...");
				break;
			default:
				System.out.println("That is not a valid choice!");
				break;
			}
		}while(choice!=-1);
		
	}
	/**
	 * This method is for printing all the options that the staff can choose
	 */
	public void displayUI() {
		System.out.println("\n----------------------");
		System.out.println("Food Menu");	
		System.out.println("----------------------");
		System.out.println("1. View Menu Items");
		System.out.println("2. Create Food Item");
		System.out.println("3. Create Promo Package");
		System.out.println("4. Update Food Item");
		System.out.println("5. Update Promo Package");
		System.out.println("6. Remove Food Item");
		System.out.println("7. Remove Promo Package");
		System.out.println("----------------------");
		System.out.println("Enter -1, to return to Main Menu");
		
	}
	
	/** 
	* Method to print out the menu items from the restaurant
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	*/
	public static void viewMenuItemsUI(ArrayList<Food> foodItems,ArrayList<PromoPackage> promoItems) {
		viewFoodItemsUI(foodItems);
		viewPromoItemsUI(promoItems);
	}
	
	/** 
	* Method to view Food Items
	* @param foodItems the Promo Items from Main Applications
	*/
	public static void viewFoodItemsUI(ArrayList<Food> foodItems) {
		System.out.println("");
		System.out.println("Food Items: ");
		for (int i =0 ;i<foodItems.size();i++) {
		      System.out.println(i+1+". "+foodItems.get(i).getName()+" Desc: "+foodItems.get(i).getDescription()+" Type: "
		    		  +foodItems.get(i).getType()+" Price: "+
		    		  foodItems.get(i).getPrice());
		}
		
	}
	
	/** 
	* 
	* Method to view Promo Items 
	* @param promoItems the Promo Items from Main Applications
	*/
	public static void viewPromoItemsUI(ArrayList<PromoPackage> promoItems) {
		System.out.println("");
		System.out.println("Promo Items: ");
		for (int i =0 ;i<promoItems.size();i++) {
		      System.out.println(i+1+". "+promoItems.get(i).getName()
		    		  +" Desc: "+promoItems.get(i).getDescription()+" Price: "+promoItems.get(i).getPrice());
		      System.out.println("Items :");
		      System.out.print("[");
		      viewFoodItemsUI(promoItems.get(i).getFoodItems());
		      System.out.println("]");
		}
		
	}
	
	/** 
	* Method to create Promo Item for restaurant
	* Staff needs to enter the name, number of food items, select the food items to add, enter the description, and price
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	*/
	public void createPromoPackageUI(List<PromoPackage> promoItems,ArrayList<Food> foodItems) {
		
		if(foodItems.size()<1) {
			System.out.println("No Food Items available!");
			return;
		}
		
		ArrayList<Food> foodItems1 = new ArrayList<Food>();
		System.out.print("Enter name of Promo Package: ");
		String name = sc.next();
		System.out.print("Enter how many food items: ");
		int n = sc.nextInt();
		
		for(int i = 0; i<n ;i++) {
			viewFoodItemsUI(foodItems);
			System.out.print("Select food items to add: ");
			int choice = sc.nextInt();
			if(choice<=foodItems.size() && choice>=0)
				foodItems1.add(foodItems.get(choice-1));
			else {
				System.out.println("Choose a valid number!");
				i--;
			}
				
		}
		
		System.out.print("Enter description: ");
		sc.nextLine();
		String desc = sc.nextLine();
		System.out.print("Enter price: ");
		
		double price = sc.nextDouble();
		
		MenuMGR.createPromoPackage(name, foodItems1, desc, price, promoItems);
	}
	
	
	/** 
	* 
	* Method to create Food Item for Restaurant
	* Staff needs to enter the name of the food item, type (can only be Appetizer, drinks, maincourse, or dessert), description and price
	* @param foodItems the Promo Items from Main Applications
	*/
	public void createNewFoodUI(List<Food> foodItems) {

		System.out.print("Enter name of food item: ");
		String name = sc.next();
		System.out.print("Enter type of food: ");
		Type type = Type.valueOf(sc.next().toUpperCase());
		System.out.print("Enter description: ");
		sc.nextLine();
		String desc = sc.nextLine();
		System.out.print("Enter price: ");
		double price = sc.nextDouble();
		
		MenuMGR.createFoodItem(name, desc, price, type, foodItems);
	}
	
	/** 
	* 
	* Method to update Food Item for Restaurant
	* Staff can choose the food item to update, from the food item Staff can choose the attribute of the food item to update
	* @param foodItems the Food Items from Main Application
	* 
	*/
	public void updateFoodUI(List<Food> foodItems) {
		viewFoodItemsUI((ArrayList)foodItems);
		System.out.print("Select item to update: ");
		
		int choice = sc.nextInt();
		if(choice>foodItems.size() || choice<1) {
			System.out.println("Choose number within the list!");
			return;
		}
		
		String name = foodItems.get(choice-1).getName();
		Type type = foodItems.get(choice-1).getType();
		String desc = foodItems.get(choice-1).getDescription();
		double price = foodItems.get(choice-1).getPrice();
		
		int choice2; 
		do {
			System.out.println("What do you want to edit?");
			System.out.println("1. Name");
			System.out.println("2. Type");
			System.out.println("3. Description");
			System.out.println("4. Price");
			System.out.println("-1. Exit");
			System.out.print("Pick an attribute: ");
			choice2 = sc.nextInt();
			switch(choice2) {
			case 1:
				System.out.print("Enter name of food item: ");
				name = sc.next();
				break;
			case 2:
				System.out.print("Enter type of food: ");
				type = Type.valueOf(sc.next().toUpperCase());
				break;
			case 3:
				System.out.print("Enter description: ");
				sc.nextLine();
				desc = sc.nextLine();
				break;
			case 4:
				System.out.print("Enter price: ");
				price = sc.nextDouble();
				break;
			case -1:
				break;
			default:
				System.out.println("Please select a number within the list!");
				break;
			}
			MenuMGR.updateFoodItem(name, desc, price, type, foodItems, choice-1);
		}while(choice2!=-1);
		
		
		
		
	}
	
	
	/** 
	* 
	* Staff can choose the promo item to update, from the promo item Staff can choose the attribute of the promo item to update
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	*/
	public void updatePromoPackageUI(List<PromoPackage> promoItems,ArrayList<Food> foodItems) {
		viewPromoItemsUI((ArrayList)promoItems);
		System.out.print("Select item to update: ");
		int choice = sc.nextInt();
		if(choice>promoItems.size()|| choice<1) {
			System.out.println("Choose number within the list!");
			return;
		}
		String name = promoItems.get(choice-1).getName();
		ArrayList<Food> foodItems1 = promoItems.get(choice-1).getFoodItems();
		String desc = promoItems.get(choice-1).getDescription();
		double price = promoItems.get(choice-1).getPrice();
		
		int choice2; 
		do {
			System.out.println("What do you want to edit?");
			System.out.println("1. Name");
			System.out.println("2. Food Items");
			System.out.println("3. Description");
			System.out.println("4. Price");
			System.out.println("-1. Exit");
			System.out.print("Pick an attribute: ");
			choice2 = sc.nextInt();
			switch(choice2) {
			case 1:
				System.out.print("Enter name of Promo Package: ");
				name = sc.next();
				break;
			case 2:
				foodItems1 = new ArrayList<Food>();
				System.out.print("Enter how many food items: ");
				int n = sc.nextInt();
				for(int i = 0; i<n ;i++) {
					viewFoodItemsUI(foodItems);
					System.out.print("Select food items to add: ");
					int c = sc.nextInt();
					if(choice<=foodItems.size() && choice>=0)
						foodItems1.add(foodItems.get(choice-1));
					else {
						System.out.println("Choose a valid number!");
						i--;
					}
				}
				break;
			case 3:
				System.out.print("Enter description: ");
				sc.nextLine();
				desc = sc.nextLine();
				break;
			case 4:
				System.out.print("Enter price: ");
				price = sc.nextDouble();
				break;
			case -1:
				break;
			default:
				System.out.println("Please select a number within the list!");
				break;
			}
			MenuMGR.updatePromoPackage(name, desc, price, foodItems1, promoItems, choice-1);
		}while(choice2!=-1);
		
		
		
	}
	
	
	/** 
	* 
	* Staff can choose the Food item to be removed 
	* @param foodItems the Food Items from Main Application
	* 
	*/
	public void removeFoodUI(List<Food> foodItems) {
		viewFoodItemsUI((ArrayList)foodItems);
		System.out.print("Select item to delete: ");
		sc.nextLine();
		int choice = sc.nextInt();
		if(choice>foodItems.size()|| choice<1) {
			System.out.println("Choose number within the list!");
			return;
		}
		if(choice<=foodItems.size() && choice>=0)
		MenuMGR.removeFoodItem(foodItems, choice-1);
		else
			System.out.println("Choose a valid number!");
	}


	/** 
	* 
	* Staff can choose the Promo item to be removed 
	* @param promoItems the Promo Items from Main Applications
	*/
	public void removePromoPackageUI(List<PromoPackage> promoItems) {
		viewPromoItemsUI((ArrayList)promoItems);
		System.out.print("Select item to delete: ");
		sc.nextLine();
		int choice = sc.nextInt();
		if(choice>promoItems.size()|| choice<1) {
			System.out.println("Choose number within the list!");
			return;
		}
		if(choice<=promoItems.size() && choice>=0)
		MenuMGR.removePromoPackage(promoItems, choice-1);
		else
			System.out.println("Choose a valid number!");
	}
	

}