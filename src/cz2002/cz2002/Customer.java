package cz2002;

public class Customer {

	private boolean membership;
	private String name;
	private String contact;
	
	/** 
	* Class constructor.
	*/
	public Customer(boolean membership, String name, String contact) {
		this.membership = membership;
		this.name = name;
		this.contact = contact;
	}
	
	/*
	 *Getter method 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *  Setter method
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 *Getter method 
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 *  Setter method
	 * @param contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/*
	 *Getter method 
	 */
	public boolean getMembership() {
		return this.membership;
	}

	/**
	 *  Setter method
	 * @param membership
	 */
	public void setMembership(boolean membership) {
		this.membership = membership;
	}

}