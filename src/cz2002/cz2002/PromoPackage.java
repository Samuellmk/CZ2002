package cz2002;
import java.util.ArrayList;

/**
 * This is the promo package class which extends from menu item as it is a type of menu item in the restaurant
 * Each promopackage object will have its own name, list of food items included, description and price.
 * @author xingwei1
 *
 */
public class PromoPackage extends MenuItem {
	
	private ArrayList<Food> foodItems;
	private String description;
	private double price;
	
	/** 
	* Class constructor.
	* @param name Initialise name of promo item
	* @param foodItems Initialise food items of promo item
	* @param description Initialise description of promo item
	* @param price Initialise price of promo item
	*/
	public PromoPackage(String name, ArrayList<Food> foodItems, String description, double price) {
		super(name);
		this.foodItems = foodItems;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Getter method
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Setter method
	 * @param price New price of promo item
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Getter method
	 * @return This promo item description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter method
	 * @param description New description of promo item
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter method
	 * @return This promo item Food Items
	 */
	public ArrayList<Food> getFoodItems() {
		return this.foodItems;
	}

	/**
	 * Setter method
	 * @param foodItems New food items list of promo item
	 */
	public void setFoodItems(ArrayList<Food> foodItems) {
		this.foodItems = foodItems;
	}

}