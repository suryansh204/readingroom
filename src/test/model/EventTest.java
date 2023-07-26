package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Event a1;
    private Event a2;
    private Event a3;
    private int x = 100;
    private Date d;
    private Date d1;
    private Date d2;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added a book");   // (1)
        d = Calendar.getInstance().getTime();// (2)
    }

    @Test
    public void testEvent() {

        d1 = new Date(String.valueOf(d));
        d2= new Date(String.valueOf(e.getDate()));

        //d1=d;
        //d2=e.getDate();

        assertEquals("Added a book", e.getDescription());
        assertEquals(d1,d2);
//        System.out.println(d1);
//        System.out.println(d2);
    }

    @Test
    public void testToString() {

        assertEquals(d.toString() + "\n" + "Added a book", e.toString());
    }

    @Test
    public void testEqualsAndHash() {
        a1 = new Event("Added a book");
        a2 = new Event("Added a book");
        a3 = new Event("deleted a book");
        Event e4 = null;


        assertNotEquals(a1, a3);  // Same time, Different description
        assertNotEquals(a1, x);   // Comparing with a different class
        assertEquals(a1, a2);     // Same time, Same description


        assertNotEquals(a1, e4);   // Comparing with null

        e4 = new Event("Added a book");
        assertNotEquals(a1, e4);  // Different time, Same description
        assertNotEquals(e4, a3);  // Different time, Different description




        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1.hashCode(), a3.hashCode());
    }
}

