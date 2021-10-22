package cz2002;
import java.util.ArrayList;

public class PromoPackage extends MenuItem {
	
	private ArrayList<Food> foodItems;
	private String description;
	private double price;
	
	/** 
	* Class constructor.
	*/
	public PromoPackage(String name, ArrayList<Food> foodItems, String description, double price) {
		super(name);
		this.foodItems = foodItems;
		this.description = description;
		this.price = price;
		// TODO Auto-generated constructor stub
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Food> getFoodItems() {
		return this.foodItems;
	}

	/**
	 * 
	 * @param foodItems
	 */
	public void setFoodItems(ArrayList<Food> foodItems) {
		this.foodItems = foodItems;
	}

}