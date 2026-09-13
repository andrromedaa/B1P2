class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        patientId = patientId.trim();

        if (patientId.length() < 4) {
            throw new IllegalArgumentException("Invalid patient ID");
        }

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}


class AccessRuleEngine {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }


    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (result.equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}


public class Problem1 {

    public static void main(String[] args) {

        // Test 1
        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "private", "SAME_CLASS"
                )
        );

        // Test 2
        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "default", "DIFFERENT_PACKAGE"
                )
        );

        // Test 3
        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessRuleEngine.summarizeBatch(attempts)
        );

        // Test 4
        try {

            PatientRecord p = new PatientRecord(
                    "MT9",
                    "W3",
                    98.2,
                    "MediTrack Central"
            );

            System.out.println("Construction successful");

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }

        // Test 5
        try {

            PatientRecord p = new PatientRecord(
                    "MT94",
                    "W3",
                    98.2,
                    "MediTrack Central"
            );

            System.out.println("Construction successful");

        } catch (IllegalArgumentException e) {

            System.out.println("construction rejected");
        }
    }
}