
import java.util.Scanner;

class RaceEntry5 {

    String bibNumber;
    double entryFee;
    double amountPaid;

    private final int entryCode;
    private static int bibCounter = 0;

    RaceEntry5(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException();
        }

        bibCounter++;

        entryCode = bibCounter;

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        amountPaid = 0;
    }


    void pay(double amount) {
        amountPaid = amountPaid + amount;
    }


    void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }


    double getBalanceDue() {
        return entryFee - amountPaid;
    }


    static boolean isValidDiscountCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }


    static int getBibCounter() {
        return bibCounter;
    }


    static String settleNight(RaceEntry5[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (int i = 0; i < entries.length; i++) {

            if (entries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entries[i] instanceof RelayTeamEntry5) {
                relay++;
            }
            else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }
}


class RunnerEntry5 extends RaceEntry5 {

    String category;

    RunnerEntry5(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}


class EliteRunnerEntry5 extends RunnerEntry5 {

    double sponsorBonus;

    EliteRunnerEntry5(String bibNumber, double entryFee,
                      String category, double sponsorBonus) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}


class RelayTeamEntry5 extends RaceEntry5 {

    int teamSize;

    RelayTeamEntry5(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}


public class Problem5 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        // Normal Race Entry
        System.out.print("Enter bib number: ");
        String bib = in.nextLine();

        System.out.print("Enter entry fee: ");
        double fee = in.nextDouble();

        RaceEntry5 entry =
                new RaceEntry5(bib, fee);


        // Pay using amount and mode
        System.out.print("Enter amount to pay: ");
        double amount = in.nextDouble();

        in.nextLine();

        System.out.print("Enter payment mode: ");
        String mode = in.nextLine();

        entry.pay(amount, mode);


        // Discount code
        System.out.print("Enter discount code: ");
        String code = in.nextLine();

        System.out.println("Valid discount code: "
                + RaceEntry5.isValidDiscountCode(code));


        // Elite Runner
        System.out.print("\nEnter elite bib number: ");
        String eliteBib = in.nextLine();

        System.out.print("Enter elite entry fee: ");
        double eliteFee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter elite category: ");
        String category = in.nextLine();

        System.out.print("Enter sponsor bonus: ");
        double bonus = in.nextDouble();

        EliteRunnerEntry5 elite =
                new EliteRunnerEntry5(
                        eliteBib,
                        eliteFee,
                        category,
                        bonus);


        // Relay Team
        System.out.print("\nEnter relay bib number: ");
        String relayBib = in.next();

        System.out.print("Enter relay entry fee: ");
        double relayFee = in.nextDouble();

        System.out.print("Enter team size: ");
        int teamSize = in.nextInt();

        RelayTeamEntry5 relay =
                new RelayTeamEntry5(
                        relayBib,
                        relayFee,
                        teamSize);


        // Mixed array
        RaceEntry5[] entries = {
                elite,
                null,
                relay
        };


        // Night settlement
        System.out.println("\nNight Settlement:");

        System.out.println(
                RaceEntry5.settleNight(entries)
        );


        // Counter
        System.out.println(
                "Bib counter: "
                        + RaceEntry5.getBibCounter()
        );


        in.close();
    }
}



