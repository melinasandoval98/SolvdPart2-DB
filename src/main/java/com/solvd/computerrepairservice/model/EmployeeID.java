package com.solvd.computerrepairservice.model;

public class EmployeeID {
	private long id;
	private long employeeID;
	private long userID;

	public EmployeeID() {

	}

	public EmployeeID(long id, long employeeID, long userID) {
		super();
		this.id = id;
		this.employeeID = employeeID;
		this.userID = userID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "EmployeeID [id=" + id + ", employeeID=" + employeeID + ", userID=" + userID + "]";
	}

}
