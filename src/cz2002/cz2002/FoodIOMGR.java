package cz2002;
import java.io.*;
import java.util.ArrayList;

public class FoodIOMGR implements BaseIOMGR {
	/*
	 * Method to read persistent data from file for Food Items
	 */
	public ArrayList<Food> readFromFile() {
		// create ArrayList to store the food objects
				ArrayList<Food> foodItems = new ArrayList<>();
				try
				{
					// create a Buffered Reader object instance with a FileReader
					BufferedReader br = new BufferedReader(new FileReader("Food.txt"));

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
						Type tempType = Type.valueOf(tokenize[1].toUpperCase());
						String tempDesc = tokenize[2];
						double tempPrice = Double.parseDouble(tokenize[3]);

						// create temporary instance of Inventory object
						// and load with three data values
						Food tempFood = new Food(tempName,tempType,tempDesc,tempPrice);

						// add to array list
						foodItems.add(tempFood);

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

				return foodItems;
	}

	/**
	 * Method to write persistent data to file for Food Items
	 * @param foodItems
	 */
	public static void writeToFile(ArrayList<Food> foodItems) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Food.txt", false));
			for(int i =0; i< foodItems.size();i++) {
				String output = foodItems.get(i).getName()+","+foodItems.get(i).getType()+","+foodItems.get(i).getDescription()+","+
						foodItems.get(i).getPrice();
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