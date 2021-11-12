package cz2002;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This is the customer file input output manager responsible for storing persistent data for customer if necessary
 * @author xingwei1
 *
 */
public class CustIOMGR implements BaseIOMGR{
	/*
	 * Method to read persistent data from file for customers test 
	 */
	public ArrayList<Customer> readFromFile() {
		ArrayList<Customer> customers = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Customer.txt"));

			// read the first line from the text file
			String fileRead = br.readLine();

			// loop until all lines are read
			while (fileRead != null)
			{

				// use string.split to load a string array with the values from each line of
				// the file, using a comma as the delimiter
				String[] tokenize = fileRead.split(",");

				// assume file is made correctly
				// and make temporary variables for the three types of data
				boolean tempMember = Boolean.parseBoolean(tokenize[0]);
				String tempName = tokenize[1];
				String tempContact = tokenize[2];

				// create temporary instance of Inventory object
				// and load with three data values
				Customer tempCust = new Customer(tempMember,tempName,tempContact);

				// add to array list
				customers.add(tempCust);

				// read next line before looping
				// if end of file reached 
				fileRead = br.readLine();
			}

			// close file stream
			br.close();
		}
		
		// handle exceptions
		catch (FileNotFoundException fnfe)
		{
			System.out.println("file not found");
			return null;
		}

		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}

		return customers;
	}

	/**
	 * Method to write persistent data to file for customers
	 * @param customers Customer list of the restaurant
	 */
	public static void writeToFile(ArrayList<Customer> customers) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Customer.txt", false));
			for(int i =0; i< customers.size();i++) {
				String output = customers.get(i).getMembership()+","+customers.get(i).getName()+","+customers.get(i).getContact();
				out.write(output);
				out.newLine();
			}
			
			out.close();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

}