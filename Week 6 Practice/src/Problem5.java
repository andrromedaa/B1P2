class DischargeSummary5 {

    private final String patientId;
    private final String[] medicationCodes;

    // Static block
    static {
        System.out.println("Discharge Summary System Started");
    }

    // Constructor
    public DischargeSummary5(String patientId,
                             String[] medicationCodes) {

        this.patientId = patientId;

        // Validate every medication code
        for (int i = 0; i < medicationCodes.length; i++) {

            String code = medicationCodes[i];

            if (code == null ||
                    code.length() != 5 ||
                    !code.startsWith("MED-") ||
                    !Character.isUpperCase(code.charAt(4))) {

                throw new IllegalArgumentException(
                        "Invalid medication code"
                );
            }
        }

        // Defensive copy
        this.medicationCodes =
                new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            this.medicationCodes[i] = medicationCodes[i];
        }
    }


    // Return a copy of medication codes
    public String[] getMedicationCodes() {

        String[] copy =
                new String[medicationCodes.length];

        for (int i = 0; i < medicationCodes.length; i++) {
            copy[i] = medicationCodes[i];
        }

        return copy;
    }


    // Create a new object with corrected code
    public DischargeSummary5 withCorrectedMedication(
            int index, String newCode) {

        if (newCode == null ||
                newCode.length() != 5 ||
                !newCode.startsWith("MED-") ||
                !Character.isUpperCase(newCode.charAt(4))) {

            throw new IllegalArgumentException(
                    "Invalid medication code"
            );
        }

        String[] newCodes = getMedicationCodes();

        newCodes[index] = newCode;

        return new DischargeSummary5(
                patientId, newCodes
        );
    }


    // Process nightly batch
    static String processNightlyBatch(
            DischargeSummary5[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {

            if (summaries[i] == null) {

                nullSkipped++;

            } else {

                processed++;

                if (summaries[i]
                        instanceof CriticalCareDischargeSummary5) {

                    criticalCare++;

                } else {

                    routine++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | "
                + routine + " routine";
    }
}


// Critical-care subclass
class CriticalCareDischargeSummary5
        extends DischargeSummary5 {

    private int icuDays;

    public CriticalCareDischargeSummary5(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        this.icuDays = icuDays;
    }
}


public class Problem5 {

    public static void main(String[] args) {

        // Test 1: Invalid medication code
        try {

            DischargeSummary5 d1 =
                    new DischargeSummary5(
                            "MT2026-0142",
                            new String[]{"MED-A", "bad"}
                    );

            System.out.println(
                    "Construction successful"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "construction rejected"
            );
        }


        // Test 2: Defensive copy
        DischargeSummary5 d =
                new DischargeSummary5(
                        "MT2026-0142",
                        new String[]{"MED-A", "MED-B"}
                );

        String[] codes = d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                d.getMedicationCodes()[0]
        );


        // Test 3: Nightly batch
        DischargeSummary5[] summaries = {

                new CriticalCareDischargeSummary5(
                        "MT001",
                        new String[]{"MED-X"},
                        4
                ),

                null,

                new DischargeSummary5(
                        "MT002",
                        new String[]{"MED-Y"}
                )
        };

        System.out.println(
                DischargeSummary5.processNightlyBatch(
                        summaries
                )
        );
    }
}