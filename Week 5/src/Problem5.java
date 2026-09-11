class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    // Static block
    static {
        System.out.println("Loan Receipt System Started");
    }

    // Constructor
    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        // Check every book ID
        for (int i = 0; i < bookIds.length; i++) {

            String id = bookIds[i];

            if (id == null ||
                    id.length() != 6 ||
                    !id.startsWith("BK-") ||
                    !Character.isDigit(id.charAt(3)) ||
                    !Character.isDigit(id.charAt(4)) ||
                    !Character.isDigit(id.charAt(5))) {

                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        // Defensive copy
        this.bookIds = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            this.bookIds[i] = bookIds[i];
        }
    }


    // Return a copy of the array
    public String[] getBookIds() {

        String[] copy = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            copy[i] = bookIds[i];
        }

        return copy;
    }


    // Create a new corrected receipt
    public LoanReceipt withCorrectedBookId(int index, String newId) {

        if (newId == null ||
                newId.length() != 6 ||
                !newId.startsWith("BK-") ||
                !Character.isDigit(newId.charAt(3)) ||
                !Character.isDigit(newId.charAt(4)) ||
                !Character.isDigit(newId.charAt(5))) {

            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] newBooks = getBookIds();

        newBooks[index] = newId;

        return new LoanReceipt(memberId, newBooks);
    }


    // Process all receipts
    static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
            }
            else {
                processed++;

                if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                }
                else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}


// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}


public class Problem5 {

    public static void main(String[] args) {

        // Test 1
        try {

            LoanReceipt r1 =
                    new LoanReceipt("LIB-8841",
                            new String[]{"BK-100", "bad"});

            System.out.println("Construction successful");
        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }


        // Test 2 - Defensive copying
        LoanReceipt r =
                new LoanReceipt("LIB-8841",
                        new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);


        // Test 3 - Nightly processing
        LoanReceipt[] receipts = {

                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                LoanReceipt.processNightlyCirculation(receipts)
        );
    }
}