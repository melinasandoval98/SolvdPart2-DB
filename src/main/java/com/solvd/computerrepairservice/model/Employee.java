package com.solvd.computerrepairservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private long employeeID;
    private User user;
    private long employeeIdentificationNumber;
    private Date employeeDateOfBirth;
    private Genders employeeGender;

    private List<ComputerForRepair> userComputersForRepair = new ArrayList<>();

    public Employee() {

    }

    public Employee(long id, User user, long employeeIdentificationNumber, Date dateOfBirth, Genders gender, List<ComputerForRepair> userComputersForRepair) {
        this.employeeID = id;
        this.user = user;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.employeeDateOfBirth = dateOfBirth;
        this.employeeGender = gender;
        this.userComputersForRepair = userComputersForRepair;
    }

    public Employee(long id, long employeeIdentificationNumber, Date dateOfBirth) {
        this.employeeID = id;
        this.employeeIdentificationNumber = employeeIdentificationNumber;
        this.employeeDateOfBirth = dateOfBirth;
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getEmployeeIdentificationNumber() {
        return employeeIdentificationNumber;
    }

    public void setEmployeeIdentificationNumber(long employeeIdentificationNumber) {
        this.employeeIdentificationNumber = employeeIdentificationNumber;
    }

    public Date getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(Date employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public Genders getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Genders employeeGender) {
        this.employeeGender = employeeGender;
    }

    public List<ComputerForRepair> getUserComputersForRepair() {
        return userComputersForRepair;
    }

    public void setUserComputersForRepair(List<ComputerForRepair> userComputersForRepair) {
        this.userComputersForRepair = userComputersForRepair;
    }
}
