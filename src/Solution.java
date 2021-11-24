
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class Item {
    String name;
    int id;
    float price;

    void printItem() {
        System.out.println("ItemID: " + id + " Name: " + name + " Price: " + price);
    }


    Item(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}


class ItemQ extends Item {
    int quantity;
    float amount;

    ItemQ(Item item, int quantity) {
        super(item.id, item.name, item.price);
        this.quantity = quantity;
        this.amount = (this.quantity) * (this.price);
    }
}

class Order extends Solution.CookingState {
    ArrayList<ItemQ> currentOrder;

    Order() {
        currentOrder = new ArrayList<>();
        Solution.Admin.allOrders.add(this);
    }

    float grossAmt() {
        float amt = 0;
        for (int i = 0; i < currentOrder.size(); i++) {
            amt += currentOrder.get(i).amount;
        }
        return amt;
    }

    float netAmt() {
        return grossAmt() * 1.18f;
    }

    public String toString() {
        return super.toString();
    }
}

class Customer{
    String name;
    private int id;
    Order newOrder;


    Customer(String name) {
        this.name = name;
        Solution.Admin.allCustomers.add(this);
        this.id= Solution.Admin.allCustomers.size();
//        order();
    }


    void getBill() {
        System.out.println(newOrder.currentOrder.toString());
        System.out.println("Gross amount for your order is:" + newOrder.grossAmt());
        System.out.println("Net amount after 18% gst is: " + newOrder.netAmt());

    }

    String getMenu() {
        return Solution.PriceList.priceList.toString();
    }

    void order() {
        System.out.println("The order should be in the format : Item1id Item1quantity <enter>Item2id Item2quantity <enter> Item3id Item3quantity");
        System.out.println("Press anything except an integer to end the order");
        newOrder = new Order();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            int quant = scanner.nextInt();
            ItemQ food = new ItemQ(Solution.PriceList.priceList.get(id), quant);
            newOrder.currentOrder.add(food);
        }
    }

    void getOrderStatus() {
        System.out.println(newOrder);
    }


    void printCustomer() {
        System.out.println("CustomerID: " + id + " Name: " + name);
    }

}


public class Solution {
    static class PriceList {
        static ArrayList<Item> priceList = new ArrayList<>();

        static void printPriceList() {
            for (int i = 0; i < priceList.size(); i++) {
                priceList.get(i).printItem();
            }
        }
    }

    static final class Admin {
        static ArrayList<Order> allOrders = new ArrayList<>();
        static ArrayList<Customer> allCustomers = new ArrayList<>();

        private static float expenses = 0;

        static void changeItem(String name, int index, float price) {
            Item item = new Item(index, name, price);
            PriceList.priceList.set(index, item);
        }


        static void addItem(String name, float price) {
            int index = PriceList.priceList.size();
            Item item = new Item(index, name, price);
            PriceList.priceList.add(item);
        }

        static void removeItem(int index) {
            PriceList.priceList.remove(index);
            for (int i = 0; i < PriceList.priceList.size(); i++) {
                Item item_change = PriceList.priceList.get(i);
                item_change.id = i;
                PriceList.priceList.set(i, item_change);
            }
        }

        static float revenue() {
            float rev = 0;
            for (int i = 0; i < allOrders.size(); i++) {
                rev += allOrders.get(i).grossAmt();
            }
            return rev;
        }

        static float profit() {
            return Admin.revenue() - expenses;
        }

        static void addExpense(float amt) {
            expenses += amt;
        }
    }

    static class RestaurantState {
        private static int state = 0;


        public static String currentState() {
            // 0 stands for closed/full 1 stands for open

            if (state == 0) {
                return "Restaurant is Closed/Full";
            }
            return "Restaurant is Open";
        }

        static void setState(int state) {
            RestaurantState.state = state;
        }
    }

    static class CookingState {
        private int cState = 0;

        @Override
        public String toString() {
            // 0 stands for closed/full 1 stands for open

            if (cState == 0) {
                return "Food in being cooked";
            }
            return "Food is ready";
        }

        void setState(int state) {
            this.cState = state;
        }
    }
//
//static {
//    new Customer("Darshil");
//    new Customer("Sarthak");
//    Admin.addItem("Paneer Butter", 99.2f);
//    Admin.addItem("Dal Makhani", 9.2f);
//    Admin.addItem("Butter Roti", 22.6f);
//}
    public static void main(String[] args) {
        new Customer("Darshil");
        new Customer("Sarthak");
       Admin.addItem("Paneer Butter", 99.2f);
       Admin.addItem("Dal Makhani", 9.2f);
       Admin.addItem("Butter Roti", 22.6f);


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        while (true) {
            System.out.println("Welcome Admin, " + formatter.format(date));
            System.out.println("Press 1: To change the restaurant state");
            System.out.println("Press 2: To edit the Pricelist");
            System.out.println("Press 3: To add an expense");
            System.out.println("Press 4: To change the cooking state of an order");
            System.out.println("Press 5: To see the revenue till now");
            System.out.println("Press 6: To see the profits till now");

            Scanner sc = new Scanner(System.in);
            int main = sc.nextInt();
//            sc.close();
            System.out.println(main);
            switch (main) {
                case 1: {
                    System.out.println("The current restaurant state is" + RestaurantState.currentState());
                    System.out.println("Press 0 to set the state to closed/full, 1 to set state to opened ");
                    Scanner sc1 = new Scanner(System.in);
                    int sec = sc1.nextInt();
//                    sc1.close();
                    switch (sec) {
                        case 0: {
                            RestaurantState.setState(0);
                            System.out.println("Restaurant State set to closed");
                            break;
                        }
                        case 1: {
                            RestaurantState.setState(1);
                            System.out.println("Restaurant State set to opened");
                            break;
                        }

                    }
                    break;
                }
                case 2: {
                    System.out.println("Showing PriceList");
                    PriceList.printPriceList();
                    System.out.println("Press 1 to add item, 2 to edit an item , 3 to remove an item");
                    Scanner sc1 = new Scanner(System.in);
                    int sec = sc1.nextInt();
//                    sc1.close();
                    switch (sec) {
                        case 1: {
                            System.out.println("Enter Name");
                            Scanner sc2 = new Scanner(System.in);
                            String name = sc2.nextLine();
//                            sc2.close();

                            if (!name.equals("")) {
                                System.out.println("Enter Price");
                                Scanner sc3 = new Scanner(System.in);
                                float price = sc3.nextFloat();
//                                sc3.close();
                                Admin.addItem(name, price);
                                System.out.println("Item added");
                                break;
                            }

                            //break;
                        }
                        case 2: {
                            System.out.println("Enter Item ID");
                            Scanner sc2 = new Scanner(System.in);
                            int id = sc2.nextInt();
//                            sc2.close();
                            System.out.println("Enter Name");
                            Scanner sc3 = new Scanner(System.in);
                            String name = sc3.nextLine();
//                            sc3.close();
                            String name1 = sc3.nextLine();
                            System.out.println("Enter Price");
                            Scanner sc4 = new Scanner(System.in);
                            float price1 = sc4.nextFloat();
//                            sc4.close();
                            Admin.changeItem(name1, id, price1);
                            System.out.println("Item changed");
                            break;
                        }
                        case 3: {
                            System.out.println("Enter Item ID");
                            Scanner sc2 = new Scanner(System.in);
                            int id1 = sc2.nextInt();
//                            sc2.close();
                            Admin.removeItem(id1);
                            System.out.println("Item removed");
                            break;
                        }

                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter the expense");
                    Scanner sc4 = new Scanner(System.in);
                    float price1 = sc4.nextFloat();
//                    sc4.close();
                    Admin.addExpense(price1);
                    break;
                }

                case 4: {
                    System.out.println("Enter order id between 0-" + Admin.allOrders.size());
                    Scanner sc2 = new Scanner(System.in);
                    int id = sc2.nextInt();
//                    sc2.close();
                    System.out.println(Admin.allOrders.get(id));
                    System.out.println("Press 1 to change to the cooking state to SERVED else press 0");
                    Scanner sc3 = new Scanner(System.in);
                    int id2 = sc3.nextInt();
//                    sc3.close();
                    switch (id2) {
                        case 0: {
                            Admin.allOrders.get(id).setState(0);
                            System.out.println("The order state is PREPARING");
                            break;
                        }
                        case 1: {
                            Admin.allOrders.get(id).setState(1);
                            System.out.println("The order state is set to SERVED");
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Your revenue is" + Admin.revenue());
                    break;
                }
                case 6: {
                    System.out.println("Your profit is" + Admin.profit());
                    break;
                }
                case 7: {
                    System.out.println("Please enter the customer's name");
                    Scanner sc2 = new Scanner(System.in);
                    String name = sc2.nextLine();
                    Solution.PriceList.printPriceList();
                    Customer c1= new Customer(name);
                    c1.order();
                    System.out.println("Thank you for ordering, you can check your order status from the main menu");
                    break;
                }
            }


        }
    }
}





