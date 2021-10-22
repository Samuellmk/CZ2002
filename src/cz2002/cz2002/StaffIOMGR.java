package cz2002;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StaffIOMGR {
	/*
	 * Method to read persistent data from file for staff
	 */
	public static ArrayList<Staff> readFromFile() {
		ArrayList<Staff> staff = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Staff.txt"));

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
				String tempName = tokenize[0];
				String tempGender = tokenize[1];
				String tempID = tokenize[2];
				String tempJobtitle = tokenize[3];

				// create temporary instance of Inventory object
				// and load with three data values
				Staff tempStaff= new Staff(tempName,tempGender,tempID,tempJobtitle);

				// add to array list
				staff.add(tempStaff);

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

		return staff;
	}

	/**
	 * Method to write persistent data to file for staff
	 * @param staff
	 */
	public static void writeToFile(ArrayList<Staff> staff) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Staff.txt", false));
			for(int i =0; i< staff.size();i++) {
				String output = staff.get(i).getName()+","+staff.get(i).getGender()+","+staff.get(i).getEmployeeID()+","+
						staff.get(i).getJobtitle();
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