// item class signifies the class of each food item that is available on the Restaurant Menu
//It consists of various parameters as shown below

class Item  {
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

    void printItemQ() {
        System.out.println("ItemID: " + id + " Name: " + name + " Price: " + price + " Quantity: "+ quantity + " Amount: "+ amount);
    }

}

//loose coupling