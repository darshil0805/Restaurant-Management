import java.util.ArrayList;

// Order class basically consists of an array list of all the itemQs
public class Order extends States.CookingState {
    ArrayList<ItemQ> currentOrder;

    //whenever a Customer gives an order
    Order() {
        currentOrder = new ArrayList<>();
        SuperAdmin.Admin.allOrders.add(this);
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

    public void printOrder() {
        for (int i = 0; i < currentOrder.size(); i++) {
            currentOrder.get(i).printItemQ();
        }
    }
}
