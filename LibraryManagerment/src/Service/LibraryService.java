/*
 * Class LibraryService - Library business logic
 */
package Service;

import Model.Book;
import Model.Library;
import Model.Member;

import java.util.List;

/**
 * @author Admin
 */
public class LibraryService {
    private Library library;

    public LibraryService(Library library) {
        this.library = library;
    }

    // === Book Management ===
    public void addBook(Book book) {
        library.addBook(book);
        System.out.println("[+] Book added: " + book.getTitle());
    }

    public boolean removeBook(String bookId) {
        boolean removed = library.removeBook(bookId);
        if (removed) {
            System.out.println("[+] Book removed ID: " + bookId);
        } else {
            System.out.println("[-] Book not found ID: " + bookId);
        }
        return removed;
    }

    // === Search ===
    public List<Book> searchByTitle(String title) {
        return library.searchByTitle(title);
    }

    public List<Book> searchByAuthor(String author) {
        return library.searchByAuthor(author);
    }

    public Book searchById(String bookId) {
        return library.searchById(bookId);
    }

    // === Member Management ===
    public void addMember(Member member) {
        library.addMember(member);
    }

    public Member findMemberById(String memberId) {
        for (Member member : library.getMemberList()) {
            if (member.getId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    // === Borrow - Return Books ===
    public boolean borrowBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        Book book = searchById(bookId);

        if (member == null) {
            System.out.println("[-] Member not found ID: " + memberId);
            return false;
        }

        if (book == null) {
            System.out.println("[-] Book not found ID: " + bookId);
            return false;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("[-] Book '" + book.getTitle() + "' is not available");
            return false;
        }

        member.borrowBook(book);
        System.out.println("[+] " + member.getName() + " borrowed: " + book.getTitle());
        return true;
    }

    public boolean returnBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);

        if (member == null) {
            System.out.println("[-] Member not found ID: " + memberId);
            return false;
        }

        boolean returned = member.returnBook(bookId);
        if (returned) {
            System.out.println("[+] " + member.getName() + " returned book ID: " + bookId);
        } else {
            System.out.println("[-] Member does not have this book in borrowed list");
        }
        return returned;
    }

    // === Display ===
    public void displayAllBooks() {
        library.displayAllBooks();
    }

    public void displayAllMembers() {
        library.displayAllMembers();
    }

    public void displayBorrowStatistics() {
        library.getBorrowStatistics();
    }

    // Getters
    public Library getLibrary() {
        return library;
    }
}
