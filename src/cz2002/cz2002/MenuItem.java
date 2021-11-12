package cz2002;

/**
 * Super class of Food and PromoPackage
 * @author xingwei1
 *
 */
public abstract class MenuItem {

	private String name;
	
	/** 
	* Class constructor.
	* @param name initialising the name of the menu item
	*/
	public MenuItem(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method
	 * @return This menu item name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter method
	 * @param name setting the name of the menu item with a new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Abstract method for get price that food and promopackage needs to inherit
	 * @return This menu item price
	 */
	public abstract double getPrice();
	
	
}