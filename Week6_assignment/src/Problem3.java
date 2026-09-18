
import java.util.Scanner;

class RaceEntry3 {

    String bibNumber;
    double entryFee;
    double amountPaid;

    double[] lateFeeHistory = new double[10];
    int feeCount = 0;

    RaceEntry3(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        amountPaid = 0;
    }

    void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    double getBalanceDue() {
        return entryFee - amountPaid;
    }

    protected void applyLateFee(double amount) {

        entryFee = entryFee + amount;

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }

    double[] getLateFeeHistory() {

        double[] copy = new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}


class RunnerEntry3 extends RaceEntry3 {

    String category;

    RunnerEntry3(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}


public class Problem3 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter bib number: ");
        String bib = in.nextLine();

        System.out.print("Enter entry fee: ");
        double fee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter category: ");
        String category = in.nextLine();

        RunnerEntry3 runner =
                new RunnerEntry3(bib, fee, category);

        System.out.print("Enter amount paid: ");
        double paid = in.nextDouble();

        runner.pay(paid);

        System.out.print("Enter late fee: ");
        double lateFee = in.nextDouble();

        runner.applyLateFee(lateFee);

        System.out.println("\nBalance due: "
                + runner.getBalanceDue());

        double[] history = runner.getLateFeeHistory();

        System.out.println("Late fee history:");

        for (int i = 0; i < history.length; i++) {
            System.out.println(history[i]);
        }

        in.close();
    }
}


