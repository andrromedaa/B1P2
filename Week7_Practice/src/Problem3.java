import java.util.Scanner;

abstract class StaffMember {

    private double baseSalary;
    protected double bonusRate;

    StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    StaffMember(double baseSalary, double bonusRate) {
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public abstract double calculateBonus();

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {

        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        } else {
            System.out.println("Negative salary rejected.");
        }
    }
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember implements Auditable {

    private int teamSize;

    TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize +
                " team members, salary $" + getSalary();
    }
}

public class Problem3 {

    static String getAuditIfApplicable(StaffMember s) {

        if (s instanceof Auditable) {
            Auditable a = (Auditable) s;
            return a.auditRecord();
        }

        return "No audit required";
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter salary: ");
        double salary = in.nextDouble();

        System.out.print("Enter team size: ");
        int teamSize = in.nextInt();

        TeamLead t = new TeamLead(salary, teamSize);

        System.out.println("Bonus: " + t.calculateBonus());

        System.out.print("Enter new salary: ");
        double newSalary = in.nextDouble();

        t.setSalary(newSalary);

        System.out.println("Salary: " + t.getSalary());

        // Upcasting
        StaffMember ref = t;

        System.out.println(getAuditIfApplicable(ref));

        in.close();
    }
}
