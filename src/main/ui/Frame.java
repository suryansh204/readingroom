package ui;

import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//A class which extends JFrame to have a graphical user interface for the code
public class Frame extends JFrame implements ActionListener {

    JButton searchButton;
    JLabel showDisplayLabel;
    ReadingRoom readingRoom;
    JTextField searchTextField;
    JButton but;
    JButton addBook;
    JButton deleteBook;
    JButton sortBooks;
    JButton viewShelf;
    JButton saveBookshelf;
    JButton editSummary;
    JPanel searchPanel;
    JPanel bookPanel;
    JTextField nameTextField;
    JTextField authorTextField;
    JTextField summaryTextField;
    JButton summBut;
    JPanel deletePanel;
    JTextField deleteTextField;
    JButton del;
    JLabel delLabel;
    JPanel sortPanel;
    JLabel sortLabel;
    JPanel shelfPanel;
    String sortLabelText;
    JLabel shelfLabel;
    JPanel summaryPanel;
    JTextField nameField;
    JTextField summaryField;
    JButton sum;
    JLabel summaryLabel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel dellabel1;
    JLabel searchLabel1;
    JLabel sumlabel2;
    JLabel sumlabel1;
    JLabel eventLabel;


    // Constructs a panel
    // effects:  sets size and background colour of panel,
    //           updates this with the books to be displayed

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    Frame() {
        readingRoom = new ReadingRoom();
        //readingRoom.add("Book", "Author", "Summary");

        try {
            JLabel bgImg = new JLabel(new ImageIcon(ImageIO.read(new File("src/bookshelf.png"))));
            this.setContentPane(bgImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.buttons();
        this.addSearchPanel();
        this.addBookPanel();
        this.addDeleteBookPanel();
        this.addSortBooksPanel();
        this.addViewShelfPanel();
        this.addEditSummaryPanel();
        // this.textFields();


        this.setTitle("The Reading Room");
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                displayLog(EventLog.getInstance());
                //THEN you can exit the program
                System.exit(0);
            }
        });
        //this.setLayout(new FlowLayout());
        this.setLayout(null);
        this.setSize(1000, 1000);

        this.getContentPane().setBackground(new Color(0, 0, 0));

        this.add(searchButton);
        this.add(addBook);
        this.add(deleteBook);
        this.add(sortBooks);
        this.add(viewShelf);
        this.add(editSummary);
        this.add(saveBookshelf);
        //this.add(searchTextField);

        this.setVisible(true);

        int result = JOptionPane.showConfirmDialog(this, "Do you wish to load?", "Load",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            readingRoom.load();
        }


    }

    private void displayLog(EventLog eventLog) {
        for (Event next : eventLog) {
            System.out.println(next.toString() + "\n");
        }


    }

    //MODIFIES: this, showDisplayLabel
    //EFFECTS: Creates a panel and displays name, author and summary of the book.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setBounds(200, 200, 600, 600);
        searchPanel.setLayout(null);

        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(250, 40));
        searchTextField.setBounds(10, 20, 250, 50);

        but = new JButton("submit");
        but.addActionListener(this);
        but.setBounds(260, 30, 80, 30);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                showDisplayLabel.setText(readingRoom.searchBook(searchTextField.getText()) + "\n");
                searchTextField.setText("");

            }
        });

        showDisplayLabel = new JLabel();

        showDisplayLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        showDisplayLabel.setBounds(10, 100, 1000, 100);

        searchLabel1 = new JLabel("NAME:");
        searchLabel1.setBounds(15, -35, 1000, 100);
        searchLabel1.setLayout(null);


        searchPanel.add(searchTextField);
        searchPanel.add(but);
        searchPanel.add(showDisplayLabel);
        searchPanel.add(searchLabel1);
        searchPanel.setVisible(false);


        add(searchPanel);

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    //MODIFIES: this, Bookshelf
    //EFFECTS: Creates a panel for display and adds a book to the bookshelf.
    private void addBookPanel() {
        bookPanel = new JPanel();
        bookPanel.setBounds(200, 200, 600, 600);
        bookPanel.setLayout(null);

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(250, 40));
        nameTextField.setBounds(10, 20, 250, 50);

        authorTextField = new JTextField();
        authorTextField.setPreferredSize(new Dimension(250, 40));
        authorTextField.setBounds(10, 90, 250, 50);


        summaryTextField = new JTextField();
        summaryTextField.setPreferredSize(new Dimension(250, 40));
        summaryTextField.setBounds(10, 160, 250, 50);

        summBut = new JButton("submit");
        summBut.addActionListener(this);
        summBut.setBounds(93, 210, 80, 30);
        summBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readingRoom.add(nameTextField.getText(), authorTextField.getText(), summaryTextField.getText());
                bookPanel.setVisible(false);
                nameTextField.setText("");
                authorTextField.setText("");
                summaryTextField.setText("");
            }
        });

        label1 = new JLabel("NAME:");
        label1.setBounds(15, -35, 1000, 100);
        label1.setLayout(null);

        label2 = new JLabel("AUTHOR:");
        label2.setBounds(15, 35, 1000, 100);
        label2.setLayout(null);

        label3 = new JLabel("SUMMARY:");
        label3.setBounds(15, 105, 1000, 100);
        label3.setLayout(null);


        bookPanel.add(nameTextField);
        bookPanel.add(authorTextField);
        bookPanel.add(summaryTextField);
        bookPanel.add(summBut);
        bookPanel.add(label1);
        bookPanel.add(label2);
        bookPanel.add(label3);

        bookPanel.setVisible(false);

        add(bookPanel);


    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    //MODIFIES: Bookshelf, this
    //EFFECTS: Creates a panel for display and deletes a book to the bookshelf.
    private void addDeleteBookPanel() {
        deletePanel = new JPanel();
        deletePanel.setBounds(200, 200, 600, 600);
        deletePanel.setLayout(null);

        deleteTextField = new JTextField();
        deleteTextField.setPreferredSize(new Dimension(250, 40));
        deleteTextField.setBounds(10, 20, 250, 50);

        del = new JButton("submit");
        del.addActionListener(this);
        del.setBounds(260, 30, 80, 30);
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delLabel.setText(readingRoom.delete(deleteTextField.getText()));
                deleteTextField.setText("");

            }
        });

        delLabel = new JLabel();
        delLabel.setBounds(10, 100, 1000, 100);
        delLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        dellabel1 = new JLabel("NAME:");
        dellabel1.setBounds(15, -35, 1000, 100);
        dellabel1.setLayout(null);

        deletePanel.add(deleteTextField);
        deletePanel.add(del);
        deletePanel.add(delLabel);
        deletePanel.add(dellabel1);

        deletePanel.setVisible(false);

        add(deletePanel);

    }

    //MODIFIES: sortLabel
    //EFFECTS: displays a panel gives a sorted list of all the book names in the bookshelf.
    private void addSortBooksPanel() {

        sortPanel = new JPanel();
        sortPanel.setBounds(200, 200, 600, 600);
        sortPanel.setLayout(null);

        sortLabel = new JLabel();
        sortLabel.setBounds(10, 100, 1000, 100);


        sortPanel.setVisible(false);

        sortPanel.add(sortLabel);
        add(sortPanel);

    }

    //MODIFIES: shelfLabel
    //EFFECTS: Creates a panel for display list of all books in the bookshelf.
    private void addViewShelfPanel() {

        shelfPanel = new JPanel();
        shelfPanel.setBounds(200, 200, 600, 600);
        shelfPanel.setLayout(null);

        shelfLabel = new JLabel();
        shelfLabel.setBounds(10, 100, 1000, 100);

        shelfPanel.setVisible(false);

        shelfPanel.add(shelfLabel);
        add(shelfPanel);

    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    //MODIFIES: summary of a book, summaryLabel
    //EFFECTS: displays a panel and edits summary of a book
    private void addEditSummaryPanel() {
        summaryPanel = new JPanel();
        summaryPanel.setBounds(200, 200, 600, 600);
        summaryPanel.setLayout(null);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 40));
        nameField.setBounds(10, 20, 250, 50);

        summaryField = new JTextField();
        summaryField.setPreferredSize(new Dimension(250, 40));
        summaryField.setBounds(10, 90, 250, 50);

        sum = new JButton("submit");
        sum.addActionListener(this);
        sum.setBounds(90, 140, 80, 30);
        sum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                summaryLabel.setText(readingRoom.summary(nameField.getText(), summaryField.getText()));
                nameField.setText("");
                summaryField.setText("");

            }
        });

        summaryLabel = new JLabel();
        summaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        summaryLabel.setBounds(10, 200, 1000, 100);

        sumlabel1 = new JLabel("NAME:");
        sumlabel1.setBounds(15, -35, 1000, 100);
        sumlabel1.setLayout(null);

        sumlabel2 = new JLabel("SUMMARY:");
        sumlabel2.setBounds(15, 35, 1000, 100);
        sumlabel2.setLayout(null);

        summaryPanel.add(nameField);
        summaryPanel.add(summaryField);
        summaryPanel.add(sum);
        summaryPanel.add(summaryLabel);
        summaryPanel.add(sumlabel1);
        summaryPanel.add(sumlabel2);

        summaryPanel.setVisible(false);

        add(summaryPanel);


    }

    private void displayLogEventsPanel() {
        summaryPanel = new JPanel();
        summaryPanel.setBounds(200, 200, 300, 300);
        summaryPanel.setLayout(null);

        eventLabel = new JLabel();
        eventLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        eventLabel.setBounds(10, 200, 1000, 100);

        //eventLabel.setText();

    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    //EFFECTS: Displays all the buttons
    private void buttons() {
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(10, 200, 130, 80);
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);


        addBook = new JButton("ADD BOOk");
        addBook.setBounds(10, 250, 130, 80);
        addBook.addActionListener(this);
        addBook.setFocusable(false);

        deleteBook = new JButton("DELETE BOOK");
        deleteBook.setBounds(10, 300, 130, 80);
        deleteBook.addActionListener(this);
        deleteBook.setFocusable(false);

        sortBooks = new JButton("SORT BOOKS");
        sortBooks.setBounds(10, 350, 130, 80);
        sortBooks.addActionListener(this);
        sortBooks.setFocusable(false);

        viewShelf = new JButton("VIEW BOOKSHELF");
        viewShelf.setBounds(10, 400, 130, 80);
        viewShelf.addActionListener(this);
        viewShelf.setFocusable(false);

        editSummary = new JButton("EDIT SUMMARY");
        editSummary.setBounds(10, 450, 130, 80);
        editSummary.addActionListener(this);
        editSummary.setFocusable(false);

        saveBookshelf = new JButton("SAVE");
        saveBookshelf.setBounds(10, 500, 130, 80);
        saveBookshelf.addActionListener(this);
        saveBookshelf.setFocusable(false);
    }

    //EFFECTS: sets all the panels to false.
    private void panels() {
        searchPanel.setVisible(false);
        bookPanel.setVisible(false);
        deletePanel.setVisible(false);
        sortPanel.setVisible(false);
        shelfPanel.setVisible(false);
        summaryPanel.setVisible(false);

    }

    //EFFECTS: calls appropriate methods when a button is pressed.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == searchButton) {
            panels();
            showDisplayLabel.setText("");
            searchPanel.setVisible(true);

        } else if (e.getSource() == addBook) {
            panels();
            bookPanel.setVisible(true);


        } else if (e.getSource() == deleteBook) {
            panels();
            delLabel.setText("");
            deletePanel.setVisible(true);


        } else if (e.getSource() == sortBooks) {
            panels();
            sortLabelText = String.join(",", readingRoom.sortTitle());
            sortLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            sortLabel.setText(sortLabelText);
            sortPanel.setVisible(true);


        } else if (e.getSource() == viewShelf) {
            panels();
            String shelfText = String.join(", ", readingRoom.viewShelf());
            shelfLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            shelfLabel.setText(shelfText);
            shelfPanel.setVisible(true);


        } else if (e.getSource() == editSummary) {
            panels();
            summaryLabel.setText("");
            summaryPanel.setVisible(true);


        } else if (e.getSource() == saveBookshelf) {
            readingRoom.save();
        }


    }


}
