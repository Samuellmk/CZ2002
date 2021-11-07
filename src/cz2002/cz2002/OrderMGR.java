package cz2002;
import java.util.ArrayList;
import java.util.List;
public class OrderMGR {
	
	
	/**
	 * 
	 * @param order
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
	 * 
	 * @param staff that registers that table
	 * @param orderItems MenuItems that the table has ordered 
	 * @param tableno that made this order
	 * @param paid true or false
	 */
	public static void createOrder(Staff staff, int tableno, boolean paid, List<Order> orderList, Customer customer)
	{
		ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>(); 
		Order newOrder = new Order(staff, orderItems, tableno, paid, customer);
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
	 * 
	 * @param order
	 * @param index
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