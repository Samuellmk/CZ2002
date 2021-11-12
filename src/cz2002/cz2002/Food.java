package cz2002;

/**
 * This is the food class which extends from menu item as it is a type of menu item in the restaurant
 * Each food object will have its own name, type, description and price.
 * @author xingwei1
 *
 */
public class Food extends MenuItem {

	private Type type;
	private String description;
	private double price;
	
	/** 
	* Class constructor.
	* @param name initialising the name of the menu item
	* @param type initialising the type of the food item
	* @param description initialising the description of the food item
	* @param price initialising the price of the food item
	*/
	public Food(String name, Type type, String description, double price) {
		super(name);
		this.type = type;
		this.description = description;
		this.price = price;
	}
	
	/*
	 *Getter method 
	 *
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Setter method
	 * @param type setting the type of the food to a new type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * Getter method
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter method
	 * @param description setting description to a new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 *Getter method 
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Setter method
	 * @param price setting price to a new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}