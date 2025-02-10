package service;

public class Seat {
    private int row;
    private int column;
    private boolean isReserved;
    private char status;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.isReserved = false;
    }

    public void reserve() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    public String getRow() {
        return row + "";
    }

    public String getColumn() {
        return column + "";
    }

}
