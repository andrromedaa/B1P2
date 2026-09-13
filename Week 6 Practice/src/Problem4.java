class PatientProfile4 {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    // No-argument constructor
    public PatientProfile4() {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile4(String name) {
        this(null, name);
    }

    // Main constructor
    public PatientProfile4(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    // Patient ID getter
    public String getPatientId() {
        return patientId;
    }

    // Patient ID setter - works only once
    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    // Name getter
    public String getName() {
        return name;
    }

    // Name setter
    public void setName(String name) {
        this.name = name;
    }

    // Discharged getter
    public boolean isDischarged() {
        return discharged;
    }

    // Discharged setter
    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Locker PIN setter
    // No getter is provided
    public void setLockerPin(String pin) {

        if (pin != null &&
                pin.length() >= 4 &&
                pin.length() <= 6) {

            lockerPin = pin;
        }
    }
}


public class Problem4 {

    public static void main(String[] args) {

        // Test 1: name-only constructor
        PatientProfile4 p1 =
                new PatientProfile4("Arjun Iyer");

        System.out.println(p1.getPatientId());


        // Test 2: id + name constructor
        PatientProfile4 p2 =
                new PatientProfile4(
                        "MT2026-0142",
                        "Arjun Iyer"
                );

        System.out.println(p2.getPatientId());


        // Test 3: patient ID can be set only once
        PatientProfile4 p3 =
                new PatientProfile4();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());


        // Test 4: discharged
        p3.setDischarged(true);

        System.out.println(p3.isDischarged());


        // Test 5: locker PIN
        p3.setLockerPin("1234");
    }
}