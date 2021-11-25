
public class States {

    //Depending upon whether restaurant is open/closed the class is to be implemented
    static class RestaurantState {
        private static int state = 0;

        static int getState() {
          return state;
        }

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


        public String getCookingState() {
            // 0 stands for closed/full 1 stands for open

            if (cState == 0) {
                return "Food is being cooked";
            }
            return "Food is ready";
        }

        void setState(int state) {
            this.cState = state;
        }
    }
}
