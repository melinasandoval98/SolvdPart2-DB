package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userAddress")
@XmlType(propOrder = {"addressID", "streetNumber", "streetName", "city"})
public class Address {
    private long addressID;
    private Integer streetNumber;
    private String streetName;
    private String city;

    public Address() {

    }

    public Address(long addressID, Integer streetNumber, String streetName, String city) {
        super();
        this.addressID = addressID;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
    }

    public long getAddressID() {
        return addressID;
    }

    @XmlAttribute(name = "AddressID")
    public void setAddressID(long addressID) {
        this.addressID = addressID;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    @XmlElement(name = "streetNumber")
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    @XmlElement(name = "streetName")
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    @XmlElement(name = "City")
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address [streetNumber=" + streetNumber + ", streetName=" + streetName + ", city=" + city + "]";
    }

}
