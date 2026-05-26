/*
 * Class Librarian - Library librarian
 */
package Model;

/**
 * @author Admin
 */
public class Librarian extends Person {
    private String staffId;
    private double salary;

    // Constructor
    public Librarian(String id, String name, String email, String phoneNumber, String staffId, double salary) {
        super(id, name, email, phoneNumber);
        this.staffId = staffId;
        this.salary = salary;
    }

    // Getters
    public String getStaffId() {
        return staffId;
    }

    public double getSalary() {
        return salary;
    }

    // Setters
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }

    // Method: Add a book to the library
    public void addBook(Library library, Book book) {
        if (library != null && book != null) {
            library.addBook(book);
            System.out.println("[+] Librarian " + name + " added book: " + book.getTitle());
        }
    }

    // Method: Remove a book from the library
    public boolean removeBook(Library library, String bookId) {
        if (library != null) {
            boolean isRemoved = library.removeBook(bookId);
            if (isRemoved) {
                System.out.println("[+] Librarian " + name + " removed book ID: " + bookId);
            } else {
                System.out.println("[-] Book not found with ID: " + bookId);
            }
            return isRemoved;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", staffId='" + staffId + '\'' +
                ", salary=" + salary +
                '}';
    }
}
