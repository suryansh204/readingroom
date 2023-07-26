package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//represents a list of Book.
public class BookShelf implements Writable {

    private List<Book> books;
    private List<Book> booksRead;
    String removedName;
    private EventLog eventLog;

    //EFFECTS: constructs a bookshelf.
    public BookShelf() {
        books = new ArrayList<>();
        eventLog = EventLog.getInstance();
    }

    public List<Book> getBooks() {
        return books;
    }

    //MODIFIES: books
    //EFFECTS: adds a book to the list books.
    public void addBook(Book book) {

        books.add(book);
        eventLog.logEvent(new Event("Book added to shelf: " + book.getName()));
    }


    //MODIFIES: books
    //EFFECTS: removes a book from the list books.
    public String removeBook(String str) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().equals(str)) {
                removedName = books.get(i).getName();
                books.remove(books.get(i));
                eventLog.logEvent(new Event("Book removed from the shelf:" + removedName));
                return removedName + " removed";
            }
        }

        return "Book not found!";
    }


    //EFFECTS: searches for a book in books and returns book name, author and summary.
    public String findBook(String str) {
        for (Book b : books) {
            if (b.getName().equals(str)) {
                return "Book name: " + b.getName() + "\n"
                        + "Author: " + b.getAuthor() + "\n"
                        + "Summary: " + b.getSummary();
            }

        }

        return "Book not found!";

    }


    //MODIFIES: this, book
    //EFFECTS: edits a book's summary after it has been stored.
    public String editBookSummary(String name, String sum) {
        for (Book b : books) {
            if (b.getName().equals(name)) {
                b.setSummary(sum);
                eventLog.logEvent(new Event(b.getName() + " summary edited!"));
                return "summary edited!";
            }
        }
        return "Book not found!";
    }


    //EFFECTS: sorts all the books in ascending by their titles.
    public ArrayList<String> sortBooksByTitle() {
        ArrayList<String> bookName = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        for (Book b : books) {
            bookName.add(b.getName());
        }
        Collections.sort(bookName);
        eventLog.logEvent(new Event("Books sorted"));

        for (String b : bookName) {
            name.add(b);

        }
        return name;
    }


    //EFFECTS: returns the name of all books stored in the list books.
    public List<String> printBooks() {
        ArrayList<String> name = new ArrayList<>();
        for (Book s : books) {
            name.add(s.getName());
        }
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns things in this bookshelf as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }


}
