package cz2002;

import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import reservation.Reservation;
import table.Table;

public class OrderUI {
	

	public Scanner sc = new Scanner(System.in);

	public OrderUI(ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, List<Order> orderList, ArrayList<Staff> staff,List<Reservation> reservations, List<Table> tables)
	{
		for(int b=0; b<staff.size(); b++)
		{
			System.out.println(b+1 + "):" + staff.get(b).getName());
		}
		
		System.out.println("Which staff no. is taking order");
		int staffno = sc.nextInt();
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
				takeOrder(orderstaff, orderList, foodItems, promoItems,reservations);
				break; 
				
			case 3:
				editOrder(orderstaff, foodItems, promoItems,orderList);
				break;
				
			default:
				System.out.println("Please select a number within the list!");
				break;
	
			}
		}while(choice != -1);
	}
	
	public void displayUI() {
		System.out.println("");
		System.out.println("1. Show Order Details");
		System.out.println("2. Take Order");
		System.out.println("3. Edit Order");
		System.out.println("To Exit enter -1");
		
	}
	

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
	
    public void takeOrder(Staff orderstaff,List<Order> orderList, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems,List<Reservation> reservations)
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

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(oChoice != -1);
    }

    public void editOrder(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, List<Order> orderList)
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
                    cancelOrderUI(orderList, tableno);
                    break;
                    
                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(eChoice != -1);
    }



    public void editOrderUI()
    {
        System.out.println("Select Choice...");
        System.out.println("1. Add Order Item");
        System.out.println("2. Remove Order Item");
        System.out.println("3. Cancel Existing Order");
        System.out.println("To EXIT Enter -1");
    }


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

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(addChoice != -1);

    }


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


    public void removeOrderItemUI(List<Order> orderList, int tableno)
    {
        OrderMGR.printOrder(tableno, orderList);
    	System.out.println("which item INDEX of the order do you want to DELETE?");
        int itemIndex;
        itemIndex = sc.nextInt();
        int oIndex;
        for(oIndex = 0; oIndex<orderList.size(); oIndex++)
        {
        	if(orderList.get(oIndex).getTableno() == tableno)
        	{
        		break;
        	}
        }
        if(itemIndex>orderList.get(oIndex).getOrderItems().size() || itemIndex<0)
        {
        	System.out.println("Please enter a valid index NO.! Try again...");
        	return;
        }
                                
        OrderMGR.removeOrderItem(oIndex, itemIndex, orderList);
    }

    public void cancelOrderUI(List<Order> orderList, int tableno)
    {
        System.out.println("no of orders: " + orderList.size());
    	OrderMGR.cancelOrder(orderList,tableno);

    }
	
}	
