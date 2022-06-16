package com.solvd.computerrepairservice.model;

public enum ComputerProblemTypes {
    SOFTWARE_PROBLEM(1), HARDWARE_PROBLEM(2);

    private long computerProblemTypeID;
    
    private ComputerProblemTypes(long computerProblemTypeID) {
        this.computerProblemTypeID = computerProblemTypeID;
    }

    public long getComputerProblemTypeID() {
        return computerProblemTypeID;
    }

    @Override
    public String toString() {
        return "ComputerProblemTypes{" +
                "computerProblemTypeID=" + computerProblemTypeID +
                '}';
    }
}
