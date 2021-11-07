package invoice;

import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
import java.util.List;

import cz2002.Customer;

//import com.sun.tools.javac.util.List;

import cz2002.Order;
import table.Table;

import java.time.LocalDateTime;  
public class InvoiceMGR {

	/**
	 * 
	 * @param staff
	 * @param orderItem
	 * @param tableno
	 * @param timestamp
	 * @param servicechrg
	 * @param GST
	 */
    public static final double DISCOUNT_RATE = 0.10; //Assuming that members get a 10% discount
    public static final double GST_RATE = 0.07; 
    public static final double SERVICE_CHARGE = 0.10;
	/*public Invoice createInvoice(Staff staff, ArrayList<OrderItem> orderItems, int tableno, double servicechrg, double GST) {
		// TODO - implement InvoiceMGR.createInvoice
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        String timestamp = now.format(format);
        Invoice invoice = new Invoice(staff, orderitems, tableno, timestamp, servicechrg, GST);
	}*/

	/**
	 * 
	 * @param order
	 * @param invoices
	 * @param discount
	 */

     //creating an invoice from order
	public static void createInvoice(Order order, List<Invoice> invoices, List<Table> tables) {
		// TODO - implement InvoiceMGR.printInvoice
        //for(int i = 0;i<invoices.size();i++){
          //  if(order.getTableno()==invoices.get(i).getTableno()){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        String timestamp = now.format(dtf);
        System.out.println("--------Invoice--------");
        System.out.println("Table No: "+order.getTableno());
        System.out.println("Time: "+timestamp);
        System.out.println("Order Items:");
        System.out.println("Item Name\tItem Price");
        double total = 0.0;
        for(int j = 0;j<order.getOrderItems().size();j++)
        {
            System.out.println(order.getOrderItems().get(j).getName()+"\t"+order.getOrderItems().get(j).getPrice());
            total+= order.getOrderItems().get(j).getPrice();  
        }
        System.out.println("Subtotal: = "+total+"SGD");
        if(checkMember(order.getCustomer())){
            total = (1-DISCOUNT_RATE)*total; // Discount rate is set at 10% for members
            //System.out.println("Subtotal after "+(DISCOUNT_RATE*100)"% discount = "+total);
            System.out.println("Discount = "+(DISCOUNT_RATE*100)+"%");
        }
        System.out.println("GST = "+(GST_RATE*100)+"%");
        System.out.println("Service Charge = "+(SERVICE_CHARGE*100)+"%");
        double servicechrg = SERVICE_CHARGE*total;
        total+=servicechrg;
        double GST = GST_RATE*total;
        System.out.println("TOTAL = "+total+"SGD");
        System.out.println("Thank you for dining with us!");
        order.setPaid(true);
        //Table.setOccupied(false, order.getTableno());
        for(int i = 0;i<tables.size();i++){
            if(tables.get(i).getTableNo()==order.getTableno()){
                tables.get(i).setOccupied(false);
            }
        }
        Invoice invoice = new Invoice(order.getStaff().getEmployeeID(), order.getOrderItems(), order.getTableno(), timestamp, servicechrg, GST, checkMember(order.getCustomer()), total);
        invoices.add(invoice);
	}

	/**
	 * 
	 * @param period
	 * @param invoices
	 */
	public static void printSalesRevenueReport(String period, ArrayList<Invoice> invoices) {
        if(period.equals("day")){
            for(int i = 0;i<invoices.size();i++){
                double revenue = 0;
                String timestamp = invoices.get(i).getTimestamp().substring(0,10);
                int j = i;
                if(invoices.get(j).getTimestamp().substring(0,10).equals(timestamp)){
                    revenue += invoices.get(j).getTotal();
                    j++;
                }
                i=j;
                System.out.println("Revenue for "+timestamp+" = "+revenue);
            }
        }
        else if(period.equals("month")){
            for(int i = 0;i<invoices.size();i++){
                double revenue = 0;
                String timestamp = invoices.get(i).getTimestamp().substring(0,5);
                int j = i;
                if(invoices.get(j).getTimestamp().substring(0,5).equals(timestamp)){
                    revenue += invoices.get(j).getTotal();
                    j++;
                }
                i=j;
                System.out.println("Revenue for "+timestamp+" = "+revenue);
            }
        }
	}

	public static boolean checkMember(Customer customer) {
        return customer.getMembership();
	}
}