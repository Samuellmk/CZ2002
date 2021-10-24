package cz2002;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import table.Table;

public class OrderUI {
	

	public Scanner sc = new Scanner(System.in);

	public OrderUI(ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, ArrayList<Order> orderList, ArrayList<Staff> staff, List<Table> tableList, ArrayList<MenuItem> orderItems)
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
				takeOrder(orderstaff, tableList, orderList, foodItems, promoItems,orderItems);
				break; 
				
			case 3:
				editOrder(orderstaff, foodItems, promoItems,orderItems, orderList, tableList);
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
	

	public void showOrderDetails(ArrayList<Order> orderList) 
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
	}
	
    public void takeOrder(Staff orderstaff, List<Table> tableList, ArrayList<Order> orderList, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems,ArrayList<MenuItem> orderItems)
    {
        int i;
        System.out.println("Which TABLE NUMBER are you editing for?");
        int tableno = sc.nextInt();
        for(i=0; i<orderItems.size(); i++)
        {
            if(orderList.get(i).getTableno() == tableno)
            {
                if(tableList.get(i).isOccupied() == false)
                {
                    System.out.println("Table not reserved please try taking orders for other tables instead!");
                    return;
                }
            }
        }

        
        for(i=0; i<orderList.size(); i++)
        {
            if(orderList.get(i).getTableno() == tableno)
            {
                System.out.println("There is an existing order for this table already!");
                System.out.println("Use Edit Order function instead!"); 
                return;
            }
        }

        FoodMenuUI.viewMenuItemsUI(foodItems,promoItems); //Print menus for user to choose items
        
        int oChoice;
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
                    newF(orderstaff,foodItems,orderItems, orderList, tableno);
                    break;				
                case 2:
                    newP(orderstaff, promoItems,orderItems, orderList, tableno);
                    break; 

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(oChoice != -1);
    }


    public void newF(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno)
    {
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
                
        OrderMGR.createOrder(orderstaff, orderItems, tableno, false, orderList);
         
    }

    public void newP(Staff orderstaff,ArrayList<PromoPackage> promoItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno)
    {
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
                 
         OrderMGR.createOrder(orderstaff, orderItems, tableno, false, orderList);
    }

    public void editOrder(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, List<Table> tableList)
    {
        int i;
        System.out.println("Which TABLE NUMBER are you editing for?");
        int tableno = sc.nextInt();
        for(i=0; i<tableList.size(); i++)
        {
            if(tableList.get(i).getTableNo() == tableno)
            {
                if(tableList.get(i).isOccupied() == false)
                {
                    System.out.println("Table not reserved please try taking orders for other tables instead!");
                    return;
                }
            }
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
                    addOrderItemUI(orderstaff, foodItems, promoItems, orderItems, orderList, tableno);
                    break;				
                case 2:
                    removeOrderItemUI(orderItems, orderList, tableno);
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


    public void addOrderItemUI(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno)
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
                    addF(orderstaff,foodItems,orderItems, orderList,tableno);
                    break;				
                case 2:
                    addP(orderstaff,promoItems,orderItems,orderList,tableno);
                    break; 

                default:
                    System.out.println("Please select a number within the list!");
                    break;
            } 
        }while(addChoice != -1);

    }


    public void addF(Staff orderstaff, ArrayList<Food> foodItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno) 
    {
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

    public void addP(Staff orderstaff,ArrayList<PromoPackage> promoItems, ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno) 
    {
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


    public void removeOrderItemUI(ArrayList<MenuItem> orderItems, ArrayList<Order> orderList, int tableno)
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
                                
        OrderMGR.removeOrderItem(oIndex, itemIndex, orderList, orderItems);
    }

    public void cancelOrderUI(ArrayList<Order> orderList, int tableno)
    {
        OrderMGR.cancelOrder(orderList,tableno);
    }
	
}	
