
import java.util.*;

interface Capability {
    String getName();
    boolean execute(int value);
}

class PowerCapability implements Capability {
    boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean execute(int value) {
        if (value != 0 && value != 1) {
            return false;
        }

        on = (value == 1);
        return true;
    }
}

class BrightnessCapability implements Capability {
    int brightness = 0;

    public String getName() {
        return "Brightness";
    }

    public boolean execute(int value) {
        if (value < 0 || value > 100) {
            return false;
        }

        brightness = value;
        return true;
    }
}

class TemperatureCapability implements Capability {
    int temperature = 24;

    public String getName() {
        return "Temperature";
    }

    public boolean execute(int value) {
        if (value < 16 || value > 30) {
            return false;
        }

        temperature = value;
        return true;
    }
}

class Device {
    String name;
    ArrayList<Capability> capabilities = new ArrayList<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability capability) {
        if (getCapability(capability.getName()) != null) {
            System.out.println(name + ": " + capability.getName()
                    + " capability already exists.");
            return;
        }

        capabilities.add(capability);

        if (capability instanceof BrightnessCapability) {
            System.out.println(name + ": Brightness capability added.");
        }
    }

    Capability getCapability(String capabilityName) {
        for (Capability c : capabilities) {
            if (c.getName().equalsIgnoreCase(capabilityName)) {
                return c;
            }
        }

        return null;
    }

    boolean apply(String capabilityName, int value) {
        Capability c = getCapability(capabilityName);

        if (c == null) {
            return false;
        }

        if (!c.execute(value)) {
            if (capabilityName.equalsIgnoreCase("Temperature")) {
                System.out.println("Rejected: " + name
                        + " temperature must be between 16°C and 30°C.");
            } else if (capabilityName.equalsIgnoreCase("Brightness")) {
                System.out.println("Rejected: " + name
                        + " brightness must be between 0% and 100%.");
            } else {
                System.out.println("Rejected: Invalid power value.");
            }

            return false;
        }

        if (capabilityName.equalsIgnoreCase("Power")) {
            System.out.println(name + ": " + (value == 1 ? "ON" : "OFF") + ".");
        } else if (capabilityName.equalsIgnoreCase("Brightness")) {
            System.out.println(name + ": brightness set to " + value + "%.");
        } else if (capabilityName.equalsIgnoreCase("Temperature")) {
            System.out.println(name + ": temperature set to " + value + "°C.");
        }

        return true;
    }
}

class SceneStep {
    String capabilityName;
    int value;

    SceneStep(String capabilityName, int value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    int execute(ArrayList<Device> devices) {
        int count = 0;

        for (Device d : devices) {
            if (d.getCapability(capabilityName) != null) {
                if (d.apply(capabilityName, value)) {
                    count++;
                }
            }
        }

        return count;
    }
}

class Scene {
    String name;
    ArrayList<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(String capabilityName, int value) {
        steps.add(new SceneStep(capabilityName, value));
    }

    void execute(ArrayList<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int totalActions = 0;

        for (SceneStep step : steps) {
            totalActions += step.execute(devices);
        }

        System.out.println("Scene '" + name
                + "' completed: " + totalActions + " actions applied.");
    }
}

public class W8assignment {
    public static void main(String[] args) {
        ArrayList<Device> devices = new ArrayList<>();

        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        devices.add(ac);
        devices.add(lights);
        devices.add(projector);

        Scene lecture = new Scene("Lecture Mode");
        lecture.addStep("Power", 1);
        lecture.addStep("Brightness", 40);
        lecture.addStep("Temperature", 24);

        lecture.execute(devices);

        ac.apply("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.apply("Brightness", 70);
    }
}

