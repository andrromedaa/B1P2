class LibraryMember {

    private String membershipId;
    String branchCode;              // default
    protected double finesOwed;
    public String displayName;

    // Parameterized constructor
    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        membershipId = membershipId.trim();

        if (membershipId.length() < 4) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker {

    // Check whether a field can be accessed
    static String classifyAccess(String fieldModifier, String accessorContext) {

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

    // Count allowed and denied for each modifier
    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (modifier.equals("private")) {
                if (result.equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;
            }

            else if (modifier.equals("default")) {
                if (result.equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;
            }

            else if (modifier.equals("protected")) {
                if (result.equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;
            }

            else if (modifier.equals("public")) {
                if (result.equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied + " denied"
                + " | default: " + defaultAllowed + " allowed / " + defaultDenied + " denied"
                + " | protected: " + protectedAllowed + " allowed / " + protectedDenied + " denied"
                + " | public: " + publicAllowed + " allowed / " + publicDenied + " denied";
    }
}

public class Problem1 {

    public static void main(String[] args) {

        System.out.println(
                AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );

        try {
            LibraryMember m =
                    new LibraryMember("LB9", "BR1", 0, "Priya Nair");

            System.out.println("Construction successful");
        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        try {
            LibraryMember m =
                    new LibraryMember("LB94", "BR1", 0, "Priya Nair");

            System.out.println("construction successful");
        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}