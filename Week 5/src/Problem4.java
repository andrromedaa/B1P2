class LibraryMember4 {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // No-argument constructor
    public LibraryMember4() {
        this(null, null);
    }

    // Name-only constructor
    public LibraryMember4(String name) {
        this(null, name);
    }

    // Main constructor
    public LibraryMember4(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    // Get membership ID
    public String getMembershipId() {
        return membershipId;
    }

    // Set membership ID only once
    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Get premium member
    public boolean isPremiumMember() {
        return premiumMember;
    }

    // Set premium member
    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    // Set security answer
    public void setSecurityAnswer(String answer) {

        if (answer != null) {
            securityAnswer = answer.toLowerCase();
        }
    }
}


public class Problem4 {

    public static void main(String[] args) {

        // Name-only constructor
        LibraryMember4 m1 = new LibraryMember4("Priya Nair");

        System.out.println(m1.getMembershipId());


        // ID + name constructor
        LibraryMember4 m2 =
                new LibraryMember4("LIB-8841", "Priya Nair");

        System.out.println(m2.getMembershipId());


        // Write-once membership ID
        LibraryMember4 m3 = new LibraryMember4();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(m3.getMembershipId());


        // Premium member
        m3.setPremiumMember(true);

        System.out.println(m3.isPremiumMember());


        // Security answer can be set
        // but there is NO getter for it
        m3.setSecurityAnswer("MyPassword");
    }
}