import java.util.Scanner;

interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    protected String zoneName;

    SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {

    MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZoneName;

    DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message) +
                " [also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {
    private String deviceId;

    SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class Problem2 {

    static void broadcastAll(Alertable[] devices, String message) {

        for (int i = 0; i < devices.length; i++) {
            System.out.println(devices[i].sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {

        if (a instanceof MotionSensor) {
            MotionSensor m = (MotionSensor) a;
            return m.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter motion sensor zone: ");
        String zone = in.nextLine();

        System.out.print("Enter second zone: ");
        String secondZone = in.nextLine();

        System.out.print("Enter smoke detector ID: ");
        String id = in.nextLine();

        MotionSensor m = new MotionSensor(zone);
        DualZoneMotionSensor d =
                new DualZoneMotionSensor(zone, secondZone);
        SmokeDetector s = new SmokeDetector(id);

        Alertable[] devices = {m, d, s};

        System.out.println("\nAlerts:");
        broadcastAll(devices, "Motion detected");

        System.out.println("\nChecking devices:");
        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        in.close();
    }
}