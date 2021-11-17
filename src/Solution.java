import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.util.ArrayList;
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
        private float expenses = 0;

        void changeItem(String name, int index, float price) {
            Item item = new Item(index, name, price);
            PriceList.priceList.set(index, item);
        }


        void addItem(String name, float price) {
            int index = PriceList.priceList.size();
            Item item = new Item(index, name, price);
        }

        void removeItem(int index) {
            PriceList.priceList.remove(index);
            for (int i = 0; i < PriceList.priceList.size(); i++) {
                Item item_change = PriceList.priceList.get(i);
                item_change.id = i;
                PriceList.priceList.set(i, item_change);
            }
        }

        float revenue() {
            float rev = 0;
            for (int i = 0; i < allOrders.size(); i++) {
                rev += allOrders.get(i).grossAmt();
            }
            return rev;
        }

        float profit() {
            return this.revenue() - expenses;
        }

        void addExpense(float amt) {
            expenses += amt;
        }
    }

    static class RestaurantState {
        private int state = 0;

        @Override
        public String toString() {
            // 0 stands for closed/full 1 stands for open

            if (state == 0) {
                return "Restaurant is Closed/Full";
            }
            return "Restaurant is Open";
        }

        void setState(int state) {
            this.state = state;
        }
    }

    static class CookingState {
        private int cState = 0;

        @Override
        public String toString() {
            // 0 stands for closed/full 1 stands for open

            if (cState == 0) {
                return "Restaurant is Closed/Full";
            }
            return "Restaurant is Open";
        }

        void setState(int state) {
            this.cState = state;
        }
    }


    public static void main(String[] args) {
        Admin a1 = new Admin();
    }


}



