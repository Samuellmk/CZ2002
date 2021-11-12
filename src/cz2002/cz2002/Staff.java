package cz2002;

/**
 * This is the Staff for the restaurant
 * Each staff has their own name, gender, employeeID and jobtitle
 * @author xingwei1
 *
 */
public class Staff {

	private String name;
	private String gender;
	private String employeeID;
	private String jobtitle;
	
	/** 
	* Class constructor.
	* @param name Initialise name of staff
	* @param gender Initialise gender of staff
	* @param employeeID Initialise employeeID of staff
	* @param jobtitle Initialise jobtitle of staff
	*/
	public Staff(String name, String gender, String employeeID, String jobtitle) {
		this.name = name;
		this.employeeID = employeeID;
		this.gender = gender;
		this.jobtitle = jobtitle;
	}
	
	/**
	 *Getter method 
	 *@return This staff name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *  Setter method
	 * @param name New name for staff
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 *Getter method 
	 *@return This staff gender
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 *  Setter method
	 * @param gender New gender for staff
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 *Getter method 
	 *@return This staff employeeID
	 */
	public String getEmployeeID() {
		return this.employeeID;
	}

	/**
	 *  Setter method
	 * @param employeeID New employeeID for staff
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	/**
	 *Getter method 
	 *@return This staff jobtitle
	 */
	public String getJobtitle() {
		return this.jobtitle;
	}

	/**
	 *  Setter method
	 * @param jobtitle New jobtitle for staff
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

}