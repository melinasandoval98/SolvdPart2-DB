package com.solvd.computerrepairservice.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.solvd.computerrepairservice.model.Computer;
import com.solvd.computerrepairservice.model.EmployeeID;
import com.solvd.computerrepairservice.model.User;

@XmlRootElement(name = "computerForRepair")
@XmlType(propOrder = { "computerForRepairID", "computer", "computerOwner", "computerRepairers" })
public class ComputerForRepair {
	private long computerForRepairID;
	private Computer computer;
	private User computerOwner;
	private List<EmployeeID> computerRepairers = new ArrayList<>();

	public ComputerForRepair() {

	}

	public ComputerForRepair(long computerForRepairID, Computer computerForRepair, User computerOwner,
			EmployeeID computerRepairer) {
		super();
		this.computerForRepairID = computerForRepairID;
		this.computer = computerForRepair;
		this.computerOwner = computerOwner;
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

	public void setComputer(Computer computerForRepair) {
		this.computer = computerForRepair;
	}

	@XmlElement(name = "computerOwner")
	public User getComputerOwner() {
		return computerOwner;
	}

	public void setComputerOwner(User computerOwner) {
		this.computerOwner = computerOwner;
	}

	@XmlElementWrapper(name = "computerRepairers")
	@XmlElement(name = "computerRepairer", type = EmployeeID.class)
	public List<EmployeeID> getComputerRepairers() {
		return computerRepairers;
	}

	public void setComputerRepairers(List<EmployeeID> computerRepairers) {
		this.computerRepairers = computerRepairers;
	}

	@Override
	public String toString() {
		return "ComputerForRepair [computerForRepairID=" + computerForRepairID + ", computer=" + computer
				+ ", computerOwner=" + computerOwner + ", entryDate=" + ", computerRepairers=" + computerRepairers
				+ "]";
	}

}
