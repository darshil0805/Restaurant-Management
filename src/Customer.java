import java.util.Scanner;

//Customer class contains the details of all the customers with various parameters as shown
public class Customer {
    String name;
    private int id;
    Order newOrder;


    Customer(String name) {
        this.name = name;
        SuperAdmin.Admin.allCustomers.add(this);
        this.id = SuperAdmin.Admin.allCustomers.size();
    }


    void getBill() {
        newOrder.printOrder();
        System.out.println("Gross amount for your order is:" + newOrder.grossAmt());
        System.out.println("Net amount after 18% gst is: " + newOrder.netAmt());

    }

    String getMenu() {
        return SuperAdmin.PriceList.priceList.toString();
    }


    // the order function is used to initiate an order instance, the oder is to be given in a specific format
    void order() {
        System.out.println("The order should be in the format : Item1id Item1quantity <enter>Item2id Item2quantity <enter> Item3id Item3quantity");
        System.out.println("Press anything except an integer to end the order");
        newOrder = new Order();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {

            int id = scanner.nextInt();
            int quant = scanner.nextInt();
            ItemQ food = new ItemQ(SuperAdmin.PriceList.priceList.get(id), quant);
            newOrder.currentOrder.add(food);
        }
    }

    void getOrderStatus() {
        System.out.println(newOrder.toString());
    }


    void printCustomer() {
        System.out.println("CustomerID: " + id + " Name: " + name);
    }

}
