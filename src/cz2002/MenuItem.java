package cz2002;

/*
 * Super class of Food and PromoPackage
 */

public class MenuItem {

	private String name;
	
	/** 
	* Class constructor.
	*/
	public MenuItem(String name) {
		this.name = name;
	}
	
	/*
	 * Getter method
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter method
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}