package service;

public class Theater {
    private Seat[][] seats;

    public Theater() {
        seats = new Seat[10][10];
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                seats[i-1][j-1] = new Seat(i, j);
            }
        }
    }

    public Seat getSeat(int row, int col) {
        return seats[row-1][col-1];
    }

    public boolean isSeatAvailable(int row, int col) {
        return !seats[row][col].isReserved(); // return true is available
    }

    public void reserveSeat(int row, int col, Movie movie) {
        if (isSeatAvailable(row, col)) {
            seats[row][col].reserve();
            System.out.println("Seat reserved successfully.");
        } else {
            System.out.println("Seat is already reserved.");
        }
    }

    public void cancelReservation(int row, int col) {
        seats[row][col].cancelReservation();
    }

    public void updateSeat(int row, int col, char status) {
        seats[row][col].setStatus(status);
    }

    public void displayTheater() {
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (isSeatAvailable(i-1, j-1)) {
                    System.out.print("O "); // O represents an available seat
                } else {
                    System.out.print("X "); // X represents a reserved seat
                }
            }
            System.out.println();
        }
    }
}
