package cz2002;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the manager class that will handle all the require operations that are related to menu items
 * @author xingwei1
 *
 */
public class MenuMGR {

	/**
	 * Instantiate new Food and add to food item array list using call by reference
	 * @param name Name for new food item 
	 * @param description Description for new food item
	 * @param price Price for new food item
	 * @param type Type for new food item
	 * @param foodItems Food items list of the restaurant 
	 */
	public static void createFoodItem(String name, String description, double price, Type type,List<Food> foodItems) {
		
		Food newfood = new Food(name,type,description,price);
		foodItems.add(newfood);
	}

	/**
	 * Instantiate new PromoPackage and add to food item array list using call by reference
	 * @param name Name for new promo item
	 * @param foodItems Food items included in the promo item
	 * @param description Description for new promo item
	 * @param price Price for new promo item
	 * @param promoItems Promo items list of the restaurant
	 */
	public static void createPromoPackage(String name, ArrayList<Food> foodItems, String description, double price,List<PromoPackage> promoItems) {
		
		PromoPackage newpromo = new PromoPackage(name, foodItems, description,price);
		promoItems.add(newpromo);
	}

	/**
	 * 
	 * Update the specific item in the ArrayList of food items through call by reference
	 * 
	 * @param name Name to be updated
	 * @param description Description to be updated
	 * @param price Price to be updated
	 * @param type Type to be updated
	 * @param foodItems Food items list of the restaurant
	 * @param index Index of the item to be updated
	 */
	public static void updateFoodItem(String name, String description, double price, Type type,List<Food> foodItems, int index) {
		
		Food newfood = new Food(name,type,description,price);
		foodItems.set(index, newfood);

		
	}

	/**
	 * 
	 * Update the specific item in the ArrayList of promo items through call by reference
	 * @param name Name to be updated
	 * @param description Description to be updated
	 * @param price Price to be updated
	 * @param foodItems Food items to be updated
	 * @param promoItems Promo items list of the restaurant
	 * @param index Index of the item to be updated
	 */
	public static void updatePromoPackage(String name,String description, double price,ArrayList<Food> foodItems, List<PromoPackage> promoItems, int index) {
		
		PromoPackage newpromo = new PromoPackage(name, foodItems, description,price);
		promoItems.set(index, newpromo);

	}

	/**
	 * Remove the specific item in the ArrayList of food items through call by reference
	 * @param foodItems Food items list of the restaurant
	 * @param index Index of the item to be removed
	 * 
	 */
	public static boolean removeFoodItem(List<Food> foodItems, int index) {
		
		foodItems.remove(index);
		return true;
	}

	/**
	 * Remove the specific item in the ArrayList of promo items through call by reference
	 * @param promoItems Promo items list of the restaurant
	 * @param index Index of the item to be removed
	 * 
	 */
	public static boolean removePromoPackage(List<PromoPackage> promoItems, int index) {

		promoItems.remove(index);
		return true;
	}

}