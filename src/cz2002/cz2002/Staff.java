package cz2002;

public class Staff {

	private String name;
	private String gender;
	private String employeeID;
	private String jobtitle;
	
	/** 
	* Class constructor.
	*/
	public Staff(String name, String gender, String employeeID, String jobtitle) {
		this.name = name;
		this.employeeID = employeeID;
		this.gender = gender;
		this.jobtitle = jobtitle;
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
	public String getGender() {
		return this.gender;
	}

	/**
	 *  Setter method
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/*
	 *Getter method 
	 */
	public String getEmployeeID() {
		return this.employeeID;
	}

	/**
	 *  Setter method
	 * @param employeeID
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	/*
	 *Getter method 
	 */
	public String getJobtitle() {
		return this.jobtitle;
	}

	/**
	 *  Setter method
	 * @param jobtitle
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

}