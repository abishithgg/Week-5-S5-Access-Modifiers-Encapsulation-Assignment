package A2_Reference_Desk_Subclass;

class LibraryMember {

    private String membershipPin;
    String branchCode;              // default
    protected double finesOwed;
    public String displayName;
}

class PremiumMember extends LibraryMember {

    void showFines() {
        // protected field can be accessed by subclass
        System.out.println("Fines: " + finesOwed);
    }
}

public class A2_ReferenceDeskSubclass {

    // Checks Java access rules
    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        // private → only same class
        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // default → same class or same package
        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // protected
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        // public → accessible everywhere
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    // Finds the first denied attempt
    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                classifyAccess(modifier, context);

            // Stop immediately at the first denial
            if (result.equals("DENIED")) {

                return modifier
                    + " via "
                    + context
                    + " (attempt #"
                    + (i + 1)
                    + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        String[][] attempts = {

            {"public",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
            firstDeniedAttempt(attempts)
        );

        String[][] attempts2 = {

            {"public",
             "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
            firstDeniedAttempt(attempts2)
        );
    }
}
