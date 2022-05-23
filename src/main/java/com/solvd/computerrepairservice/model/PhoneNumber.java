package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "userPhoneNumber")
@XmlType(propOrder = { "phoneNumberID", "countryCode", "phoneNumber" })
public class PhoneNumber {
	private long phoneNumberID;
	private int countryCode;
	private long phoneNumber;

	public PhoneNumber() {

	}

	public PhoneNumber(long phoneNumberID, int countryCode, long phoneNumber) {
		super();
		this.phoneNumber = phoneNumberID;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
	}

	public long getPhoneNumberID() {
		return phoneNumberID;
	}

	public void setPhoneNumberID(long phoneNumberID) {
		this.phoneNumberID = phoneNumberID;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "PhoneNumber [countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + "]";
	}

}
