
import java.util.Scanner;

class RaceEntry4 {

    String bibNumber;
    double entryFee;
    double amountPaid;

    RaceEntry4(String bibNumber, double entryFee) {
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

    String announce() {
        return "Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }
}


class RunnerEntry4 extends RaceEntry4 {

    String category;

    RunnerEntry4(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    String announce() {
        return "Runner Entry | Bib: " + bibNumber
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}


class RelayTeamEntry4 extends RaceEntry4 {

    int teamSize;

    RelayTeamEntry4(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    String announce() {
        return "Relay Team | Bib: " + bibNumber
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }

    int getTeamSize() {
        return teamSize;
    }
}


public class Problem4 {

    static String announceAll(RaceEntry4[] entries) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {

            // Polymorphism
            result.append(entries[i].announce());
            result.append(" ");

            // Downcasting only for RelayTeamEntry
            if (entries[i] instanceof RelayTeamEntry4) {

                RelayTeamEntry4 relay =
                        (RelayTeamEntry4) entries[i];

                result.append("[Team size via downcast: ");
                result.append(relay.getTeamSize());
                result.append("] ");
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Runner input
        System.out.print("Enter runner bib number: ");
        String runnerBib = in.nextLine();

        System.out.print("Enter runner entry fee: ");
        double runnerFee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter runner category: ");
        String category = in.nextLine();

        RunnerEntry4 runner =
                new RunnerEntry4(runnerBib, runnerFee, category);


        // Relay input
        System.out.print("\nEnter relay bib number: ");
        String relayBib = in.nextLine();

        System.out.print("Enter relay entry fee: ");
        double relayFee = in.nextDouble();

        System.out.print("Enter team size: ");
        int teamSize = in.nextInt();

        RelayTeamEntry4 relay =
                new RelayTeamEntry4(relayBib, relayFee, teamSize);


        // Store both in RaceEntry array
        RaceEntry4[] entries = {runner, relay};


        // Display final announcement
        System.out.println("\nAnnouncement:");
        System.out.println(announceAll(entries));

        in.close();
    }
}

