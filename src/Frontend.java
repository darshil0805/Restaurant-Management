import java.util.Scanner;

public class Frontend {
    public static void main(String[] args) {
        System.out.println("Welcome to Pizzeria, Hope you have a Good day");
        while (true) {
            System.out.println("Press 1 for New Customer");
            System.out.println("Press 2 to see existing order status and bill");
            Scanner sc = new Scanner(System.in);
            switch (sc.nextInt()) {
                case 1: {
                    System.out.println("Please enter your name");
                    Scanner sc2 = new Scanner(System.in);
                    String name = sc2.nextLine();
                    new Customer(name);

                    break;
                }
                case 2: {
                    for (int i = 0; i < Solution.Admin.allCustomers.size(); i++) {
                        Solution.Admin.allCustomers.get(i).printCustomer();
                    }

                    System.out.println("Please Enter the Customer Id to view your bill and see Order status");
                    Scanner sc2 = new Scanner(System.in);
                    int idN = sc2.nextInt();
                    Solution.Admin.allCustomers.get(idN).getBill();
                    Solution.Admin.allCustomers.get(idN).getOrderStatus();
                    break;
                }
            }
        }
    }
}