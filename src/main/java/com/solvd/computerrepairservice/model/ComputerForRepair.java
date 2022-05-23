package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComputerForRepair {
	private long computerForRepairID;
	private Computer computer;
	private User computerOwner;
	private EmployeeID computerRepairer;
	private Date entryDate;
	private List<EmployeeID> computerRepairers = new ArrayList<>();

	public ComputerForRepair() {

	}

	public ComputerForRepair(long computerForRepairID, Computer computerForRepair, User computerOwner,
			EmployeeID computerRepairer) {
		super();
		this.computerForRepairID = computerForRepairID;
		this.computer = computerForRepair;
		this.computerOwner = computerOwner;
		this.computerRepairer = computerRepairer;
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

	public void setComputer(Computer computerForRepair) {
		this.computer = computerForRepair;
	}

	public User getComputerOwner() {
		return computerOwner;
	}

	public void setComputerOwner(User computerOwner) {
		this.computerOwner = computerOwner;
	}

	public EmployeeID getComputerRepairer() {
		return computerRepairer;
	}

	public void setComputerRepairer(EmployeeID computerRepairer) {
		this.computerRepairer = computerRepairer;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public List<EmployeeID> getComputerRepairers() {
		return computerRepairers;
	}

	public void setComputerRepairers(List<EmployeeID> computerRepairers) {
		this.computerRepairers = computerRepairers;
	}

	@Override
	public String toString() {
		return "ComputerForRepair [computerForRepairID=" + computerForRepairID + ", computerForRepair=" + computer
				+ ", computerOwner=" + computerOwner + ", computerRepairer=" + computerRepairer + "]";
	}

}
