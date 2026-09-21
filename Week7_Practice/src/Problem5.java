import java.util.Scanner;

abstract class LibraryItem {

    private static int counter = 1000;
    private final String itemId;

    LibraryItem() {
        counter++;
        itemId = "LI-" + counter;
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    Textbook(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    Magazine(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {

    private String resourceName;

    DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

public class Problem5 {

    static void processCheckouts(LibraryItem[] items) {

        for (int i = 0; i < items.length; i++) {
            System.out.println(
                    "Loan period: " +
                            items[i].getLoanPeriodDays() +
                            " days"
            );
        }
    }

    static String reserveIfSupported(Object o) {

        if (o instanceof Reservable) {

            Reservable r = (Reservable) o;

            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter textbook name: ");
        String textbookName = in.nextLine();

        System.out.print("Enter magazine name: ");
        String magazineName = in.nextLine();

        System.out.print("Enter digital resource name: ");
        String resourceName = in.nextLine();

        Textbook t = new Textbook(textbookName);
        Magazine m = new Magazine(magazineName);
        DigitalPass d = new DigitalPass(resourceName);

        System.out.println("\nTextbook:");
        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        System.out.println("\nMagazine:");
        System.out.println(m.getLoanPeriodDays());
        System.out.println(m.renew());

        System.out.println("\nDigital Pass:");
        System.out.println(d.renew());

        LibraryItem[] items = {t, m};

        System.out.println("\nCheckout Information:");
        processCheckouts(items);

        System.out.println("\nReservation Check:");
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
        System.out.println(reserveIfSupported(t));

        in.close();
    }
}
