package invoice;

import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import cz2002.Customer;
import cz2002.Food;
import cz2002.Order;
import cz2002.PromoPackage;
import table.Table;

import java.time.LocalDateTime; 

/**
* InvoiceMGR can generate invoices based input and print the sales revenue report either by day or month as per the user's preference.
*
* @author Tejas Rajagopal
*/ 

public class InvoiceMGR {

	/**
	 * 
	 * @param staff  the staff taking the order
	 * @param orderItem the items that were ordered by the customer
	 * @param tableno the table where the order was taken
	 * @param timestamp the time at which the invoice is generated
	 * @param servicechrg the percentage of service charge to be applied to the subtotal
	 * @param GST the percentage of GST to be applied to the subtotal
	 */
    public static final double DISCOUNT_RATE = 0.10; //Assuming that members get a 10% discount
    public static final double GST_RATE = 0.07;
    public static final double SERVICE_CHARGE = 0.10;

	/**
	 * 
	 * @param order the order for which the invoices is generated
	 * @param invoices the list of invoices
	 * @param discount the discount percentage to be applied if the customer is a member
	 */

     //creating an invoice from order
	public static void createInvoice(Order order, List<Invoice> invoices, List<Table> tables) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        String timestamp = now.format(dtf);
        System.out.println("----------------Invoice----------------------");
        System.out.println("Staff: "+order.getStaff().getName()+"\tDate: "+timestamp.substring(0,10));
        System.out.println("Table: "+order.getTableno()+"\t\tTime: "+timestamp.substring(11));
        System.out.println("---------------------------------------------");
        System.out.println("Order Items:");
        System.out.println("Quantity\tItem Name\t\tPrice");
        double total = 0.0;
        int q[] = new int[order.getOrderItems().size()];;
        int tempQuant=1;
        for(int i = 0;i<q.length;i++){
            q[i] = 0;
        }
        for(int j = 0;j<order.getOrderItems().size();j++)
        {
            tempQuant=1;
            for(int i = 0;i<order.getOrderItems().size();i++){
                if(order.getOrderItems().get(j).getName().equals(order.getOrderItems().get(i).getName()) && i!=j && q[i]==0){
                    tempQuant++;
                    q[i]=1;
                }
            }
            if(q[j]==0)
            {
                if(order.getOrderItems().get(j).getName().length()>7)
                    System.out.println(tempQuant+"\t\t"+order.getOrderItems().get(j).getName()+"\t\t"+tempQuant*order.getOrderItems().get(j).getPrice());
                else
                    System.out.println(tempQuant+"\t\t"+order.getOrderItems().get(j).getName()+"\t\t\t"+tempQuant*order.getOrderItems().get(j).getPrice());
            }
            total+= order.getOrderItems().get(j).getPrice();  
        }
        System.out.println("---------------------------------------------");
        System.out.println("\t\tSubtotal: "+total+" SGD");
        if(checkMember(order.getCustomer())){
            total = (1-DISCOUNT_RATE)*total; // Discount rate is set at 10% for members
            System.out.println("\t\tDiscount: "+(DISCOUNT_RATE*100)+"%");
        }
        System.out.printf("\t\tService Charge: %.1f", SERVICE_CHARGE*100);
        System.out.print("%\n");
        System.out.printf("\t\tGST: %.1f", GST_RATE*100);
        System.out.print("%\n");
        double servicechrg = SERVICE_CHARGE*total;
        double taxes=0;
        total+=servicechrg;
        taxes+=servicechrg;
        double GST = GST_RATE*total;
        total+=GST;
        taxes+=GST;
        System.out.printf("\t\tTaxes: %.2f",taxes);
        System.out.print(" SGD\n");
        System.out.printf("\t\tTOTAL: %.2f",total);
        System.out.print(" SGD\n");
        System.out.println("--------Thank you for dining with us!--------");
        order.setPaid(true);
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
	 * @param period either day or month 
	 * @param invoices list of invoices
     * @param timestamp timestamp of period given by user
     * @param foodItems list of food items in the menu
     * @param promoItems list of promo items in the menu
	 */

    public static void printSalesRevenueReport(String period, ArrayList<Invoice> invoices, String timestamp, ArrayList<Food> foodItems, ArrayList<PromoPackage> promoItems) { 
    //timestamp can either be dd/mm/yyyy or mm/yyyy
    System.out.println("------------------Sales Revenue Report------------------");
    int quantFood[] = new int[foodItems.size()];
    int quantPromo[] = new int[promoItems.size()];  
    System.out.println("Period: "+timestamp);  
    System.out.println();    
    System.out.println("Food Item\tQuantity\tCurrent Price\tTotal Price");
    double revenue = 0;
    for(int n = 0;n<foodItems.size();n++)
    {
        int quant = 0;
        String foodName = foodItems.get(n).getName();
        for(int i = 0;i<invoices.size();i++)
        {
            if((period.equals("month") && invoices.get(i).getTimestamp().substring(3,10).equals(timestamp)) || (period.equals("day") && invoices.get(i).getTimestamp().substring(0,10).equals(timestamp))){
                for(int j = 0;j<invoices.get(i).getOrderItems().size();j++)
                {
                    if(invoices.get(i).getOrderItems().get(j).getName().equals(foodName)){
                        quant++;
                    }
                }
            }
        }

        if(quant>0)
        {
            if(foodName.length()>7)
                System.out.println(foodName+"\t"+quant+"\t\t"+foodItems.get(n).getPrice()+"\t\t"+(quant*foodItems.get(n).getPrice()));
            else
                System.out.println(foodName+"\t\t"+quant+"\t\t"+foodItems.get(n).getPrice()+"\t\t"+(quant*foodItems.get(n).getPrice()));
        }
    }
    System.out.println();
    System.out.println("Promo Item\tQuantity\tCurrent Price\tTotal Price");
    for(int n = 0;n<promoItems.size();n++)
    {
        int quant = 0;
        String promoName = promoItems.get(n).getName();
        for(int i = 0;i<invoices.size();i++)
        {
            if((period.equals("month") && invoices.get(i).getTimestamp().substring(3,10).equals(timestamp)) || (period.equals("day") && invoices.get(i).getTimestamp().substring(0,10).equals(timestamp))){
                for(int j = 0;j<invoices.get(i).getOrderItems().size();j++)
                {
                    if(invoices.get(i).getOrderItems().get(j).getName().equals(promoName)){
                        quant++;
                    }
                }
            }
        }

        if(quant>0)
        {
            if(promoName.length()>7)
                System.out.println(promoName+"\t"+quant+"\t\t"+promoItems.get(n).getPrice()+"\t\t"+(quant*promoItems.get(n).getPrice()));
            else
                System.out.println(promoName+"\t\t"+quant+"\t\t"+promoItems.get(n).getPrice()+"\t\t"+(quant*promoItems.get(n).getPrice()));
        }
    }
    for(int i = 0;i<invoices.size();i++)
        {
            if((period.equals("month") && invoices.get(i).getTimestamp().substring(3,10).equals(timestamp)) || (period.equals("day") && invoices.get(i).getTimestamp().substring(0,10).equals(timestamp))){
                revenue+=invoices.get(i).getTotal();
            }
        }
    System.out.println();
    System.out.printf("TOTAL REVENUE (Incl. Taxes): %.2f",revenue);
    System.out.print(" SGD\n");
    System.out.println("--------------------------------------------------------");
}

	public static boolean checkMember(Customer customer) {
        return customer.getMembership();
	}
}