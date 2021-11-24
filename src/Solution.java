import java.text.SimpleDateFormat; //importing the java lib to get the current date
import java.util.*;// importing various lists


// item class signifies the class of each food item that is available on the Restaurant Menu
//It consists of various parameters as shown below
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

// Itemq class consists of an item bind to its quantity ordered by the customer
// this is done so as to make the order taking process easier
class ItemQ extends Item {
    int quantity;
    float amount;

    ItemQ(Item item, int quantity) {
        super(item.id, item.name, item.price);
        this.quantity = quantity;
        this.amount = (this.quantity) * (this.price);
    }
}


// Order class basically consists of an array list of all the itemQs
class Order extends Solution.CookingState {
    ArrayList<ItemQ> currentOrder;

    //whenever a Customer gives an order
    Order() {
        currentOrder = new ArrayList<>();
        Solution.Admin.allOrders.add(this);
    }

    // Showing the gross amount of the bill without the taxes
    float grossAmt() {
        float amt = 0;
        for (int i = 0; i < currentOrder.size(); i++) {
            amt += currentOrder.get(i).amount;
        }
        return amt;
    }
// showing the net amt with the taxes
    float netAmt() {
        return grossAmt() * 1.18f;
    }

    public String toString() {
        return super.toString();
    }
}


//Customer class contains the details of all the customers with various parameters as shown
class Customer {
    String name;
    private int id;
    Order newOrder;


    Customer(String name) {
        this.name = name;
        Solution.Admin.allCustomers.add(this);
        this.id = Solution.Admin.allCustomers.size();
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


    // the order function is used to initiate an order instance, the oder is to be given in a specific format
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

    // PriceList is basically the collection of all the items that are available in the menu with their price
    static class PriceList {
        static ArrayList<Item> priceList = new ArrayList<>();

        static void printPriceList() {
            for (int i = 0; i < priceList.size(); i++) {
                priceList.get(i).printItem();
            }
        }
    }

    //This is the admin class which controls all the functions of the restaurants
    //Adding expenses
    //Changing the order state
    //Changing the restaurant state
    //And other functions too
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


    //Depending upon whether restaurant is open/closed the class is to be implemented
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


    //the cooking state of each order
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
        new Customer("Darshil");
        new Customer("Sarthak");
        Admin.addItem("Paneer Butter", 99.2f);
        Admin.addItem("Dal Makhani", 9.2f);
        Admin.addItem("Butter Roti", 22.6f);
        while(true){
        System.out.println("Press 1 for Admin Menu and 2 for Customer Menu");
        Scanner s2 = new Scanner(System.in);
       try{
           int major = s2.nextInt();

           switch (major){
               case 2:{

                   System.out.println("Welcome to Pizzeria, Hope you have a Good day");
                   while (true) {
                       System.out.println("Press 1 for New Customer");
                       System.out.println("Press 2 to see existing order status and bill");
                       System.out.println("Press 3 to go to the main menu");
                       Scanner sc = new Scanner(System.in);
                       int get = sc.nextInt();
                       try {
                           if(get==3){break;}
                       switch (get) {
                           case 1: {
                               System.out.println("Please enter your name");
                               Scanner sc2 = new Scanner(System.in);
                               String name = sc2.nextLine();
                               Solution.PriceList.printPriceList();
                               Customer c1= new Customer(name);
                               c1.order();
                               System.out.println("Thank you for ordering, you can check your order status from the main menu");
                               break;
                           }
                           case 2: {
                               for (int i = 0; i < Solution.Admin.allCustomers.size(); i++) {
                                   Solution.Admin.allCustomers.get(i).printCustomer();
                               }

                               System.out.println("Please Enter the Customer Id to view your bill and see Order status");
                               Scanner sc2 = new Scanner(System.in);
                               int idN = sc2.nextInt();
                               Solution.Admin.allCustomers.get(idN-1).getBill();
                               Solution.Admin.allCustomers.get(idN-1).getOrderStatus();
                               break;
                           }
                           default:
                               System.out.println("Please Enter either 1 or 2");
                               break;
                       }} catch (Exception e) {
                           System.out.println("Please Enter either 1 or 2");
                           break;
                       }

                   } break;}
               case 1:{
                   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                   Date date = new Date();
                   System.out.println("Please enter the 4-digit pin to continue");
                   Scanner p = new Scanner(System.in);
                   String pass = p.nextLine();
                   if (pass.equals("6969")){

                   while (true) {

                       System.out.println("Welcome Admin, " + formatter.format(date));
                       System.out.println("Press 1: To change the restaurant state");
                       System.out.println("Press 2: To edit the Pricelist");
                       System.out.println("Press 3: To add an expense");
                       System.out.println("Press 4: To change the cooking state of an order");
                       System.out.println("Press 5: To see the revenue till now");
                       System.out.println("Press 6: To see the profits till now");
                       System.out.println("Press 7: To go to main menu");
                       Scanner sc = new Scanner(System.in);


                       try {
                           int main = sc.nextInt();
                           if(main==7){break;}
                           switch (main) {
                               case 1: {
                                   System.out.println("The current restaurant state is" + RestaurantState.currentState());
                                   System.out.println("Press 0 to set the state to closed/full, 1 to set state to opened ");
                                   Scanner sc1 = new Scanner(System.in);
                                   try {
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
                                           default:
                                               System.out.println("Press 0 or 1 only");
                                       }
                                       break;
                                   } catch (NullPointerException ee) {
                                       System.out.println("Press 0 or 1 only");
                                       break;
                                   }
                               }


                               case 2: {
                                   System.out.println("Showing PriceList");
                                   PriceList.printPriceList();
                                   System.out.println("Press 1 to add item, 2 to edit an item , 3 to remove an item");
                                   Scanner sc1 = new Scanner(System.in);
                                   try {
                                       int sec = sc1.nextInt();
//                    sc1.close();
                                       switch (sec) {
                                           case 1: {
                                               System.out.println("Enter Name");
                                               Scanner sc2 = new Scanner(System.in);
                                               String name = sc2.nextLine();
                                               if (!name.equals("")) {
                                                   System.out.println("Enter Price");
                                                   Scanner sc3 = new Scanner(System.in);
                                                   try {
                                                       float price = sc3.nextFloat();
                                                       Admin.addItem(name, price);
                                                       System.out.println("Item added");
                                                       break;
                                                   } catch (Exception mmm) {
                                                       System.out.println("please enter a floating point integer");
                                                       break;
                                                   }
                                               }
                                           }
                                           case 2: {
                                               System.out.println("Enter Item ID");
                                               Scanner sc2 = new Scanner(System.in);
                                               int id = sc2.nextInt();
                                               System.out.println("Enter Name");
                                               Scanner sc3 = new Scanner(System.in);
                                               String name1 = sc3.nextLine();
                                               System.out.println("Enter Price");
                                               Scanner sc4 = new Scanner(System.in);
                                               try {
                                                   float price1 = sc4.nextFloat();
                                                   Admin.changeItem(name1, id, price1);
                                                   System.out.println("Item changed");
                                                   break;
                                               } catch (Exception kk) {
                                                   System.out.println("Please enter floating point no");
                                                   break;
                                               }
                                           }
                                           case 3: {
                                               System.out.println("Enter Item ID");
                                               Scanner sc2 = new Scanner(System.in);
                                               int id1 = sc2.nextInt();
                                               Admin.removeItem(id1);
                                               System.out.println("Item removed");
                                               break;
                                           }
                                           default:
                                               System.out.println("Please enter an integer between 1-3");
                                       }
                                       break;
                                   } catch (Exception mm) {
                                       System.out.println("Please enter an integer between 1-3");
                                       break;
                                   }
                               }
                               case 3: {
                                   System.out.println("Enter the expense");
                                   Scanner sc4 = new Scanner(System.in);

                                   try {
                                       float price1 = sc4.nextFloat();
                                       Admin.addExpense(price1);
                                       break;
                                   } catch (Exception m) {
                                       System.out.println("Please enter a floating point number");


                                       break;


                                   }

                               }

                               case 4: {
                                   System.out.println("Enter order id between 0-" + Admin.allOrders.size());
                                   Scanner sc2 = new Scanner(System.in);
                                   try {

                                       int id = sc2.nextInt();
                                       System.out.println(Admin.allOrders.get(id));
                                       System.out.println("Press 1 to change to the cooking state to SERVED else press 0");
                                       Scanner sc3 = new Scanner(System.in);
                                       try {
                                           int id2 = sc3.nextInt();
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
                                               default:
                                                   System.out.println("PLease enter an integer between 0-1");
                                           }
                                       } catch (Exception e) {
                                           System.out.println("please enter an between 0-1");
                                           break;
                                       }

                                       break;
                                   } catch (Exception m) {
                                       System.out.println("Please enter a valid id");
                                       break;
                                   }
                               }
                               case 5: {
                                   System.out.println("Your revenue is" + Admin.revenue());
                                   break;
                               }
                               case 6: {
                                   System.out.println("Your profit is" + Admin.profit());
                                   break;
                               }

                               default:
                                   System.out.println("Please enter an integer between 1-6");
                           }
                       } catch (InputMismatchException e) {
                           System.out.println("Please enter an integer between 1-7");
                           break;
                       } catch (NullPointerException mm) {
                           System.out.println("Please enter an integer");
                           break;
                       }


                   }
                   break;
               }
               else{
                       System.out.println("Wrong Password Please try Again");
                       break;
               }
               }
               default:
                   System.out.println("Please enter numbers from 0-2");
           }
       } catch (Exception e) {
             System.out.println("Please enter either 1 or 2");

       }
    }}}





