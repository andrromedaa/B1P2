import java.util.Scanner;

abstract class ServiceableVehicle3 {
    private double mileage;

    abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km >= 0) {
            mileage = mileage + km;
        } else {
            System.out.println("Negative distance is not allowed.");
        }
    }
}

interface Insurable3 {
    String getInsuranceInfo();
}

class Forklift3 extends ServiceableVehicle3 implements Insurable3 {
    private String assetTag;

    Forklift3(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    String performMaintenance() {
        return "Forklift " + assetTag + ": maintenance completed";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insurance: Fleet insurance for " + assetTag;
    }
}

class HeavyDutyForklift3 extends Forklift3 {

    HeavyDutyForklift3(String assetTag) {
        super(assetTag);
    }

    @Override
    String performMaintenance() {
        return super.performMaintenance()
                + " | Extra hydraulic pressure check completed";
    }
}

public class Problem3 {

    static String getInsuranceIfApplicable(ServiceableVehicle3 v) {

        if (v instanceof Insurable3) {
            Insurable3 obj = (Insurable3) v;
            return obj.getInsuranceInfo();
        }

        return "No insurance record";
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter forklift asset tag: ");
        String tag = in.nextLine();

        System.out.print("Enter kilometres travelled: ");
        double km = in.nextDouble();

        Forklift3 forklift = new Forklift3(tag);

        forklift.addMileage(km);

        System.out.println("\n--- Forklift Details ---");
        System.out.println("Asset Tag: " + tag);
        System.out.println("Mileage: " + forklift.getMileage());
        System.out.println(forklift.performMaintenance());
        System.out.println(getInsuranceIfApplicable(forklift));

        System.out.print("\nEnter heavy-duty forklift asset tag: ");
        in.nextLine();
        String heavyTag = in.nextLine();

        HeavyDutyForklift3 heavyForklift =
                new HeavyDutyForklift3(heavyTag);

        System.out.println("\n--- Heavy Duty Forklift ---");
        System.out.println("Asset Tag: " + heavyTag);
        System.out.println(heavyForklift.performMaintenance());
        System.out.println(getInsuranceIfApplicable(heavyForklift));

        in.close();
    }
}
