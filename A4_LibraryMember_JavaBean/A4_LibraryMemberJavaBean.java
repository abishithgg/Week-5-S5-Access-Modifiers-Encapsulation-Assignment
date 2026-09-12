package A4_LibraryMember_JavaBean;

class LibraryMember {

    // Private fields
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // Public no-argument constructor
    public LibraryMember() {
        membershipId = null;
        name = "";
        premiumMember = false;
        securityAnswer = null;
    }

    // Getter for membershipId
    public String getMembershipId() {
        return membershipId;
    }

    // Write-once setter
    public void setMembershipId(String id) {

        // Set only the first time
        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // JavaBean boolean getter
    public boolean isPremiumMember() {
        return premiumMember;
    }

    // Setter for premiumMember
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        // Store a simple one-way transformation
        if (answer != null) {
            securityAnswer = Integer.toHexString(
                answer.hashCode()
            );
        }
    }
}

public class A4_LibraryMemberJavaBean {

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        // Set membership ID
        m.setMembershipId("LIB-8841");

        // Set name
        m.setName("Priya Nair");

        // Set premium status
        m.setPremiumMember(true);

        System.out.println(
            "Membership ID: "
            + m.getMembershipId()
        );

        System.out.println(
            "Name: "
            + m.getName()
        );

        System.out.println(
            "Premium: "
            + m.isPremiumMember()
        );

        // Try changing membership ID
        m.setMembershipId("FAKE-0000");

        System.out.println(
            "After second ID update: "
            + m.getMembershipId()
        );

        // Security answer can be set
        // but there is NO getter
        m.setSecurityAnswer("BlueMountain");

        System.out.println("Security answer set successfully");
    }
}
