package cz2002;

import java.util.ArrayList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import invoice.Invoice;
import invoice.InvoiceIOMGR;
import invoice.InvoiceMGR;
import reservation.Reservation;
import table.Table;
import table.TableMGR;


/**
 * This is the Order UI where the staff will take order, check order, edit order, cancel order and make payments for the customer.
 * @author JunHeng
 *
 */

public class OrderUI {
	
	/**
	 * Scanner for user input
	 */
	public Scanner sc = new Scanner(System.in);
	
	/** 
	* Class constructor.
	* Items here will be modified through call by reference
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	* @param orderList List of existing orders to loop through and find matching tableno order
	* @param staff List of the staff working currently
	* @param reservations List of reservations made for all the tables in the restaurant
	* @param tables List of all the tables in the restaurant
	* @param invoices List of all the invoices paid for 
	*/
	public OrderUI(ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, List<Order> orderList, ArrayList<Staff> staff,List<Reservation> reservations, List<Table> tables,List<Invoice> invoices)
	{
		for(int b=0; b<staff.size(); b++)
		{
			System.out.println(b+1 + "):" + staff.get(b).getName());
		}
		
		System.out.println("Which staff no. is taking order");
		int totalStaff = staff.size();
		int staffno = sc.nextInt();		
		if((staffno-1>=totalStaff) || staffno<0)
		{
			System.out.println("Please enter a valid staff ID!");
			return;
		}
		Staff orderstaff = staff.get(staffno-1);
		
		
		int choice = -1;
		do {
			displayUI();
			System.out.println("Pick a task: ");
			choice = sc.nextInt();
			switch(choice) 
			{
			case 1:
				showOrderDetails(orderList);
				break;				
			case 2:
				takeOrder(orderstaff, orderList, foodItems, promoItems,reservations,tables);
				break; 
				
			case 3:
				editOrder(orderstaff, foodItems, promoItems,orderList,tables);
				break;
			case 4:
				payment(orderList,invoices,tables);
				break;
				
			case -1:
				System.out.println("Exiting...");
				break;	
				
			default:
				System.out.println("Please select a number within the list!");
				break;
	
			}
		}while(choice != -1);
	}
	
	/**
	 * This method is for printing all the options that the staff can choose
	 */
	public void displayUI() {
		System.out.println("");
		System.out.println("1. Show Order Details");
		System.out.println("2. Take Order");
		System.out.println("3. Edit Order");
        System.out.println("4. Pay Order");
		System.out.println("To Exit enter -1");
		
	}
	
	/** 
	* Method to print out the orders made and price of the orderitems from the given tableno
	* Staff needs to enter tableno 
	* @param orderList List of existing orders to loop through and find matching tableno order
	*/
	public void showOrderDetails(List<Order> orderList) 
	{
		int tableno;
		System.out.println("Which table order would you like to SHOW?");
		tableno = sc.nextInt();
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno) //Check if existing orders got a order with table no = what you want to show
			{
				OrderMGR.printOrder(tableno, orderList);
				break;
			}		
			
		}
		if(i>=orderList.size()) 
		{
			System.out.println("No existing order to show!");
		}
	}
	
	
	/** 
	* Method to take new order and add it to orderList
	* Staff needs to enter tableno 
	* @param orderstaff staff taking the order is being recorded 
	* @param orderList List of existing orders to loop through and find matching tableno order
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	* @param reservations list of reservations made, can only take order when there is a reservation made for the given tableno 
	* @param tables list of all the tables in the restaurant, to set it to occupied once an order has been made by the given tableno
	* 
	*/
    public void takeOrder(Staff orderstaff,List<Order> orderList, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems,List<Reservation> reservations,List<Table> tables)
    {
        int i; 
        Customer customer = null;
        System.out.println("Which TABLE NUMBER are you taking order for?");
        int tableno = sc.nextInt();
        
        for(i=0; i<orderList.size(); i++)
        {
            if(orderList.get(i).getTableno() == tableno)
            {
                System.out.println("There is an existing order for this table already!");
                System.out.println("Use Edit Order function instead!"); 
                return;
            }
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for(i=0; i<reservations.size(); i++)
        {
            if(reservations.get(i).getTableNo() == tableno)
            {
            	LocalDateTime rsvtimeStart = LocalDateTime.parse(reservations.get(i).getDateTime(), formatter);
                LocalDateTime dateTimeNow = LocalDateTime.now();           
            	Duration duration = Duration.between(rsvtimeStart,dateTimeNow);
          
            	if(duration.toMinutes() <= 15 && duration.toMinutes()>=0)
            	{
            		customer = reservations.get(i).getCustomer();
            		break;
            	}
            }
        } 
        
        if(i>=reservations.size())
        {
        	System.out.println("No reservation for this table! Please try taking order for other tables instead!");
        	return;
        }       

        FoodMenuUI.viewMenuItemsUI(foodItems,promoItems); //Print menus for user to choose items
        
        int oChoice;
        OrderMGR.createOrder(orderstaff, tableno, false, orderList, customer);
        TableMGR.setTableOccupancy(tables, tableno, true);
        do
        {
            System.out.println("Pick a task: ");
            System.out.println("1. Add Item From Food Menu");
            System.out.println("2. Add Item From Promotional Menu");
            System.out.println("To EXIT Enter -1");

            oChoice = sc.nextInt();
            switch(oChoice)
            {
                case 1:
                    addF(orderstaff,foodItems,orderList, tableno);
                    break;				
                case 2:
                    addP(orderstaff, promoItems,orderList, tableno);
                    break;
                    
                case -1:
    				System.out.println("Exiting...");
    				break;    

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(oChoice != -1);
    }
    
    /** 
	* Method to edit the order of the given tableno from the list of existing orders
	* Staff needs to enter tableno
	* @param orderstaff staff taking the order is being recorded 
	* @param orderList List of existing orders to loop through and find matching tableno order
	* @param foodItems the Food Items from Main Application
	* @param promoItems the Promo Items from Main Applications
	* @param tables list of tables in the restaurant
	*/
    public void editOrder(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, List<Order> orderList,List<Table> tables)
    {
        int i;
        System.out.println("Which TABLE NUMBER are you editing for?");
        int tableno = sc.nextInt();
        for(i=0; i<orderList.size(); i++)
        {
            if(orderList.get(i).getTableno() == tableno)
            {
                break;
            }
        }
        
        if(i>=orderList.size())
        {
        	System.out.println("No existing orders for this table yet! Please take new order first!");
        	return;
        }

        OrderMGR.printOrder(tableno,orderList);

        int eChoice;
        do
        {
        	for(i=0; i<orderList.size(); i++)
            {
                if(orderList.get(i).getTableno() == tableno)
                {
                    break;
                }
            }
        	if(i>=orderList.size())
            {
        		System.out.println("No existing orders for this table yet! Please take new order first!");
              	return;
            }
            editOrderUI();
            System.out.println("Pick a task: ");
            eChoice = sc.nextInt();
            switch(eChoice)
            {
                case 1:
                    addOrderItemUI(orderstaff, foodItems, promoItems,orderList, tableno);
                    break;				
                case 2:
                    removeOrderItemUI(orderList, tableno);
                    break; 
                    
                case 3:
                    cancelOrderUI(orderList, tableno,tables);
                    break;
                    
                case -1:
    				System.out.println("Exiting...");
    				break;     
                    
                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(eChoice != -1);
    }
    
    /** 
   	* Method to make payment for the given tableno
   	* Staff needs to choose and enter the index of their order in the orderList to pay for
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param invoices list of all the invoices for the payments made
   	* @param tables list of tables in the restaurant
   	*/
    public void payment(List<Order> orderList, List<Invoice> invoices, List<Table> tables){
    	if(orderList.size()==0)
    	{
    		System.out.println("No existing orders to pay for!!!");
    		return;
    	}
        System.out.println("Which order would you like to pay for?");
        for(int j=0;j<orderList.size();j++) {
        	System.out.println(j+1+")"+" Staff: "+orderList.get(j).getStaff().getName()+" | Table No: "+orderList.get(j).getTableno()+" | Customer Name: "+
        orderList.get(j).getCustomer().getName());
        	/*for(int k=0;k<orderList.get(j).getOrderItems().size();k++) {
        		System.out.println(orderList.get(j).getOrderItems().get(k).getName()+" "+"Price "+
        	orderList.get(j).getOrderItems().get(k).getPrice());
        	}*/
        }
        int oIndex = sc.nextInt();
        if(oIndex>orderList.size() || oIndex<0)
        {
        	System.out.println("Please enter a valid order index!");
        	return;
        }
        Order tempOrder;
        tempOrder = orderList.get(oIndex-1);
        InvoiceMGR.createInvoice(tempOrder, invoices, tables);
   
    }


    /**
	 * This method is for printing all the options that the staff can choose to edit order
	 */
    public void editOrderUI()
    {
        System.out.println("Select Choice...");
        System.out.println("1. Add Order Item");
        System.out.println("2. Remove Order Item");
        System.out.println("3. Cancel Existing Order");
        System.out.println("To EXIT Enter -1");
    }

    
    /** 
   	* Method to choose to add items from Food Menu or Promo Menu to existing order 
   	* Staff have to enter the index of the choice of action
   	* @param orderstaff staff taking the order is being recorded 
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param foodItems the Food Items from Main Application
   	* @param promoItems the Promo Items from Main Applications
   	* @param tableno tableno of the table that wants to make this amendment of order 
   	*/
    public void addOrderItemUI(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, List<Order> orderList, int tableno)
    {
        FoodMenuUI.viewMenuItemsUI(foodItems,promoItems); //Print menus for user to choose items
        int addChoice;

        do
        {
            System.out.println("Select Choice...");
            System.out.println("1. Add Item From Food Menu");
            System.out.println("2. Add Item From Promotional Menu");
            System.out.println("To EXIT Enter -1");
            addChoice = sc.nextInt();
            switch(addChoice)
            {
                case 1:
                    addF(orderstaff,foodItems,orderList,tableno);
                    break;				
                case 2:
                    addP(orderstaff,promoItems,orderList,tableno);
                    break; 
                    
                case -1:
    				System.out.println("Exiting...");
    				break;    

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(addChoice != -1);

    }

    /** 
   	* Method to add items from the food menu and choose the quantity to add to existing order 
   	* Staff have to enter 
   	* @param orderstaff staff taking the order is being recorded 
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param foodItems the Food Items from Main Application
   	* @param tableno tableno of the table that wants to make this amendment of order 
   	*/
    public void addF(Staff orderstaff, ArrayList<Food> foodItems, List<Order> orderList, int tableno) 
    {
    	List<MenuItem> orderItems = new ArrayList<MenuItem>();
    	System.out.println("Enter Food Item NO. please...");
        int fIndex = sc.nextInt();
        if(fIndex>foodItems.size() || fIndex<0)
        {
        	System.out.println("Please enter a valid Food Item NO.!");
        	return;
        }
        System.out.println("Enter quantity please...");
        int q = sc.nextInt();
        if(q<1) 
        {
        	System.out.println("Pleae enter a valid quantity!");
            return;
        }
                             
        for(int i=0;i<q;i++)
        {
        	orderItems.add(foodItems.get(fIndex-1));
        }
                
        OrderMGR.addOrderItem(orderstaff, orderItems, tableno, orderList);
    }
    
    
    /** 
   	* Method to add items from the promo menu and choose the quantity to add to existing order 
   	* Staff have to enter the index of the menu item in the menu that the customer wants to add
   	* @param orderstaff staff taking the order is being recorded 
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param promoItems the Promo Items from Main Application
   	* @param tableno tableno of the table that wants to make this amendment of order 
   	*/
    public void addP(Staff orderstaff,ArrayList<PromoPackage> promoItems, List<Order> orderList, int tableno) 
    {
    	List<MenuItem> orderItems = new ArrayList<MenuItem>();
    	System.out.println("Enter Promotional Item NO. please...");
    	int pIndex = sc.nextInt();
    	if(pIndex>promoItems.size() || pIndex<0)
        {
    		System.out.println("Please enter a valid Food Item NO.!");
         	return;
        }
        System.out.println("Enter quantity please...");
        int q = sc.nextInt();
        if(q<1) 
        {
        	System.out.println("Pleae enter a valid quantity!");
            return;
        }
                            
        for(int i=0;i<q;i++)
        {
        	orderItems.add(promoItems.get(pIndex-1));
        }
                 
        OrderMGR.addOrderItem(orderstaff, orderItems, tableno, orderList);
    }

    /** 
   	* Method to remove existing order items from the order of the given tableno and index of the orderitem in the order
   	* Staff have to enter the index of the menu item in the menu that the customer wants to add
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param tableno tableno of the table that wants to make this amendment of order 
   	*/
    public void removeOrderItemUI(List<Order> orderList, int tableno)
    {
    	int oIndex;
   
        for(oIndex = 0; oIndex<orderList.size(); oIndex++)
        {
        	if(orderList.get(oIndex).getTableno() == tableno)
        	{
        		OrderMGR.printOrder(tableno, orderList);
            	System.out.println("which item INDEX of the order do you want to DELETE?");
             	int itemIndex = sc.nextInt();
            	if(itemIndex>orderList.get(oIndex).getOrderItems().size() || itemIndex<0)
                {
            		System.out.println("Please enter a valid index NO.! Try again...");
                 	return;
                }
                                         
                OrderMGR.removeOrderItem(oIndex, itemIndex, orderList,tableno);
            	return;
          	}
        }
        
    	System.out.println("No existing order yet! Nothing to remove!");
    	return;
    	
       
    }
    
    /** 
   	* Method to cancel existing order for the given tableno 
   	* @param orderList List of existing orders to loop through and find matching tableno order
   	* @param tableno tableno of the table that wants to make this amendment of order 
   	* @param tables list of tables in the restaurant
   	*/
    public void cancelOrderUI(List<Order> orderList, int tableno,List<Table> tables)
    {
    	
    	int oIndex;
        for(oIndex = 0; oIndex<orderList.size(); oIndex++)
        {
        	if(orderList.get(oIndex).getTableno() == tableno)
        	{
        		OrderMGR.cancelOrder(orderList,tableno);
    	    	TableMGR.setTableOccupancy(tables, tableno, false);		
    	    	System.out.println("Order Cancelled!");
    	    	return;
        	}
        }
        
    	System.out.println("No existing order yet! Nothing to cancel!");
    	return;
    	
    	

    }
	
}	
