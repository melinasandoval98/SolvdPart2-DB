package com.solvd.computerrepairservice.model;

public class WorkSchedule {
    private long id;
    private WorkingDays workingDay;
    private Shifts shift;

    public WorkSchedule() {
    }

    public WorkSchedule(long id, WorkingDays workingDay, Shifts shift) {
        super();
        this.id = id;
        this.workingDay = workingDay;
        this.shift = shift;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WorkingDays getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(WorkingDays workingDay) {
        this.workingDay = workingDay;
    }

    public Shifts getShift() {
        return shift;
    }

    public void setShift(Shifts shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "WorkSchedule [id=" + id + ", workingDay=" + workingDay + ", shift=" + shift + "]";
    }

}