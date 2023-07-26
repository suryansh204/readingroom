package persistence;

import model.Book;
import model.BookShelf;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads bookshelf from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bookshelf from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookShelf read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookShelf(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses bookshelf from JSON object and returns it
    private BookShelf parseBookShelf(JSONObject jsonObject) {
        BookShelf bookShelf = new BookShelf();
        addBooks(bookShelf, jsonObject);
        return bookShelf;
    }

    // MODIFIES: bookShelf
    // EFFECTS: parses books from JSON object and adds them to bookshelf
    private void addBooks(BookShelf bookShelf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bookShelf, nextBook);
        }
    }

    // MODIFIES: bookshelf
    // EFFECTS: parses book from JSON object and adds it to bookshelf
    private void addBook(BookShelf bookShelf, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        String summary = jsonObject.getString("summary");
        Book newBook = new Book(name, author, summary);
        bookShelf.addBook(newBook);
    }
}
