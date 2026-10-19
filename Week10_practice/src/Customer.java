
import java.util.HashSet;

class CustomerDetails {
    int id;
    String name;

    CustomerDetails(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof CustomerDetails))
            return false;

        CustomerDetails c = (CustomerDetails) obj;

        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return id + name.hashCode();
    }
}

public class Customer {
    public static void main(String[] args) {
        HashSet<CustomerDetails> customers = new HashSet<>();

        CustomerDetails c1 = new CustomerDetails(101, "Asha");
        CustomerDetails c2 = new CustomerDetails(101, "Asha");
        CustomerDetails c3 = new CustomerDetails(102, "Ravi");

        System.out.println(customers.add(c1));

        if (customers.add(c2))
            System.out.println("true");
        else
            System.out.println("false (duplicate rejected)");

        System.out.println(customers.add(c3));

        System.out.println("unique count " + customers.size());

        System.out.println("contains: " +
                customers.contains(new CustomerDetails(101, "Asha")));
    }
}
