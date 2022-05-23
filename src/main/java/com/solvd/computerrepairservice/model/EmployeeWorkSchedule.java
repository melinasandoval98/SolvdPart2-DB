package com.solvd.computerrepairservice.model;

public class EmployeeWorkSchedule {
	private long id;
	private EmployeeID employeeID;
	private WorkSchedule workSchedule;

	public EmployeeWorkSchedule(long id, EmployeeID employeeID, WorkSchedule workSchedule) {
		super();
		this.id = id;
		this.employeeID = employeeID;
		this.workSchedule = workSchedule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EmployeeID getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(EmployeeID employeeID) {
		this.employeeID = employeeID;
	}

	public WorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(WorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	@Override
	public String toString() {
		return "EmployeeWorkSchedule [id=" + id + ", employeeID=" + employeeID + ", workSchedule=" + workSchedule + "]";
	}

}
