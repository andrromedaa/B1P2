import java.util.Scanner;

abstract class HomeDevice5 {
    private static int counter = 0;
    private final int serialNumber;

    HomeDevice5() {
        counter++;
        serialNumber = counter;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    abstract void activate();
}

interface RemoteControllable5 {
    void connect(String appId);
}

interface EnergyTrackable5 {
    double getConsumptionWatts();
}

class WashingMachine5 extends HomeDevice5
        implements RemoteControllable5, EnergyTrackable5 {

    @Override
    void activate() {
        System.out.println("Washing machine is activated.");
    }

    @Override
    public void connect(String appId) {
        System.out.println("Washing machine connected to app: " + appId);
    }

    @Override
    public double getConsumptionWatts() {
        return 500;
    }
}

class Refrigerator5 extends HomeDevice5
        implements EnergyTrackable5 {

    @Override
    void activate() {
        System.out.println("Refrigerator is activated.");
    }

    @Override
    public double getConsumptionWatts() {
        return 150;
    }
}

class MobileApp5 implements RemoteControllable5 {

    @Override
    public void connect(String appId) {
        System.out.println("Mobile app connected using ID: " + appId);
    }
}

public class Problem5 {

    static void connectAll(RemoteControllable5[] items, String appId) {

        for (RemoteControllable5 item : items) {
            item.connect(appId);
        }
    }

    static double getConsumptionIfTrackable(HomeDevice5 d) {

        if (d instanceof EnergyTrackable5) {
            EnergyTrackable5 device = (EnergyTrackable5) d;
            return device.getConsumptionWatts();
        }

        return 0;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter app ID: ");
        String appId = in.nextLine();

        WashingMachine5 washingMachine = new WashingMachine5();
        Refrigerator5 refrigerator = new Refrigerator5();
        MobileApp5 mobileApp = new MobileApp5();

        System.out.println("\n--- Washing Machine ---");
        washingMachine.activate();
        washingMachine.connect(appId);

        System.out.println("Serial Number: "
                + washingMachine.getSerialNumber());

        System.out.println("Power Consumption: "
                + getConsumptionIfTrackable(washingMachine)
                + " W");

        System.out.println("\n--- Refrigerator ---");
        refrigerator.activate();

        System.out.println("Serial Number: "
                + refrigerator.getSerialNumber());

        System.out.println("Power Consumption: "
                + getConsumptionIfTrackable(refrigerator)
                + " W");

        System.out.println("\n--- Mobile App ---");
        mobileApp.connect(appId);

        System.out.println("\n--- Connect All ---");

        RemoteControllable5[] devices = {
                washingMachine,
                mobileApp
        };

        connectAll(devices, appId);

        System.out.println("\n--- Upcasting Example ---");

        HomeDevice5 device = washingMachine;

        System.out.println("Serial Number: "
                + device.getSerialNumber());

        device.activate();

        in.close();
    }
}