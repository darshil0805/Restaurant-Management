import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Item {
    String name;
    int id;
    float price;

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

class Customer {
    String name;
    private int id;
    Order newOrder;


    Customer(String name) {
        this.name = name;
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


}


public class Solution {
    static class PriceList {
        static ArrayList<Item> priceList = new ArrayList<>();
    }

    static final class Admin {
        static ArrayList<Order> allOrders = new ArrayList<>();
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


    public static void main(String[] args) {


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        while (true){
        System.out.println("Welcome Admin, " + formatter.format(date));
        System.out.println("Press 1: To change the restaurant state");
        System.out.println("Press 2: To edit the Pricelist");
        System.out.println("Press 3: To add an expense");
        System.out.println("Press 4: To change the cooking state of an order");
        System.out.println("Press 5: To see the revenue till now");
        System.out.println("Press 6: To see the profits till now");

        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {
            case 1:
                System.out.println("The current restaurant state is" + RestaurantState.currentState());
                System.out.println("Press 0 to set the state to closed/full, 1 to set state to opened ");
                switch (sc.nextInt()) {
                    case 0:
                        RestaurantState.setState(0);
                        System.out.println("Restaurant State set to closed");
                    case 1:
                        RestaurantState.setState(1);
                        System.out.println("Restaurant State set to opened");
                }
            case 2:
                System.out.println(PriceList.priceList.toString());
                System.out.println("Press 1 to add item, 2 to edit an item , 3 to remove an item");
                switch (sc.nextInt()) {
                    case 1:
                        System.out.println("Enter Name");
                        String name = sc.nextLine();
                        System.out.println("Enter Price");
                        float price = sc.nextFloat();
                        Admin.addItem(name, price);
                        System.out.println("Item added");
                    case 2:
                        System.out.println("Enter Item ID");
                        int id = sc.nextInt();
                        System.out.println("Enter Name");
                        String name1 = sc.nextLine();
                        System.out.println("Enter Price");
                        float price1 = sc.nextFloat();
                        Admin.changeItem(name1, id, price1);
                        System.out.println("Item changed");
                    case 3:
                        System.out.println("Enter Item ID");
                        int id1 = sc.nextInt();
                        Admin.removeItem(id1);
                        System.out.println("Item removed");

                }
            case 3:
                System.out.println("Enter the expense");
                Admin.addExpense(sc.nextFloat());

            case 4:
                System.out.println("Enter order id between 0-" + Admin.allOrders.size());
                int id = sc.nextInt();
                System.out.println(Admin.allOrders.get(id));
                System.out.println("Press 1 to change to the cooking state to SERVED else press 0");
                switch (sc.nextInt()){
                    case 0:
                        Admin.allOrders.get(id).setState(0);
                        System.out.println("The order state is PREPARING");
                    case 1:
                        Admin.allOrders.get(id).setState(1);
                        System.out.println("The order state is set to SERVED");
                }



            case 5:
                System.out.println("Your revenue is" +Admin.revenue());
            case 6:
                System.out.println("Your profit is" + Admin.profit());
        }


    }
}}





