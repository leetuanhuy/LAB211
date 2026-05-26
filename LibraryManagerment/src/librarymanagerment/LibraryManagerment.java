/*
 * Main class - Entry point of the library management application
 */
package librarymanagerment;

import Model.Library;
import Service.LibraryService;
import Service.MemberService;
import Untils.InputUntil;
import View.LibraryView;

/**
 * @author Admin
 */
public class LibraryManagerment {

    public static void main(String[] args) {
        // Initialize Model
        Library library = new Library("Central City Library");

        // Initialize Service Layer
        LibraryService libraryService = new LibraryService(library);
        MemberService memberService = new MemberService();

        // Initialize View Layer
        LibraryView libraryView = new LibraryView(libraryService, memberService);

        // Load sample data
        libraryView.initializeSampleData();

        // Main menu loop
        boolean running = true;
        while (running) {
            libraryView.displayMainMenu();
            String choice = InputUntil.inputChoice("");

            switch (choice) {
                case "1":
                    libraryView.handleAddBook();
                    break;

                case "2":
                    libraryView.handleRemoveBook();
                    break;

                case "3":
                    libraryView.handleSearchByTitle();
                    break;

                case "4":
                    libraryView.handleSearchByAuthor();
                    break;

                case "5":
                    libraryView.handleBorrowBook();
                    break;

                case "6":
                    libraryView.handleReturnBook();
                    break;

                case "7":
                    libraryView.handleDisplayAllBooks();
                    break;

                case "8":
                    libraryView.handleDisplayAllMembers();
                    break;

                case "9":
                    libraryView.handleBorrowStatistics();
                    break;

                case "0":
                    System.out.println("\n[*] Thank you for using the system! Goodbye!\n");
                    running = false;
                    break;

                default:
                    System.out.println("[ERROR] Invalid option! Please choose again (0-9)");
            }
        }

        // Close scanner
        InputUntil.closeScanner();
    }
}
