class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    // No-argument constructor
    public PatientProfile() {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile(String name) {
        this(null, name);
    }

    // Main constructor
    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    // Get patient ID
    public String getPatientId() {
        return patientId;
    }

    // Set patient ID only once
    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    // Get discharged status
    public boolean isDischarged() {
        return discharged;
    }

    // Set discharged status
    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Set locker PIN
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

        // Name-only constructor
        PatientProfile p1 =
                new PatientProfile("Arjun Iyer");

        System.out.println(p1.getPatientId());


        // ID + name constructor
        PatientProfile p2 =
                new PatientProfile("MT2026-0142", "Arjun Iyer");

        System.out.println(p2.getPatientId());


        // Write-once patient ID
        PatientProfile p3 = new PatientProfile();

        p3.setPatientId("MT2026-0142");
        p3.setPatientId("HACKED-0000");

        System.out.println(p3.getPatientId());


        // Discharged
        p3.setDischarged(true);

        System.out.println(p3.isDischarged());


        // Locker PIN
        p3.setLockerPin("1234");
    }
}