import java.util.Scanner;

class RaceEntry1 {

    String bibNumber;
    double entryFee;
    double amountPaid;

    RaceEntry1(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException();
        }

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

    static String registerBatch(String[] bibNumbers, double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {

            try {
                new RaceEntry1(bibNumbers[i], entryFee);
                registered++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
                " | Rejected: " + rejected;
    }
}


class RunnerEntry1 extends RaceEntry1 {

    String category;

    RunnerEntry1(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}


public class Problem1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter bib number: ");
        String bib = in.nextLine();

        System.out.print("Enter entry fee: ");
        double fee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter category: ");
        String category = in.nextLine();

        try {

            RunnerEntry1 runner =
                    new RunnerEntry1(bib, fee, category);

            System.out.print("Enter amount to pay: ");
            double amount = in.nextDouble();

            runner.pay(amount);

            System.out.println("Balance due: "
                    + runner.getBalanceDue());

        }
        catch (IllegalArgumentException e) {

            System.out.println("Invalid bib number");
        }


        System.out.print("\nEnter number of bibs: ");
        int n = in.nextInt();

        in.nextLine();

        String[] bibNumbers = new String[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter bib number " + (i + 1) + ": ");
            bibNumbers[i] = in.nextLine();
        }

        System.out.print("Enter entry fee for batch: ");
        double batchFee = in.nextDouble();

        System.out.println(
                RaceEntry1.registerBatch(bibNumbers, batchFee)
        );

        in.close();
    }
}


