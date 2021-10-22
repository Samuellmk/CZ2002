package cz2002;

import java.util.ArrayList;
import java.util.List;

public class MenuMGR {

	/**
	 * Instantiate new Food and add to food item array list using call by reference
	 * @param name
	 * @param description
	 * @param price
	 * @param type
	 * @param foodItems
	 */
	public static void createFoodItem(String name, String description, double price, Type type,List<Food> foodItems) {
		
		Food newfood = new Food(name,type,description,price);
		foodItems.add(newfood);
	}

	/**
	 * Instantiate new PromoPackage and add to food item array list using call by reference
	 * @param name
	 * @param foodItems
	 * @param description
	 * @param price
	 * @param promoItems
	 */
	public static void createPromoPackage(String name, ArrayList<Food> foodItems, String description, double price,List<PromoPackage> promoItems) {
		
		PromoPackage newpromo = new PromoPackage(name, foodItems, description,price);
		promoItems.add(newpromo);
	}

	/**
	 * 
	 * Update the specific item in the ArrayList of food items through call by reference
	 * 
	 * @param name
	 * @param description
	 * @param price
	 * @param type
	 * @param foodItems
	 * @param index
	 */
	public static void updateFoodItem(String name, String description, double price, Type type,List<Food> foodItems, int index) {
		
		Food newfood = new Food(name,type,description,price);
		foodItems.set(index, newfood);

		
	}

	/**
	 * 
	 * Update the specific item in the ArrayList of promo items through call by reference
	 * @param description
	 * @param price
	 * @param foodItems
	 * @param promoItems
	 * @param index
	 */
	public static void updatePromoPackage(String name,String description, double price,ArrayList<Food> foodItems, List<PromoPackage> promoItems, int index) {
		
		PromoPackage newpromo = new PromoPackage(name, foodItems, description,price);
		promoItems.set(index, newpromo);

	}

	/**
	 * Remove the specific item in the ArrayList of food items through call by reference
	 * @param foodItems
	 * @param index
	 * 
	 */
	public static boolean removeFoodItem(List<Food> foodItems, int index) {
		
		foodItems.remove(index);
		return true;
	}

	/**
	 * Remove the specific item in the ArrayList of promo items through call by reference
	 * @param promoItems
	 * @param index
	 */
	public static boolean removePromoPackage(List<PromoPackage> promoItems, int index) {

		promoItems.remove(index);
		return true;
	}

}