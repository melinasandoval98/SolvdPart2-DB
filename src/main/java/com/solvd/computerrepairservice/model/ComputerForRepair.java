package com.solvd.computerrepairservice.model;

import com.solvd.computerrepairservice.jaxb.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "computerForRepair")
@XmlType(propOrder = {"computerForRepairID", "computer", "entryDate", "clientID", "repairerID", "computerForRepairDiagnoses"})
public class ComputerForRepair {
    private long computerForRepairID;
    private Computer computer;
    private Date entryDate;
    private long clientID;
    private long repairerID;
    private List<ComputerForRepairDiagnosis> computerForRepairDiagnoses = new ArrayList<>();

    public ComputerForRepair() {

    }

    public ComputerForRepair(long computerForRepairID, Computer computer, Date entryDate, long clientID, long repairerID, List<ComputerForRepairDiagnosis> computerForRepairDiagnosis) {
        this.computerForRepairID = computerForRepairID;
        this.computer = computer;
        this.entryDate = entryDate;
        this.clientID = clientID;
        this.repairerID = repairerID;
        this.computerForRepairDiagnoses = computerForRepairDiagnosis;
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

    @XmlAttribute(name = "computerForRepairID")
    public void setComputerForRepairID(long computerForRepairID) {
        this.computerForRepairID = computerForRepairID;
    }

    public Computer getComputer() {
        return computer;
    }

    @XmlElement(name = "computer")
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "entryDate", required = true)
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public long getClientID() {
        return clientID;
    }

    @XmlElement(name = "clientID")
    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getRepairerID() {
        return repairerID;
    }

    @XmlElement(name = "repairerID")
    public void setRepairerID(long repairerID) {
        this.repairerID = repairerID;
    }

    public List<ComputerForRepairDiagnosis> getComputerForRepairDiagnoses() {
        return computerForRepairDiagnoses;
    }

    @XmlElementWrapper(name = "computerForRepairDiagnoses")
    @XmlElement(name = "computerForRepairDiagnosis")
    public void setComputerForRepairDiagnoses(List<ComputerForRepairDiagnosis> computerForRepairDiagnoses) {
        this.computerForRepairDiagnoses = computerForRepairDiagnoses;
    }

    @Override
    public String toString() {
        return "ComputerForRepair{" +
                "computerForRepairID=" + computerForRepairID +
                ", computer=" + computer +
                ", entryDate=" + entryDate +
                ", clientID=" + clientID +
                ", repairerID=" + repairerID +
                ", computerForRepairDiagnoses=" + computerForRepairDiagnoses +
                '}';
    }
}
