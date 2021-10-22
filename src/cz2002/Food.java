package cz2002;

public class Food extends MenuItem {

	private Type type;
	private String description;
	private double price;
	
	/** 
	* Class constructor.
	*/
	public Food(String name, Type type, String description, double price) {
		super(name);
		this.type = type;
		this.description = description;
		this.price = price;
	}
	
	/*
	 *Getter method 
	 */
	public Type getType() {
		return this.type;
	}

	/**
	 * Setter method
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter method
	 * @param description
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
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}