package domain;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import domain.Guest;

public class GuestList {
    private Map<String, Guest> guests;

    public GuestList() {
        this.guests = new HashMap<>();
    }

    public void addGuest(Guest guest) {
        this.guests.put(guest.getName(), guest);
    }

    // reports using lambdas
    public List<Guest> getGuestsWithReservedSeats() {
        return guests.values().stream()
                .filter(Guest::hasReservedSeat)
                .collect(Collectors.toList());
    }

    public List<Guest> getGuestsWithCanceledReservations() {
        return guests.values().stream()
                .filter(Guest::hasCanceledReservation)
                .collect(Collectors.toList());
    }

    //stream
    public void showGuestsAndReservations() {
        guests.values()
                .forEach(guest -> {
                    System.out.print("Guest: " + guest.getName() + " / ");
                    guest.showReservations();
                });
    }

    public String getGuestNameByNumber(int number) {
        int i = 1;
        for (Guest guest : guests.values()) {
            if (i == number) {
                return guest.getName();
            }
            i++;
        }
        return null;
    }

    public Collection<Guest> getGuests() {
        return this.guests.values();
    }

    public Guest getGuest(String name) {
        return this.guests.get(name);
    }

    public void removeGuest(String name) {
        this.guests.remove(name);
    }
}
