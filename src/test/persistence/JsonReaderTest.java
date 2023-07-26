package persistence;

import model.Book;
import model.BookShelf;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookShelf bookShelf = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        BookShelf bookShelf;
        try {

            JsonReader reader = new JsonReader("./data/testReaderGeneralBookshelf.json");
            bookShelf = reader.read();
            List<Book> books = bookShelf.getBooks();
            assertEquals(2, books.size());
            assertEquals("aa", books.get(0).getName());
            assertEquals("bb", books.get(0).getAuthor());
            assertEquals("cc", books.get(0).getSummary());
            assertEquals("dd", books.get(1).getName());
            assertEquals("ee", books.get(1).getAuthor());
            assertEquals("ff", books.get(1).getSummary());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
