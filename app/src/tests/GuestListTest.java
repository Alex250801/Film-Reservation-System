package tests;

import domain.GuestList;
import domain.Guest;
import org.junit.Test;
import service.MovieRepository;
import service.Theater;

import java.util.Scanner;

import static org.junit.Assert.*;

public class GuestListTest {

    @Test
    public void testAddGuest() {
        GuestList guestList = new GuestList();
        Guest guest = new Guest("John Doe");
        guestList.addGuest(guest);
        assertTrue(guestList.getGuests().contains(guest));
    }

    @Test
    public void testGetGuest() {
        GuestList guestList = new GuestList();
        Guest guest = new Guest("John Doe");
        guestList.addGuest(guest);
        assertEquals(guest, guestList.getGuest("John Doe"));
    }

    @Test
    public void testRemoveGuestByName() {
        GuestList guestList = new GuestList();
        Guest guest = new Guest("John Doe");
        guestList.addGuest(guest);
        assertEquals(1, guestList.getGuests().size());
        guestList.removeGuest("John Doe");
        assertEquals(0, guestList.getGuests().size());
    }

    @Test
    public void testGetGuestsWithReservedSeats() {
        GuestList guestList = new GuestList();
        Guest guest1 = new Guest("John Doe");
        guest1.reserveSeat();
        Guest guest2 = new Guest("Jane Doe");
        guestList.addGuest(guest1);
        guestList.addGuest(guest2);
        assertEquals(1, guestList.getGuestsWithReservedSeats().size());
    }

}
