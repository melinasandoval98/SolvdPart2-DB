package com.solvd.computerrepairservice.model;

public class EmployeeWorkSchedule {
    private long id;
    private long employeeID;
    private long workScheduleID;

    public EmployeeWorkSchedule() {
    }

    public EmployeeWorkSchedule(long id, long employeeID, long workScheduleID) {
        this.id = id;
        this.employeeID = employeeID;
        this.workScheduleID = workScheduleID;
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

    public long getWorkScheduleID() {
        return workScheduleID;
    }

    public void setWorkScheduleID(long workScheduleID) {
        this.workScheduleID = workScheduleID;
    }

    @Override
    public String toString() {
        return "EmployeeWorkSchedule{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", workScheduleID=" + workScheduleID +
                '}';
    }
}
