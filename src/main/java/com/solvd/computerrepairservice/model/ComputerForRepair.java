package com.solvd.computerrepairservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.computerrepairservice.jaxb.DateAdapter;

@XmlRootElement(name = "computerForRepair")
@XmlType(propOrder = {"computerForRepairID", "computer", "clientID", "repairerID", "entryDate"})
public class ComputerForRepair {
    private long computerForRepairID;
    private Computer computer;
    private Date entryDate;
    private long clientID;
    private long repairerID;
    private List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = new ArrayList<>();

    public ComputerForRepair() {

    }

    public ComputerForRepair(long computerForRepairID, Computer computer, Date entryDate, long clientID, long repairerID, List<ComputerForRepairDiagnosis> computerForRepairDiagnosis) {
        this.computerForRepairID = computerForRepairID;
        this.computer = computer;
        this.entryDate = entryDate;
        this.clientID = clientID;
        this.repairerID = repairerID;
        this.computerForRepairDiagnosis = computerForRepairDiagnosis;
    }

    public ComputerForRepair(long computerForRepairID, Date entryDate, long clientID, long repairerID) {
        this.computerForRepairID = computerForRepairID;
        this.entryDate = entryDate;
        this.clientID = clientID;
        this.repairerID = repairerID;
    }

    public long getComputerForRepairID() {
        return computerForRepairID;
    }

    public void setComputerForRepairID(long computerForRepairID) {
        this.computerForRepairID = computerForRepairID;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getRepairerID() {
        return repairerID;
    }

    public void setRepairerID(long repairerID) {
        this.repairerID = repairerID;
    }

    public List<ComputerForRepairDiagnosis> getComputerForRepairDiagnosis() {
        return computerForRepairDiagnosis;
    }

    public void setComputerForRepairDiagnosis(List<ComputerForRepairDiagnosis> computerForRepairDiagnosis) {
        this.computerForRepairDiagnosis = computerForRepairDiagnosis;
    }
}
