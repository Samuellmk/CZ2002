package cz2002;
import java.util.ArrayList;
public class OrderMGR {
	
	
	/**
	 * 
	 * @param order
	 */
	public static void printOrder(int tableno, ArrayList<Order> orderList)
	{
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno)
			{
				System.out.println("Table No: " + tableno);
				System.out.println("Order Taken By: " + orderList.get(i).getStaff());
				ArrayList<MenuItem> orders = orderList.get(i).getOrderItems();
				int orderItemNo = 1;
				for(int j = 0; j < orders.size(); j++)
				{
					System.out.printf("%-15S", (orderItemNo++) + ")" + orders.get(j).getName());
					System.out.printf("%.2f%n", (orders.get(j).getPrice()));
				}
				break;
			}
		}
	}

	/**
	 * 
	 * @param staff that registers that table
	 * @param orderItems MenuItems that the table has ordered 
	 * @param tableno that made this order
	 * @param paid true or false
	 */
	public static void createOrder(Staff staff, ArrayList<MenuItem> orderItems, int tableno, boolean paid, ArrayList<Order> orderList)
	{
		int i;
		for(i=0; i<orderList.size(); i++)
		{
			if(orderList.get(i).getTableno() == tableno)
			{
				addOrderItem(staff, orderItems, tableno, orderList); //means got existing order already, use addorderItem instead
				break;
			}
		}
		
		Order newOrder = new Order(staff, orderItems, tableno, paid);
		orderList.add(newOrder);		
	}

	/**
	 * 
	 * @param orders
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
	 * 
	 * @param order
	 */
	public static void addOrderItem(Staff staff, ArrayList<MenuItem> orderItems, int tableno, ArrayList<Order> orderList)
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
	 * 
	 * @param order
	 * @param index
	 */
	public static void removeOrderItem(int ordersIndex, int itemIndex, ArrayList<Order> orderList, ArrayList<MenuItem> orderItems)
	{
		ArrayList<MenuItem> tempOrder = orderList.get(ordersIndex).getOrderItems(); 
		tempOrder.remove(itemIndex-1);
		orderList.get(ordersIndex).setOrderItems(tempOrder);  
		   
		
	}
	
	public static void cancelOrder(ArrayList<Order> orderList, int tableno)
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