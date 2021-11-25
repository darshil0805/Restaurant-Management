import java.text.SimpleDateFormat; //importing the java lib to get the current date
import java.util.*;// importing various lists
//The Open/closed principle implies that you can create systems in which new features are added by adding new code as opposed to changing old code



public class Solution {
    static class Closed extends Thread {
        public void run() {
            System.out.println("Restaurant is Closed/Full, no new orders can be taken");
            System.out.println("Press 2 to see existing order status and bill");
            System.out.println("Press 3 to go to the main menu");
        }
    }

    static class Opened extends Thread {
        public void run() {
            System.out.println("Press 1 for New Customer");
            System.out.println("Press 2 to see existing order status and bill");
            System.out.println("Press 3 to go to the main menu");
        }
    }

    public static void main(String[] args) {
        new Customer("Darshil");
        new Customer("Sarthak");
        SuperAdmin.Admin.addItem("Paneer Butter", 99.2f);
        SuperAdmin.Admin.addItem("Dal Makhani", 9.2f);
        SuperAdmin.Admin.addItem("Butter Roti", 22.6f);
        while (true) {
            System.out.println("Press 1 for Admin Menu and 2 for Customer Menu");
            Scanner s2 = new Scanner(System.in);
            try {
                int major = s2.nextInt();

                switch (major) {
                    case 2: {

                        System.out.println("Welcome to Pizzeria, Hope you have a Good day");
                        while (true) {
                            if (States.RestaurantState.getState() == 0) {
                                Closed closed = new Closed();
                                closed.start();
                            } else {
                                Opened opened = new Opened();
                                opened.start();
                            }

                            Scanner sc = new Scanner(System.in);
                            int get = sc.nextInt();
                            try {
                                if ((States.RestaurantState.getState() == 0 && get == 1) || get == 3) {
                                    break;
                                }
                                switch (get) {
                                    case 1: {
                                        System.out.println("Please enter your name");
                                        Scanner sc2 = new Scanner(System.in);
                                        String name = sc2.nextLine();
                                        Customer c1 = new Customer(name);
                                        c1.getMenu();
                                        c1.order();
                                        System.out.println("Thank you for ordering, you can check your order status from the main menu");
                                        break;
                                    }
                                    case 2: {
                                        for (int i = 0; i < SuperAdmin.Admin.getAllCustomers().size(); i++) {
                                            SuperAdmin.Admin.getAllCustomers().get(i).printCustomer();
                                        }

                                        System.out.println("Please Enter the Customer Id to view your bill and see Order status");
                                        Scanner sc2 = new Scanner(System.in);
                                        int idN = sc2.nextInt();
                                        SuperAdmin.Admin.getAllCustomers().get(idN - 1).getBill();
                                        SuperAdmin.Admin.getAllCustomers().get(idN - 1).getOrderStatus();
                                        break;
                                    }
                                    default:
                                        System.out.println("Please Enter either 1 or 2");
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Please Enter either 1 or 2");
                            }

                        }
                        break;
                    }
                    case 1: {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = new Date();
                        System.out.println("Please enter the 4-digit pin to continue");
                        Scanner p = new Scanner(System.in);
                        String pass = p.nextLine();
                        if (pass.equals("6969")) {
                            System.out.println("Welcome Admin, " + formatter.format(date));

                            while (true) {


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
                                    if (main == 7) {
                                        break;
                                    }
                                    switch (main) {
                                        case 1: {
                                            System.out.println("The current restaurant state is" + States.RestaurantState.currentState());
                                            System.out.println("Press 0 to set the state to closed/full, 1 to set state to opened ");
                                            Scanner sc1 = new Scanner(System.in);
                                            try {
                                                int sec = sc1.nextInt();
                                                switch (sec) {
                                                    case 0: {
                                                        States.RestaurantState.setState(0);
                                                        System.out.println("Restaurant State set to closed");
                                                        break;
                                                    }
                                                    case 1: {
                                                        States.RestaurantState.setState(1);
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
                                            SuperAdmin.PriceList.printPriceList();
                                            System.out.println("Press 1 to add item, 2 to edit an item , 3 to remove an item");
                                            Scanner sc1 = new Scanner(System.in);
                                            try {
                                                int sec = sc1.nextInt();
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
                                                                SuperAdmin.Admin.addItem(name, price);
                                                                System.out.println("Item added");
                                                                break;
                                                            } catch (Exception mmm) { //different type of errors may come so using general class
                                                                System.out.println("please enter a floating point number");
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    case 2: {
                                                        System.out.println("Enter Item ID");
                                                        Scanner sc2 = new Scanner(System.in);
                                                        try {
                                                            int id = sc2.nextInt();
                                                            System.out.println("Enter Name");
                                                            Scanner sc3 = new Scanner(System.in);
                                                            String name1 = sc3.nextLine();
                                                            System.out.println("Enter Price");
                                                            Scanner sc4 = new Scanner(System.in);
                                                            float price1 = sc4.nextFloat();
                                                            SuperAdmin.Admin.changeItem(name1, id, price1);
                                                            System.out.println("Item changed");
                                                            break;
                                                        } catch (Exception kk) {
                                                            System.out.println("Please enter valid inputs");
                                                            break;
                                                        }
                                                    }
                                                    case 3: {
                                                        System.out.println("Enter Item ID");
                                                        Scanner sc2 = new Scanner(System.in);
                                                        int id1 = sc2.nextInt();
                                                        SuperAdmin.Admin.removeItem(id1);
                                                        System.out.println("Item removed");
                                                        break;
                                                    }
                                                    default:
                                                        System.out.println("Please enter an integer between 1-3");
                                                }
                                                break;
                                            } catch (Exception mm) {
                                                System.out.println("Please enter valid input");
                                                break;
                                            }
                                        }
                                        case 3: {
                                            System.out.println("Enter the expense");
                                            Scanner sc4 = new Scanner(System.in);

                                            try {
                                                float price1 = sc4.nextFloat();
                                                SuperAdmin.Admin.addExpense(price1);
                                                break;
                                            } catch (Exception m) {
                                                System.out.println("Please enter a floating point number");


                                                break;


                                            }

                                        }

                                        case 4: {
                                            System.out.println("Enter order id between 0-" + SuperAdmin.Admin.getAllOrders().size());
                                            Scanner sc2 = new Scanner(System.in);
                                            try {

                                                int id = sc2.nextInt();
                                                System.out.println(SuperAdmin.Admin.getAllOrders().get(id));
                                                System.out.println("Press 1 to change to the cooking state to SERVED else press 0");
                                                Scanner sc3 = new Scanner(System.in);
                                                try {
                                                    int id2 = sc3.nextInt();
                                                    switch (id2) {
                                                        case 0: {
                                                            SuperAdmin.Admin.getAllOrders().get(id).setState(0);
                                                            System.out.println("The order state is PREPARING");
                                                            break;
                                                        }
                                                        case 1: {
                                                            SuperAdmin.Admin.getAllOrders().get(id).setState(1);
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
                                            System.out.println("Your revenue is " + SuperAdmin.Admin.revenue());
                                            break;
                                        }
                                        case 6: {
                                            System.out.println("Your profit is " + SuperAdmin.Admin.profit());
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
                        } else {
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
        }
    }
}





