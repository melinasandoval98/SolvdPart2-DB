package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userPhoneNumber")
@XmlType(propOrder = {"phoneNumberID", "countryCode", "phoneNumberBody"})
public class PhoneNumber {
    private long phoneNumberID;
    private int countryCode;
    private long phoneNumberBody;

    public PhoneNumber() {

    }

    public PhoneNumber(long phoneNumberID, int countryCode, long phoneNumber) {
        super();
        this.phoneNumberBody = phoneNumberID;
        this.countryCode = countryCode;
        this.phoneNumberBody = phoneNumber;
    }

    public long getPhoneNumberID() {
        return phoneNumberID;
    }

    @XmlAttribute(name = "phoneNumberID")
    public void setPhoneNumberID(long phoneNumberID) {
        this.phoneNumberID = phoneNumberID;
    }

    public int getCountryCode() {
        return countryCode;
    }

    @XmlElement(name = "countryCode")
    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public long getPhoneNumberBody() {
        return phoneNumberBody;
    }

    @XmlElement(name = "phoneNumberBody")
    public void setPhoneNumberBody(long phoneNumberBody) {
        this.phoneNumberBody = phoneNumberBody;
    }

    @Override
    public String toString() {
        return "PhoneNumber [countryCode=" + countryCode + ", phoneNumber=" + phoneNumberBody + "]";
    }

}
