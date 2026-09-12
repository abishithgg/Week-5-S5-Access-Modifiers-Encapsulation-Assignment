package A5_Loan_Receipt;

import java.util.Arrays;

// Parent class for loan receipts
class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    // Constructor
    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    // Defensive copy while returning
    public String[] getBookIds() {

        return Arrays.copyOf(bookIds, bookIds.length);
    }

    // With-style method
    // Creates a new object instead of changing the original
    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] updatedBookIds =
                Arrays.copyOf(bookIds, bookIds.length);

        updatedBookIds[index] = newId;

        return new LoanReceipt(
                memberId,
                updatedBookIds
        );
    }
}


// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}


// Nightly circulation processor
class CirculationLedger {

    // Shared branch code
    static String branchCode;

    // Static initialization block
    static {
        branchCode = "PT-LIB-01";
    }

    // Process the nightly receipts
    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        // Single pass
        for (LoanReceipt receipt : receipts) {

            // Handle null safely
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            // Check the actual object type
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}


// Main class
public class A5_LoanReceipt {

    public static void main(String[] args) {

        // Create a receipt
        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"}
                );

        // Test defensive copy from getter
        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        // Original must remain unchanged
        System.out.println(
                "Original first book: "
                        + r.getBookIds()[0]
        );


        // Correct the second book ID
        LoanReceipt corrected =
                r.withCorrectedBookId(
                        1,
                        "BK-102"
                );

        // Original remains unchanged
        System.out.println(
                "Original books: "
                        + Arrays.toString(
                                r.getBookIds()
                        )
        );

        // New object contains corrected value
        System.out.println(
                "Corrected books: "
                        + Arrays.toString(
                                corrected.getBookIds()
                        )
        );


        // Create nightly receipt batch
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


        // Process nightly circulation
        System.out.println(
                CirculationLedger.processNightlyCirculation(
                        receipts
                )
        );


        // Show static block value
        System.out.println(
                "Branch code: "
                        + CirculationLedger.branchCode
        );
    }
}
