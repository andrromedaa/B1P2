class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    // Constructor
    BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("Invalid copies");
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    // Check out one book
    void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    // Check in one book
    void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    // Get available copies
    int getCopiesAvailable() {
        return copiesAvailable;
    }
}


public class Problem3 {

    public static void main(String[] args) {

        // Test 1
        try {
            BookInventory b1 = new BookInventory(0);
            System.out.println("Construction successful");
        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }


        // Test 2
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();

        // Fourth checkout should be ignored
        b.checkOut();

        System.out.println(b.getCopiesAvailable());


        // Check-in
        b.checkIn();
        b.checkIn();
        b.checkIn();

        // Fourth check-in should be ignored
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}