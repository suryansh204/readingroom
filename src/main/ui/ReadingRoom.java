package ui;


import model.Book;
import model.BookShelf;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class which interacts with the user and enables them to use the app.
public class ReadingRoom {

    private Scanner input;
    BookShelf books = new BookShelf();
    final String store = "./data/bookshelf.json";
    JsonWriter wj = new JsonWriter(store);
    JsonReader reader = new JsonReader(store);

    // constructs readingroom
    public ReadingRoom() {
        input = new Scanner(System.in);
    }

    public void save() {
        try {
            wj.open();
            wj.write(books);
            wj.close();
        } catch (FileNotFoundException a) {
            //
        }
    }

    public void load() {
        try {
            books = reader.read();

        } catch (IOException e) {
            //
        }
    }


    //EFFECTS: presents user with the option to either view the bookshelf or quit.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void runApp() {
        boolean on = true;
        int option;

//        System.out.println("Do you wish to load data? (y/n)");
//        Character load = input.nextLine().charAt(0);
//        if (load.equals('y')) {
        try {
            books = reader.read();
        } catch (IOException e) {
//                System.out.println("File not found");
        }


        while (on) {
            // displayMenu();
            option = input.nextInt();

//            if (option == 2) {
//                System.out.println("Do you want to save your book? (y/n)");
//                String r = input.nextLine();
//                String response = input.nextLine();
//                if (response.equals("y")) {
            try {
                wj.open();
                wj.write(books);
                wj.close();
                System.out.println("saved my books");
            } catch (FileNotFoundException a) {
                System.out.println("unable to find books");
            }
        }

        on = false;


    }


    //EFFECTS:inputs book name and displays name, author and summary of the book.
    public String searchBook(String name) {
        return books.findBook(name);

    }


    //EFFECTS:gives a sorted list of all the book names in the bookshelf.
    public ArrayList<String> sortTitle() {

        return books.sortBooksByTitle();
    }


    //MODIFIES:books, this.
    //EFFECTS:removes a book from books.
    public String delete(String name) {
        return books.removeBook(name);
    }


    //EFFECTS:displays names of all the books in the bookshelf.
    public List<String> viewShelf() {

        return books.printBooks();
    }


    //MODIFIES:this, books
    //EFFECTS:adds a book to bookshelf
    public void add(String title, String authorName, String sum) {

        Book book1 = new Book(title, authorName, sum);
        books.addBook(book1);

    }


    //MODIFIES:this, book.
    //EFFECTS:edits summary of a book already in the bookshelf.
    public String summary(String name, String sum) {

        return books.editBookSummary(name, sum);
    }

    //EFFECTS - prints the option to either view the bookshelf or end the program.
//    private void displayMenu() {
//        System.out.println("1. bookshelf");
//        System.out.println("2. quit");
//    }
//
//    // EFFECTS - prints the menu options.
//    private void displayMenu2() {
//
//        System.out.println("select an option:");
//        System.out.println("1. add a book");
//        System.out.println("2. remove a book");
//        System.out.println("3. sort by title");
//        System.out.println("4. View all books");
//        System.out.println("5. search");
//        System.out.println("6. Edit summary");
//
//    }

}


