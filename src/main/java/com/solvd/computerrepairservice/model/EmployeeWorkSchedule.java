package com.solvd.computerrepairservice.model;

public class EmployeeWorkSchedule {
	private long id;
	private Employee employee;
	private WorkSchedule workSchedule;

	public EmployeeWorkSchedule(long id, Employee employeeID, WorkSchedule workSchedule) {
		super();
		this.id = id;
		this.employee = employeeID;
		this.workSchedule = workSchedule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public WorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(WorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	@Override
	public String toString() {
		return "EmployeeWorkSchedule [id=" + id + ", employeeID=" + employee + ", workSchedule=" + workSchedule + "]";
	}

}
