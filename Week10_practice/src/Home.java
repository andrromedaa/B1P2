
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void energy();
}

class Device {
    String id;
    boolean on = false;

    Device(String id) {
        this.id = id;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    Plug(String id) {
        super(id);
    }

    public void energy() {
        System.out.println(id + " energy 12 kWh");
    }
}

public class Home {
    public static void main(String[] args) {
        Light l1 = new Light("L1");
        Fan f1 = new Fan("F1");
        Plug p1 = new Plug("P1");

        l1.turnOn();

        if (l1 instanceof Dimmable)
            l1.dim(40);
        else
            System.out.println("L1 rejected: DIM unsupported");

        if (f1 instanceof Dimmable)
            System.out.println("F1 dimmed to 30");
        else
            System.out.println("F1 rejected: DIM unsupported");

        if (f1 instanceof Schedulable)
            f1.schedule("22:00");
        else
            System.out.println("F1 rejected: SCHEDULE unsupported");

        if (p1 instanceof EnergyMonitor)
            p1.energy();
        else
            System.out.println("P1 rejected: ENERGY unsupported");

        if (l1 instanceof EnergyMonitor)
            System.out.println("L1 energy " + "12 kWh");
        else
            System.out.println("L1 rejected: ENERGY unsupported");
    }
}

