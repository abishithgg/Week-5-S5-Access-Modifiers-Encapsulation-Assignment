package A3_Book_Circulation;

class BookInventory {

    // Private fields for encapsulation
    private int copiesTotal;
    private int copiesAvailable;

    // Constructor
    BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                "copiesTotal must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    // Check out one book
    void checkOut() {

        // Only reduce if a copy is available
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    // Check in one book
    void checkIn() {

        // Only increase if inventory is not full
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    // Getter for available copies
    int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class A3_BookCirculation {

    public static void main(String[] args) {

        BookInventory b = new BookInventory(3);

        // Check out 4 times
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();   // rejected silently

        System.out.println(
            "Available copies: "
            + b.getCopiesAvailable()
        );

        // Check in 4 times
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();    // rejected silently

        System.out.println(
            "Available copies: "
            + b.getCopiesAvailable()
        );
    }
}
