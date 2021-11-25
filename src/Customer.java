import java.util.Scanner;


interface customerMethods{
    void getOrderStatus();
    void printCustomer();
}
//Customer class contains the details of all the customers with various parameters as shown
public class Customer implements customerMethods{
    private String name;
    private int id;
    private Order newOrder;



    Customer(String name) {
        this.name = name;
        SuperAdmin.Admin.addCustomer(this);
        this.id = SuperAdmin.Admin.getAllCustomers().size();
    }

    public Order getNewOrder() {
        return newOrder;
    }

    void getBill() {
        newOrder.printOrder();
        System.out.println("Gross amount for your order is:" + newOrder.grossAmt());
        System.out.println("Net amount after 18% gst is: " + newOrder.netAmt());

    }

    void getMenu() {
        SuperAdmin.PriceList.printPriceList();
    }


    // the order function is used to initiate an order instance, the oder is to be given in a specific format
    void order() {
        System.out.println("The order should be in the format : Item1id Item1Quantity <enter>Item2id Item2Quantity <enter> Item3id Item3Quantity");
        System.out.println("Press anything except an integer to end the order");
        newOrder = new Order();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            int quant = scanner.nextInt();
            ItemQ food = new ItemQ(SuperAdmin.PriceList.getPriceList().get(id), quant);
            newOrder.getCurrentOrder().add(food);
        }
    }

    public void getOrderStatus() {
        System.out.println(newOrder.getCookingState());
    }
    public void printCustomer() {
        System.out.println("CustomerID: " + id + " Name: " + name);
    }

}
