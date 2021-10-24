package reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cz2002.Customer;
import table.TableSeats;

public class ReservationIOMGR {
	/*
	 * Method to read persistent data from file for customers test
	 */
	public static ArrayList<Reservation> readFromFile() {
		ArrayList<Reservation> reservationItems = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Reservation.txt"));

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
				String tempDateTime = tokenize[0];
				TableSeats tempPax = TableSeats.valueOf(tokenize[1]);
				String tempName = tokenize[2];
				String tempContact = tokenize[3];
				int tempTableNo = Integer.parseInt(tokenize[4]);
				boolean tempMembership = Boolean.parseBoolean(tokenize[5]);

				// create temporary instance of Inventory object
				// and load with three data values
				Reservation tempReserv = new Reservation(tempDateTime, tempPax, tempName,
						 tempContact, tempTableNo, tempMembership);

				// add to array list
				reservationItems.add(tempReserv);

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

		return reservationItems;
	}

	/**
	 * Method to write persistent data to file for customers
	 * @param customers
	 */
	public static void writeToFile(ArrayList<Reservation> reservationItem) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Reservation.txt", false));
			for(int i =0; i< reservationItem.size();i++) {
				Customer customer = reservationItem.get(i).getCustomer();
				String output = reservationItem.get(i).getDateTime()+","+reservationItem.get(i).getPax()+","
						+customer.getName()+","+customer.getContact()
						+","+reservationItem.get(i).getTableNo()+","+customer.getMembership();
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
