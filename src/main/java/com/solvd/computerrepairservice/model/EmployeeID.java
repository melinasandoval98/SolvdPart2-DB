package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlRootElement(name = "computerRepairer")
//@XmlType(propOrder = { "id", "employeeID", "userEmployee" })
public class EmployeeID {
	private long id;
	private long employeeID;
	private User userEmployee;

	public EmployeeID() {

	}

	public EmployeeID(long id, long employeeID, User userEmployee) {
		super();
		this.id = id;
		this.employeeID = employeeID;
		this.userEmployee = userEmployee;
	}

//	@XmlAttribute(name = "id")
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

	public User getUserEmployee() {
		return userEmployee;
	}

	public void setUserEmployee(User userEmployee) {
		this.userEmployee = userEmployee;
	}

	@Override
	public String toString() {
		return "EmployeeID [id=" + id + ", employeeID=" + employeeID + ", userEmployee=" + userEmployee + "]";
	}

}
