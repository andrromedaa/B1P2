
class FoodOrder {
    String studentName;
    String dishName;
    boolean delivered = false;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
                dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }

        this.studentName = studentName;
        this.dishName = dishName;
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered successfully");
        } else {
            System.out.println("Order has already been delivered");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {
            try {
                FoodOrder order = new FoodOrder(
                        rawOrders[i][0],
                        rawOrders[i][1]
                );
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}

public class W4assignment {
    public static void main(String[] args) {
        String[][] orders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(orders);
    }
}

