package com.solvd.computerrepairservice.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "computerForRepairDiagnosis")
@XmlType(propOrder = {"computerForRepairDiagnosisID", "computerForRepairID", "problemSubTypeID",})
public class ComputerForRepairDiagnosis {
    private long computerForRepairDiagnosisID;
    private long computerForRepairID;
    private long problemSubTypeID;


    public ComputerForRepairDiagnosis() {
    }

    public ComputerForRepairDiagnosis(long computerForRepairDiagnosisID, long computerForRepairID, long problemSubTypeID) {
        this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
        this.computerForRepairID = computerForRepairID;
        this.problemSubTypeID = problemSubTypeID;
    }

    public long getComputerForRepairDiagnosisID() {
        return computerForRepairDiagnosisID;
    }

    @XmlAttribute(name = "computerForRepairDiagnosisID")
    public void setComputerForRepairDiagnosisID(long computerForRepairDiagnosisID) {
        this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
    }

    public long getComputerForRepairID() {
        return computerForRepairID;
    }

    @XmlElement(name = "computerForRepairID")
    public void setComputerForRepairID(long computerForRepairID) {
        this.computerForRepairID = computerForRepairID;
    }

    public long getProblemSubTypeID() {
        return problemSubTypeID;
    }

    @XmlElement(name = "problemSubTypeID")
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
