package com.solvd.computerrepairservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ComputerForRepair {
	private long computerForRepairID;
	private Computer computer;
	private Date entryDate;
	private long clientID;
	private long repairerID;
	private List<ComputerProblemSubType> computerSubTypesProblems = new ArrayList<>();

	public ComputerForRepair() {

	}

	public ComputerForRepair(long computerForRepairID, Computer computer, Date entryDate,
			List<ComputerProblemSubType> computerSubTypesProblems) {
		super();
		this.computerForRepairID = computerForRepairID;
		this.computer = computer;
		this.entryDate = entryDate;
		this.computerSubTypesProblems = computerSubTypesProblems;
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

	public List<ComputerProblemSubType> getComputerSubTypesProblems() {
		return computerSubTypesProblems;
	}

	public void setComputerSubTypesProblems(List<ComputerProblemSubType> computerSubTypesProblems) {
		this.computerSubTypesProblems = computerSubTypesProblems;
	}

	public long getclientID() {
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

}
