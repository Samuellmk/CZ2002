package table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cz2002.BaseIOMGR;

/**
 * This class is the Table IO manager, it is responsible for storing and retrieving persistent data from the Table.txt file.
 * @author Samuel Leong
 *
 */
public class TableIOMGR implements BaseIOMGR {
	/*
	 * Method to read persistent data from file for tables
	 */
	public ArrayList<Table> readFromFile() {
		ArrayList<Table> tableItems = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Table.txt"));

			// read the first line from the text file
			String fileRead = br.readLine();

			// loop until all lines are read
			while (fileRead != null)
			{

				// use string.split to load a string array with the values from each line of
				// the file, using a comma as the delimiter
				String[] tokenize = fileRead.split(",");

				// assume file is made correctly
				// and make temporary variables for the five types of data
				int tempTableNo = Integer.parseInt(tokenize[0]);
				TableSeats tempCapacity = TableSeats.valueOf(tokenize[1]);
				boolean tempReserved = Boolean.parseBoolean(tokenize[2]);

				// create temporary instance of Inventory object
				// and load with three data values
				Table tempTable = new Table(tempTableNo, tempCapacity, tempReserved);

				// add to array list
				tableItems.add(tempTable);

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

		return tableItems;
	}

	/**
	 * Method to write persistent data to file for Tables
	 * @param tableItem 	Table items list of the restaurant
	 */
	public static void writeToFile(ArrayList<Table> tableItem) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Table.txt", false));
			for(int i =0; i< tableItem.size();i++) {
				String output = tableItem.get(i).getTableNo()+","+tableItem.get(i).getCapacity()+","
						+tableItem.get(i).isOccupied();
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

