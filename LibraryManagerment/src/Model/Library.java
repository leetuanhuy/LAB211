/*
 * Class Library - Library management
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 */
public class Library implements Searchable {
    private String libraryName;
    private List<Book> bookList;
    private List<Member> memberList;

    // Constructor
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.bookList = new ArrayList<>();
        this.memberList = new ArrayList<>();
    }

    // Getters
    public String getLibraryName() {
        return libraryName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    // Setters
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    // Method: Add a book to the library
    public void addBook(Book book) {
        // Check if book already exists, increase quantity
        for (Book existingBook : bookList) {
            if (existingBook.getBookId().equals(book.getBookId())) {
                existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
                return;
            }
        }
        // Otherwise add new book
        bookList.add(book);
    }

    // Method: Remove a book from the library
    public boolean removeBook(String bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId().equals(bookId)) {
                bookList.remove(i);
                return true;
            }
        }
        return false;
    }

    // Method: Add a member
    public void addMember(Member member) {
        for (Member m : memberList) {
            if (m.getId().equals(member.getId())) {
                System.out.println("[-] This member already exists!");
                return;
            }
        }
        memberList.add(member);
        System.out.println("[+] Member added: " + member.getName());
    }

    // Implement Searchable interface methods

    @Override
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        String lowerTitle = title.toLowerCase();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(lowerTitle)) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        String lowerAuthor = author.toLowerCase();
        for (Book book : bookList) {
            if (book.getAuthor().toLowerCase().contains(lowerAuthor)) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public Book searchById(String id) {
        for (Book book : bookList) {
            if (book.getBookId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    // Method: Display all books
    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("Library has no books yet.");
            return;
        }
        System.out.println("\n=== ALL BOOKS LIST ===");
        System.out.println(String.format("%-7s %-30s %-20s %-15s %-8s %-10s",
                "ID", "Title", "Author", "Category", "Quantity", "Price"));
        System.out.println("=".repeat(100));
        for (Book book : bookList) {
            System.out.println(String.format("%-7s %-30s %-20s %-15s %-8d %-10.0f",
                    book.getBookId(),
                    truncate(book.getTitle(), 28),
                    truncate(book.getAuthor(), 18),
                    truncate(book.getCategory(), 13),
                    book.getQuantity(),
                    book.getPrice()));
        }
    }

    // Method: Display all members
    public void displayAllMembers() {
        if (memberList.isEmpty()) {
            System.out.println("Library has no members yet.");
            return;
        }
        System.out.println("\n=== ALL MEMBERS LIST ===");
        System.out.println(String.format("%-7s %-20s %-25s %-15s %-15s",
                "ID", "Name", "Email", "Phone", "Books Borrowed"));
        System.out.println("=".repeat(85));
        for (Member member : memberList) {
            System.out.println(String.format("%-7s %-20s %-25s %-15s %-15d",
                    member.getId(),
                    truncate(member.getName(), 18),
                    truncate(member.getEmail(), 23),
                    member.getPhoneNumber(),
                    member.getBorrowedBooks().size()));
        }
    }

    // Method: Statistics of borrowed books
    public void getBorrowStatistics() {
        int totalBorrowedBooks = 0;
        System.out.println("\n=== BORROWED BOOKS STATISTICS ===");

        if (memberList.isEmpty()) {
            System.out.println("No members have borrowed books yet.");
            return;
        }

        for (Member member : memberList) {
            int borrowed = member.getBorrowedBooks().size();
            if (borrowed > 0) {
                System.out.println(member.getName() + ": " + borrowed + " books");
                for (Book book : member.getBorrowedBooks()) {
                    System.out.println("  - " + book.getTitle());
                }
            }
            totalBorrowedBooks += borrowed;
        }
        System.out.println("---");
        System.out.println("Total books borrowed: " + totalBorrowedBooks + " books");
    }

    // Utility method: Rút gọn chuỗi
    private String truncate(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length - 2) + "..";
        }
        return str;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryName='" + libraryName + '\'' +
                ", books=" + bookList.size() +
                ", members=" + memberList.size() +
                '}';
    }
}
