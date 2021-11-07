package invoice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cz2002.Food;
import cz2002.MenuItem;
import cz2002.PromoPackage;
import cz2002.Type;
import invoice.Invoice;

public class InvoiceIOMGR {
	/*
	 * Method to read persistent data from file for customers test 
	 */
	public static ArrayList<Invoice> readFromFile() {
		ArrayList<Invoice> invoices = new ArrayList<>();
		try
		{
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("Invoice.txt"));

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
				//boolean tempMember = Boolean.parseBoolean(tokenize[0]);
				//String tempName = tokenize[1];
				//String tempContact = tokenize[2];

				// create temporary instance of Inventory object
				// and load with three data values
				//Customer tempCust = new Customer(tempMember,tempName,tempContact);
				String tempEmpID = tokenize[0];
				int tempTableno = Integer.parseInt(tokenize[1]);
				String tempTimestamp = tokenize[2];
				double tempServicechrg = Double.parseDouble(tokenize[3]);
				double tempGST = Double.parseDouble(tokenize[4]);
				boolean tempDiscount = Boolean.parseBoolean(tokenize[5]);
				double tempTotal = Double.parseDouble(tokenize[6]);	
				int numOrderItems = Integer.parseInt(tokenize[7]);

				ArrayList<MenuItem> tempOrderItems= new ArrayList<MenuItem>();
				int i = 8;
				for(i=8;i<8+numOrderItems*5;i+=5){
					if(tokenize[i].equals('f')){
						String tempName = tokenize[i+1];
						Type tempType = Type.valueOf(tokenize[i+2].toUpperCase());
						String tempDesc = tokenize[i+3];
						double tempPrice = Double.parseDouble(tokenize[i+4]);
						Food tempFood = new Food(tempName,tempType,tempDesc,tempPrice);
						tempOrderItems.add(tempFood);
					}
					else{
						String tempName = tokenize[i+1];
						int tempSizePromo = Integer.parseInt(tokenize[i+2]);
						String tempDesc = tokenize[i+3];
						double tempPrice = Double.parseDouble(tokenize[i+4]);
						ArrayList<Food> tempFoods= new ArrayList<Food>();
						for(int j=i+5;j<i+5+(tempSizePromo*4);j+=4){
							String tempFoodName = tokenize[j];
							Type tempFoodType = Type.valueOf(tokenize[j+1].toUpperCase());
							String tempFoodDescription = tokenize[j+2];
							double tempFoodPrice = Double.parseDouble(tokenize[j+3]);
							Food tempFood = new Food(tempFoodName,tempFoodType,tempFoodDescription,tempFoodPrice);
							tempFoods.add(tempFood);
						}
						PromoPackage tempPromoPackage = new PromoPackage(tempName, tempFoods, tempDesc, tempPrice);
						tempOrderItems.add(tempPromoPackage);
					}
				}
				// add to array list
				Invoice tempInvoice = new Invoice(tempEmpID,tempOrderItems,tempTableno,tempTimestamp,tempServicechrg,tempGST,tempDiscount,tempTotal);
				invoices.add(tempInvoice);

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

		return invoices;
	}

	/**
	 * Method to write persistent data to file for customers
	 * @param customers
	 */
	public static void writeToFile(ArrayList<Invoice> invoices) {
		try
		{
			// create Bufferedwriter instance with a FileWriter
			// the flag set to 'false' tells it to override a file if file exists
			BufferedWriter out = new BufferedWriter(new FileWriter("Invoice.txt", false));
			for(int i =0; i< invoices.size();i++) {
				//EmpID,tableNo,timestamp,servicechrg,GST,discount,total,number of order items
				String output = invoices.get(i).getEmployeeID()+","+invoices.get(i).getTableno()+","+invoices.get(i).getTimestamp()+","+
				invoices.get(i).getServicechrg()+","+invoices.get(i).getGST()+","+invoices.get(i).getDiscount()+","+invoices.get(i).getTotal()+","+invoices.get(i).getOrderItems().size();
				for(int j = 0;j<invoices.get(i).getOrderItems().size();j++){
					if(invoices.get(i).getOrderItems().get(j) instanceof Food){
						Food temp = (Food)invoices.get(i).getOrderItems().get(j);
						//food,name,type,desc,price
						output = output+","+"f,"+temp.getName()+","+temp.getType()+","+temp.getDescription()+","
						+invoices.get(i).getOrderItems().get(j).getPrice();
					}
					else if(invoices.get(i).getOrderItems().get(j) instanceof PromoPackage){
						PromoPackage temp = (PromoPackage)invoices.get(i).getOrderItems().get(j);
						//promo,name,sizeofpromo,desc,price
						output = output + ","+"p,"+temp.getName()+","+temp.getFoodItems().size()+","+temp.getDescription()+","+temp.getPrice();
						for(int k = 0;k < temp.getFoodItems().size();k++){
							//food name, food type, food description, food price
							output = output + ","+temp.getFoodItems().get(k).getName()+","+temp.getFoodItems().get(k).getType()+","+temp.getFoodItems().get(k).getDescription()+","+temp.getFoodItems().get(k).getPrice();	
						}
					}
				}
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