import java.util.ArrayList;

public class SuperAdmin {

    // PriceList is basically the collection of all the items that are available in the menu with their price
    static class PriceList {
        private static ArrayList<Item> priceList = new ArrayList<>();

        static void printPriceList() {
            for (int i = 0; i < priceList.size(); i++) {
                priceList.get(i).printItem();
            }
        }
        static ArrayList<Item> getPriceList(){
            return priceList;
        }
    }

    //This is the admin class which controls all the functions of the restaurants
    //Adding expenses
    //Changing the order state
    //Changing the restaurant state
    //And other functions too

    static final class Admin{
        private static ArrayList<Order> allOrders = new ArrayList<>();
        private static ArrayList<Customer> allCustomers = new ArrayList<>();

        private static float expenses = 0;

        public static ArrayList<Customer> getAllCustomers() {
            return allCustomers;
        }

        static void changeItem(String name, int index, float price) {
            Item item = new Item(index, name, price);
            SuperAdmin.PriceList.priceList.set(index, item);
        }

        static void addOrder(Order order){
            allOrders.add(order);
        }

        static void addCustomer(Customer customer){
            allCustomers.add(customer);
        }

        static ArrayList<Order> getAllOrders(){
            return  allOrders;
        }

        static void addItem(String name, float price) {
            int index = SuperAdmin.PriceList.priceList.size();
            Item item = new Item(index, name, price);
            SuperAdmin.PriceList.priceList.add(item);
        }

        static void removeItem(int index) {
            SuperAdmin.PriceList.priceList.remove(index);
            for (int i = 0; i < SuperAdmin.PriceList.priceList.size(); i++) {
                Item item_change = SuperAdmin.PriceList.priceList.get(i);
                item_change.id = i;
                SuperAdmin.PriceList.priceList.set(i, item_change);
            }
        }


        //Get revenue: only accessible from the admin class
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
}

//loose coupling