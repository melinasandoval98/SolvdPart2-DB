package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userAdress")
@XmlType(propOrder = { "adressID", "streetNumber", "streetName", "city" })
public class Adress {
	private long adressID;
	private Integer streetNumber;
	private String streetName;
	private String city;

	public Adress() {

	}

	public Adress(long adressID, Integer streetNumber, String streetName, String city) {
		super();
		this.adressID = adressID;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
	}

	public long getAdressID() {
		return adressID;
	}

	public void setAdressID(long adressID) {
		this.adressID = adressID;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Adress [streetNumber=" + streetNumber + ", streetName=" + streetName + ", city=" + city + "]";
	}

}
