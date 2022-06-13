package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.List;

public class ComputerForRepairDiagnosis {
    private long computerForRepairDiagnosisID;
    private long computerForRepairID;
    private long problemSubTypeID;

    public ComputerForRepairDiagnosis(long computerForRepairDiagnosisID, long computerForRepairID, long problemSubTypeID) {
        this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
        this.computerForRepairID = computerForRepairID;
        this.problemSubTypeID = problemSubTypeID;
    }

    public long getComputerForRepairDiagnosisID() {
        return computerForRepairDiagnosisID;
    }

    public void setComputerForRepairDiagnosisID(long computerForRepairDiagnosisID) {
        this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
    }

    public long getComputerForRepairID() {
        return computerForRepairID;
    }

    public void setComputerForRepairID(long computerForRepairID) {
        this.computerForRepairID = computerForRepairID;
    }

    public long getProblemSubTypeID() {
        return problemSubTypeID;
    }

    public void setProblemSubTypeID(long problemSubTypeID) {
        this.problemSubTypeID = problemSubTypeID;
    }

    @Override
    public String toString() {
        return "ComputerForRepairDiagnosis{" +
                "computerForRepairDiagnosisID=" + computerForRepairDiagnosisID +
                ", computerForRepairID=" + computerForRepairID +
                ", problemSubTypeID=" + problemSubTypeID +
                '}';
    }
}
