package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.List;

//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;
//
//@XmlRootElement(name = "user")
//@XmlType(propOrder = { "userID", "userName", "userAge", "userEMail", "userPhoneNumber", "userAdress", "userGender" })
public class User {
	private long userID;
	private String userName;
	private Integer userAge;
	private String userEMail;
	private PhoneNumber userPhoneNumber;
	private Adress userAdress;
	private Genders userGender;
	private List<ComputerForRepair> userComputersForRepair = new ArrayList<>();

	public User() {

	}

	public User(long userID, String userName, Integer userAge, String userEMail, PhoneNumber userPhoneNumber,
			Adress userAdress, Genders userGender, List<ComputerForRepair> userComputers) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userAge = userAge;
		this.userEMail = userEMail;
		this.userPhoneNumber = userPhoneNumber;
		this.userAdress = userAdress;
		this.userGender = userGender;
		this.userComputersForRepair = userComputers;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserEMail() {
		return userEMail;
	}

	public void setUserEMail(String userEMail) {
		this.userEMail = userEMail;
	}

	public PhoneNumber getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(PhoneNumber userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public Adress getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(Adress userAdress) {
		this.userAdress = userAdress;
	}

	public Genders getUserGender() {
		return userGender;
	}

	public void setUserGender(Genders userGender) {
		this.userGender = userGender;
	}

	public List<ComputerForRepair> getUserComputers() {
		return userComputersForRepair;
	}

	public void setUserComputers(List<ComputerForRepair> userComputers) {
		this.userComputersForRepair = userComputers;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userAge=" + userAge + ", userEMail=" + userEMail
				+ ", userPhoneNumber=" + userPhoneNumber + ", userAdress=" + userAdress + ", userGender=" + userGender
				+ ", userComputers=" + userComputersForRepair + "]";
	}

}
