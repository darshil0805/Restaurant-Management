import java.util.Scanner;

public class Frontend {
    public static void main(String[] args) {
        new Customer("Darshil");
        new Customer("Sarthak");
        Solution.Admin.addItem("Paneer Butter", 99.2f);
        Solution.Admin.addItem("Dal Makhani", 9.2f);
        Solution.Admin.addItem("Butter Roti", 22.6f);
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
            }
        }
    }
}