package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "user")
@XmlType(propOrder = {"userID", "userName", "userEMail", "userPhoneNumber", "userAddress"})
public class User {
    private long userID;
    private String userName;
    private String userEMail;
    private PhoneNumber userPhoneNumber;
    private Address userAddress;

    public User() {

    }

    public User(long userID, String userName, String userEMail, PhoneNumber userPhoneNumber,
                Address userAddress) {
        this.userID = userID;
        this.userName = userName;
        this.userEMail = userEMail;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
    }

    public User(long userID, String userName, Integer userAge, String userEMail) {
        this.userID = userID;
        this.userName = userName;
        this.userEMail = userEMail;
    }

    public long getUserID() {
        return userID;
    }

    @XmlAttribute(name = "userID")
    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    @XmlElement(name = "userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEMail() {
        return userEMail;
    }

    @XmlElement(name = "userEMail")
    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }

    public PhoneNumber getUserPhoneNumber() {
        return userPhoneNumber;
    }

    @XmlElement(name = "userPhoneNumber")
    public void setUserPhoneNumber(PhoneNumber userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    @XmlElement(name = "userAddress")
    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userEMail='" + userEMail + '\'' +
                ", userPhoneNumber=" + userPhoneNumber +
                ", userAddress=" + userAddress +
                '}';
    }
}
