package cz2002;

/**
 * The customer Class that will be used in Reservation ArrayList.
 * 
 * @author Samuel Leong
 */
public class Customer {

	private boolean membership;
	private String name;
	private String contact;
	
	/** 
	* Class constructor specifying the creation of customer object.
	* 
	* @param membership if customer has membership
	* @param name		the name of the customer
	* @param contact	the contact number of the customer
	*/
	public Customer(boolean membership, String name, String contact) {
		this.membership = membership;
		this.name = name;
		this.contact = contact;
	}
	
	/**
	 * Return the name of the customer.
	 * 
	 * @return the name of the specified customer
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setting the name to this customer Customer object.
	 *  
	 * @param name the name to be set to this Customer object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return the contact number of the customer.
	 * 
	 * @return the contact number of the specified customer
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * Setting the contact to this customer Customer object.
	 *  
	 * @param contact the contact to be set to this Customer object
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Return the membership boolean of the customer.
	 * 
	 * @return the membership boolean of the specified customer
	 */
	public boolean getMembership() {
		return this.membership;
	}

	/**
	 * Setting the membership to this customer Customer object.
	 *  
	 * @param membership the membership to be set to this Customer object
	 */
	public void setMembership(boolean membership) {
		this.membership = membership;
	}

}