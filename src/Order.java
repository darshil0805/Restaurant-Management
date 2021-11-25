import java.util.ArrayList;


//methods to implement various methods related to bill
interface variousAmts{
    float grossAmt();
    float netAmt();
}

// Order class basically consists of an array list of all the itemQs
public class Order extends States.CookingState implements variousAmts {
    private ArrayList<ItemQ> currentOrder;

    //whenever a Customer gives an order
    Order() {
        currentOrder = new ArrayList<>();
        SuperAdmin.Admin.addOrder(this);
    }

    // Showing the gross amount of the bill without the taxes
    public float grossAmt() {
        float amt = 0;
        for (int i = 0; i < currentOrder.size(); i++) {
            amt += currentOrder.get(i).amount;
        }
        return amt;
    }

    public ArrayList<ItemQ> getCurrentOrder(){
        return this.currentOrder;
    }

    // showing the net amt with the taxes
    public float netAmt() {
        return grossAmt() * 1.18f;
    }



    public void printOrder() {
        for (int i = 0; i < currentOrder.size(); i++) {
            currentOrder.get(i).printItemQ();
        }
    }
}
