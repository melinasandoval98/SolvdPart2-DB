package com.solvd.computerrepairservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.computerrepairservice.jaxb.DateAdapter;

@XmlRootElement(name = "computerForRepair")
@XmlType(propOrder = { "computerForRepairID", "computer", "clientID", "repairerID", "entryDate" })
public class ComputerForRepair {
	private long computerForRepairID;
	private Computer computer;
	private Date entryDate;
	private long clientID;
	private long repairerID;

	public ComputerForRepair() {

	}

	public ComputerForRepair(long computerForRepairID, Computer computer, Date entryDate, long clientID,
			long repairerID) {
		super();
		this.computerForRepairID = computerForRepairID;
		this.computer = computer;
		this.entryDate = entryDate;
		this.clientID = clientID;
		this.repairerID = repairerID;
	}

	@XmlAttribute(name = "computerForRepairID")
	public long getComputerForRepairID() {
		return computerForRepairID;
	}

	public void setComputerForRepairID(long computerForRepairID) {
		this.computerForRepairID = computerForRepairID;
	}

	@XmlElement(name = "computer")
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	@XmlElement(name = "entryDate")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@XmlElement(name = "clientID")
	public long getClientID() {
		return clientID;
	}

	public void setClientID(long clientID) {
		this.clientID = clientID;
	}

	@XmlElement(name = "repairerID")
	public long getRepairerID() {
		return repairerID;
	}

	public void setRepairerID(long repairerID) {
		this.repairerID = repairerID;
	}

	@Override
	public String toString() {
		return "ComputerForRepair [computerForRepairID=" + computerForRepairID + ", computer=" + computer
				+ ", entryDate=" + entryDate + ", clientID=" + clientID + ", repairerID=" + repairerID + "]";
	}

}
