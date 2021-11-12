package cz2002;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Order Manager class, it is responsible for handling all the function in OrderUI class
 * @author JunHeng
 *
 */

public class OrderMGR {
	
	
	/**
	 * Method to print order details given tableno 
	 * @param tableno  tableno of the order to print
	 * @param orderList List of existing orders to loop through and find matching tableno order
	 */
	public static void printOrder(int tableno, List<Order> orderList)
	{
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno)
			{
				System.out.println("Table No: " + tableno);
				System.out.println("Order Taken By: " + orderList.get(i).getStaff().getName());
				ArrayList<MenuItem> orders = orderList.get(i).getOrderItems();
				int orderItemNo = 1;
				for(int j = 0; j < orders.size(); j++)
				{
					System.out.printf("%-15S", (orderItemNo++) + ")" + orders.get(j).getName());
					System.out.print(" ");
					System.out.printf("%.2f%n", (orders.get(j).getPrice()));
				}
				break;
			}
		}
	}

	/**
	 * Method to create new order 
	 * @param staff that registers that table
	 * @param orderList list of all the orders 
	 * @param tableno that made this order
	 * @param paid true or false
	 * @param customer customer that made the order 
	 */
	public static void createOrder(Staff staff, int tableno, boolean paid, List<Order> orderList, Customer customer)
	{
		ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>(); 
		Order newOrder = new Order(staff, orderItems, tableno, paid, customer);
		orderList.add(newOrder);		
	}

	/**
	 * Method to check if an order has been paid for
	 * @param orderList orderList List of existing orders to loop through and find matching tableno order
	 * @param tableno tableno of table to check whether it has been paid for
	 * @return whether the order has been paid for, true or false 
	 */
	public static boolean checkOrderPaid(ArrayList<Order> orderList , int tableno) {
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno)
			{
				break;
			}			
		}
		boolean checkPaid = orderList.get(i).getPaid();
		return checkPaid;
	}

	/**
	 * Method to add more items to existing order of the table
	 * @param staff staff taking the order 
	 * @param orderItems List of orderItems made by the customer from the 2 menus 
	 * @param tableno tableno that made this order
	 * @param orderList List of existing orders to loop through and find matching tableno order
	 */
	public static void addOrderItem(Staff staff, List<MenuItem> orderItems, int tableno, List<Order> orderList)
	{
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno)
			{
				ArrayList<MenuItem> tempOrder = orderList.get(i).getOrderItems();
				for(int j = 0; j < orderItems.size(); j++)
				{
					tempOrder.add(orderItems.get(j));
				}
			
				orderList.get(i).setOrderItems(tempOrder);
				break;
			}
			
		}
	}	

	/**
	 * Method to remove items from the existing order of the given tableno
	 * @param ordersIndex index of the orderitem in the existing order to be removed
	 * @param itemIndex index of the menuitem in the order chosen to be removed
	 * @param orderList List of existing orders to loop through and find matching tableno order
	 * @param tableno tableno of the table that want to remove order item
	 */
	public static void removeOrderItem(int ordersIndex, int itemIndex, List<Order> orderList,int tableno)
	{
		ArrayList<MenuItem> tempOrder = orderList.get(ordersIndex).getOrderItems(); 
		tempOrder.remove(itemIndex-1);
		orderList.get(ordersIndex).setOrderItems(tempOrder);
		if(tempOrder.size()==0)
		{
			System.out.println("No more item in this order, cancelling order...");
			cancelOrder(orderList,tableno);
		}
		   
		
	}
	/**
	 * Method to cancel the existing order of the given tableno
	 * @param orderList List of existing orders to loop through and find matching tableno order
	 * @param tableno tableno that wants to cancel the order
	 */
	public static void cancelOrder(List<Order> orderList, int tableno)
	{
		int orderIndex;
		for(orderIndex=0; orderIndex<orderList.size(); orderIndex++)
		{
			if(orderList.get(orderIndex).getTableno() == tableno)
			{
				orderList.remove(orderIndex);
			}
		}
	}

}