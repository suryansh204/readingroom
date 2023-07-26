package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a book which includes title, author and a summary.
public class Book implements Writable {

    private String name;
    private String author;
    private String summary;

    //EFFECTS - A book which stores the book name, author name and a summary of the book.
    public Book(String name, String author, String summary) {
        this.name = name;
        this.author = author;
        this.summary = summary;
    }


    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }


    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }



    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("author", author);
        json.put("summary", summary);
        return json;
    }

}
