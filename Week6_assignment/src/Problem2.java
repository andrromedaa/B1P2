
import java.util.Scanner;

class RaceEntry2 {

    String bibNumber;
    double entryFee;
    double amountPaid;

    RaceEntry2(String bibNumber, double entryFee) {
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

    void announce() {
        System.out.println("Race Entry");
    }
}


class RunnerEntry2 extends RaceEntry2 {

    String category;

    RunnerEntry2(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber
                + " | Category: " + category
                + " | Balance: " + getBalanceDue());
    }
}


class EliteRunnerEntry2 extends RunnerEntry2 {

    double sponsorBonus;

    EliteRunnerEntry2(String bibNumber, double entryFee,
                      String category, double sponsorBonus) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    void announce() {
        System.out.println("Elite Runner | Bib: " + bibNumber
                + " | Category: " + category
                + " | Sponsor Bonus: " + sponsorBonus
                + " | Balance: " + getBalanceDue());
    }
}


class RelayTeamEntry2 extends RaceEntry2 {

    int teamSize;

    RelayTeamEntry2(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue());
    }
}


public class Problem2 {

    static String classifyGeneration(RaceEntry2 entry) {

        if (entry instanceof EliteRunnerEntry2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry2) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base entry";
    }


    static double getTotalBalanceDue(RaceEntry2[] entries) {

        double total = 0;

        for (int i = 0; i < entries.length; i++) {
            total = total + entries[i].getBalanceDue();
        }

        return total;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // Runner
        System.out.print("Enter runner bib number: ");
        String runnerBib = in.nextLine();

        System.out.print("Enter runner entry fee: ");
        double runnerFee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter runner category: ");
        String runnerCategory = in.nextLine();

        RunnerEntry2 runner =
                new RunnerEntry2(runnerBib, runnerFee, runnerCategory);


        // Elite Runner
        System.out.print("\nEnter elite bib number: ");
        String eliteBib = in.nextLine();

        System.out.print("Enter elite entry fee: ");
        double eliteFee = in.nextDouble();

        in.nextLine();

        System.out.print("Enter elite category: ");
        String eliteCategory = in.nextLine();

        System.out.print("Enter sponsor bonus: ");
        double bonus = in.nextDouble();

        EliteRunnerEntry2 elite =
                new EliteRunnerEntry2(
                        eliteBib, eliteFee, eliteCategory, bonus);


        // Relay Team
        System.out.print("\nEnter relay bib number: ");
        String relayBib = in.next();

        System.out.print("Enter relay entry fee: ");
        double relayFee = in.nextDouble();

        System.out.print("Enter team size: ");
        int teamSize = in.nextInt();

        RelayTeamEntry2 relay =
                new RelayTeamEntry2(relayBib, relayFee, teamSize);


        // Display details
        System.out.println("\n--- Entry Details ---");

        runner.announce();
        elite.announce();
        relay.announce();


        // Classification
        System.out.println("\n--- Classification ---");

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(relay));


        // Total balance
        RaceEntry2[] entries = {runner, elite, relay};

        System.out.println("\nTotal balance due: "
                + getTotalBalanceDue(entries));

        in.close();
    }
}
