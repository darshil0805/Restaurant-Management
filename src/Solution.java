import java.util.ArrayList;

public class Solution {

    public class Item {
        String name;
        int id;
        float price;

        Item(int id, String name, float price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }


    }
    final private static class Admin {


        class PriceList {



            //            Item I1= new Item();
            ArrayList<Item> items = new ArrayList<>();

            void changeItem(String name, int index, float price) {
                Item item = new Item(index, name, price);
                items.set(index, item);
            }


            void addItem(String name, float price) {
                int index = items.size();
                Item item = new Item(index, name, price);
            }

            void removeItem(int index) {
                items.remove(index);
                for (int i = 0; i < items.size(); i++) {
                    Item item_change = items.get(i);
                    item_change.id = i;
                    items.set(i, item_change);
                }
            }
        }
    }

    static class RestaurantState{
        private int state = 0;
        @Override
        public String toString(){
            // 0 stands for closed/full 1 stands for open

            if(state ==0){
                return "Restaurant is Closed/Full";
            }
            return "Restaurant is Open";
        }

        void setState(int state){
            this.state = state;
        }
    }

    static class CookingState{
        private int state = 0;
        @Override
        public String toString(){
            // 0 stands for closed/full 1 stands for open

            if(state ==0){
                return "Restaurant is Closed/Full";
            }
            return "Restaurant is Open";
        }

        void setState(int state){
            this.state = state;
        }
    }


    public static void main(String[] args) {
        Admin a1 = new Admin();
    }



}



