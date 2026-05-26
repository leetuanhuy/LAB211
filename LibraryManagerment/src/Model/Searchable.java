/*
 * Interface Searchable - Định nghĩa các phương thức tìm kiếm
 */
package Model;

import java.util.List;

/**
 * @author Admin
 */
public interface Searchable {
    List<Book> searchByTitle(String title);

    List<Book> searchByAuthor(String author);

    Book searchById(String id);
}
