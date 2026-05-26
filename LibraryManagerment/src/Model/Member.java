/*
 * Class Member - Library member
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 */
public class Member extends Person {
    private LocalDate membershipDate;
    private List<Book> borrowedBooks;

    // Constructor
    public Member(String id, String name, String email, String phoneNumber, LocalDate membershipDate) {
        super(id, name, email, phoneNumber);
        this.membershipDate = membershipDate;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Setters
    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    // Method: Borrow a book
    public void borrowBook(Book book) {
        if (book != null && book.getQuantity() > 0) {
            borrowedBooks.add(book);
            book.setQuantity(book.getQuantity() - 1);
        }
    }

    // Method: Return a book
    public boolean returnBook(String bookId) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i).getBookId().equals(bookId)) {
                Book returnedBook = borrowedBooks.remove(i);
                returnedBook.setQuantity(returnedBook.getQuantity() + 1);
                return true;
            }
        }
        return false;
    }

    // Method: Display list of borrowed books
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Member " + name + " has not borrowed any books.");
            return;
        }
        System.out.println("=== Books borrowed by " + name + " ===");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i));
        }
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipDate=" + membershipDate +
                ", borrowedBooks=" + borrowedBooks.size() +
                '}';
    }
}
