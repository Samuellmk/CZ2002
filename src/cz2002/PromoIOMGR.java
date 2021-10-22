package cz2002;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PromoIOMGR {
	
	/*
	 * Method to read persistent data from file for Promo Items
	 */
	public static ArrayList<PromoPackage> readFromFile() {
		// create ArrayList to store the food objects
		ArrayList<PromoPackage> promoItems = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Promos.txt"));

			// read the first line from the text file
			String fileRead = br.readLine();

			// loop until all lines are read
			while (fileRead != null)
			{
				int i;
				// use string.split to load a string array with the values from each line of
				// the file, using a comma as the delimiter
				String[] tokenize = fileRead.split(",");

				// assume file is made correctly
				// and make temporary variables for the three types of data
				String tempName = tokenize[0];
				int tempNoOfFood = Integer.parseInt(tokenize[1]);
				ArrayList<Food> tempFoodItems = new ArrayList<Food>();
				for(i = 2;i<2+4*tempNoOfFood;i+=4 ) {
					String tempFoodName = tokenize[i];
					Type tempFoodType = Type.valueOf(tokenize[i+1].toUpperCase());
					String tempFoodDesc = tokenize[i+2];
					double tempFoodPrice = Double.parseDouble(tokenize[i+3]);
					Food tempFood = new Food(tempName,tempFoodType,tempFoodDesc,tempFoodPrice);
					tempFoodItems.add(tempFood);
				}
				String tempDesc = tokenize[i];
				double tempPrice = Double.parseDouble(tokenize[i+1]);

				// create temporary instance of Inventory object
				// and load with three data values
				PromoPackage tempPromo = new PromoPackage(tempName,tempFoodItems,tempDesc,tempPrice);

				// add to array list
				promoItems.add(tempPromo);

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

		return promoItems;
	}

	/**
	 * Method to write persistent data to file for Promo Items
	 * @param promos
	 */
	public static void writeToFile(ArrayList<PromoPackage> promos) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'true' tells it to append a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Promos.txt", false));
			for(int i =0; i< promos.size();i++) {
				
				String foodItems = new String();
				
				for(int j = 0 ; j < promos.get(i).getFoodItems().size();j++) {
					
					foodItems += ","+promos.get(i).getFoodItems().get(j).getName()+","+promos.get(i).getFoodItems().get(j).getType()+","+
				promos.get(i).getFoodItems().get(j).getDescription()+","+promos.get(i).getFoodItems().get(j).getPrice();
				}
				
				
				String output = promos.get(i).getName()+","+promos.get(i).getFoodItems().size()+foodItems
						+","+promos.get(i).getDescription()+","+promos.get(i).getPrice();
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