import java.util.Scanner;

abstract class PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    PaymentMethod() {
        counter++;
        transactionId = "TXN-" + counter;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract String processPayment(double amount);

    // Method overloading
    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount +
                " to card ending " + cardNumberLastFour +
                " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {

    CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount +
                " in cash - Txn " + getTransactionId();
    }
}

public class Problem1 {

    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter card last 4 digits: ");
        String card = in.nextLine();

        System.out.print("Enter amount: ");
        double amount = in.nextDouble();

        CreditCardPayment cc = new CreditCardPayment(card);

        // Upcasting
        PaymentMethod ref = cc;

        printConfirmation(ref, amount);

        in.close();
    }
}