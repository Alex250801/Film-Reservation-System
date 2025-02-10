package tests;

import org.junit.Test;
import service.Movie;
import service.Theater;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class TheaterTest {
    @Test
    public void testReserveSeat() {
        Theater theater = new Theater();
        theater.reserveSeat(1, 1, new Movie("Movie 1"));

        // Verify that the seat is not available
        assertFalse(theater.isSeatAvailable(1, 1));
    }

    @Test
    public void testReserveSeatAlreadyReserved() {
        Theater theater = new Theater();
        theater.reserveSeat(1, 1, new Movie("Movie 1"));
        theater.reserveSeat(1, 1, new Movie("Movie 1"));
        // Verify that the seat is still reserved
        assertFalse(theater.isSeatAvailable(1, 1));
    }

    @Test
    public void testCancelReservation() {
        Theater theater = new Theater();
        theater.reserveSeat(1, 1, new Movie("Movie 1"));
        theater.cancelReservation(1, 1);
        // Verify that the seat is available again
        assertTrue(theater.isSeatAvailable(1, 1));
    }

}
