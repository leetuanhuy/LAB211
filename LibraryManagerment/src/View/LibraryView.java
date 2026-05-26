/*
 * Class LibraryView - Display interface and user interaction
 */
package View;

import Model.*;
import Service.LibraryService;
import Service.MemberService;
import Untils.InputUntil;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Admin
 */
public class LibraryView {
    private LibraryService libraryService;
    private MemberService memberService;

    public LibraryView(LibraryService libraryService, MemberService memberService) {
        this.libraryService = libraryService;
        this.memberService = memberService;
    }

    // Display main menu
    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("<<< LIBRARY MANAGEMENT SYSTEM >>>");
        System.out.println("=".repeat(40));
        System.out.println("1. Add new book");
        System.out.println("2. Remove book");
        System.out.println("3. Search book by title");
        System.out.println("4. Search book by author");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Display all books");
        System.out.println("8. Display all members");
        System.out.println("9. Borrow statistics");
        System.out.println("0. Exit");
        System.out.println("=".repeat(40));
        System.out.print("Select option (0-9): ");
    }

    // 1. Add new book
    public void handleAddBook() {
        System.out.println("\n--- ADD NEW BOOK ---");
        String bookId = InputUntil.inputString("Enter book ID: ");
        String title = InputUntil.inputString("Enter book title: ");
        String author = InputUntil.inputString("Enter author: ");
        String category = InputUntil.inputString("Enter category: ");
        int quantity = InputUntil.inputInt("Enter quantity: ");
        double price = InputUntil.inputDouble("Enter price: ");

        Book book = new Book(bookId, title, author, category, quantity, price);
        libraryService.addBook(book);
    }

    // 2. Remove book
    public void handleRemoveBook() {
        System.out.println("\n--- REMOVE BOOK ---");
        String bookId = InputUntil.inputString("Enter book ID to remove: ");
        libraryService.removeBook(bookId);
    }

    // 3. Search by title
    public void handleSearchByTitle() {
        System.out.println("\n--- SEARCH BY TITLE ---");
        String title = InputUntil.inputString("Enter book title to find: ");
        List<Book> results = libraryService.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("[-] No books found with title: " + title);
        } else {
            System.out.println("\n[Search Results] (" + results.size() + " books):");
            displayBookList(results);
        }
    }

    // 4. Search by author
    public void handleSearchByAuthor() {
        System.out.println("\n--- SEARCH BY AUTHOR ---");
        String author = InputUntil.inputString("Enter author name to find: ");
        List<Book> results = libraryService.searchByAuthor(author);

        if (results.isEmpty()) {
            System.out.println("[-] No books found by author: " + author);
        } else {
            System.out.println("\n[Search Results] (" + results.size() + " books):");
            displayBookList(results);
        }
    }

    // 5. Borrow book
    public void handleBorrowBook() {
        System.out.println("\n--- BORROW BOOK ---");
        String memberId = InputUntil.inputString("Enter member ID: ");
        String bookId = InputUntil.inputString("Enter book ID to borrow: ");

        libraryService.borrowBook(memberId, bookId);
    }

    // 6. Return book
    public void handleReturnBook() {
        System.out.println("\n--- RETURN BOOK ---");
        String memberId = InputUntil.inputString("Enter member ID: ");
        String bookId = InputUntil.inputString("Enter book ID to return: ");

        libraryService.returnBook(memberId, bookId);
    }

    // 7. Display all books
    public void handleDisplayAllBooks() {
        libraryService.displayAllBooks();
    }

    // 8. Display all members
    public void handleDisplayAllMembers() {
        libraryService.displayAllMembers();
    }

    // 9. Borrow statistics
    public void handleBorrowStatistics() {
        libraryService.displayBorrowStatistics();
    }

    // Utility: Display book list
    private void displayBookList(List<Book> books) {
        System.out.println(String.format("%-7s %-30s %-20s %-15s %-8s %-10s",
                "ID", "Title", "Author", "Category", "Quantity", "Price"));
        System.out.println("=".repeat(100));
        for (Book book : books) {
            System.out.println(String.format("%-7s %-30s %-20s %-15s %-8d %-10.0f",
                    book.getBookId(),
                    truncate(book.getTitle(), 28),
                    truncate(book.getAuthor(), 18),
                    truncate(book.getCategory(), 13),
                    book.getQuantity(),
                    book.getPrice()));
        }
    }

    private String truncate(String str, int length) {
        if (str == null) return "";
        if (str.length() > length) {
            return str.substring(0, length - 2) + "..";
        }
        return str;
    }

    // Initialize sample data
    public void initializeSampleData() {
        System.out.println("[*] Loading sample data...");

        // Add sample books
        libraryService.addBook(new Book("B001", "Thinking in Java", "Bruce Eckel", "Programming", 5, 99000));
        libraryService.addBook(new Book("B002", "Clean Code", "Robert Martin", "Programming", 3, 85000));
        libraryService.addBook(new Book("B003", "Design Patterns", "Gang of Four", "Programming", 2, 120000));
        libraryService.addBook(new Book("B004", "The Pragmatic Programmer", "Hunt & Thomas", "Programming", 4, 95000));
        libraryService.addBook(new Book("B005", "Harry Potter", "J.K Rowling", "Fiction", 6, 75000));
        libraryService.addBook(new Book("B006", "1984", "George Orwell", "Fiction", 3, 65000));

        // Add sample members
        libraryService.addMember(new Member("M001", "John Smith", "john@email.com", "0123456789", LocalDate.now()));
        libraryService.addMember(new Member("M002", "Jane Doe", "jane@email.com", "0987654321", LocalDate.now()));
        libraryService.addMember(new Member("M003", "Bob Johnson", "bob@email.com", "0912345678", LocalDate.now()));

        System.out.println("[+] Sample data loaded!\n");
    }
}
