package com.solvd.computerrepairservice.saxparser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.computerrepairservice.model.Adress;
import com.solvd.computerrepairservice.model.Genders;
import com.solvd.computerrepairservice.model.PhoneNumber;
import com.solvd.computerrepairservice.model.User;

public class UserXMLSAXParser extends DefaultHandler {
	private User user;
	private PhoneNumber phoneNumber;
	private Adress adress;
	private ArrayList<User> users = new ArrayList<>();
	private StringBuilder buffer = new StringBuilder();

	public ArrayList<User> getUsers() {
		return users;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (qName) {
		case "user":
			user = new User();
			user.setUserID(Long.parseLong(attributes.getValue("userID")));
			users.add(user);
			break;
		case "userPhoneNumber":
			phoneNumber = new PhoneNumber();
			user.setUserPhoneNumber(phoneNumber);
			break;
		case "userAdress":
			adress = new Adress();
			user.setUserAdress(adress);
			break;
		default:
			buffer.delete(0, buffer.length());
			break;

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		switch (qName) {
		case "userName":
			user.setUserName(buffer.toString());
			break;
		case "age":
			user.setUserAge(Integer.parseInt(buffer.toString()));
			break;
		case "userEMail":
			user.setUserEMail(buffer.toString());
			break;
		case "phoneNumberID":
			user.getUserPhoneNumber().setPhoneNumberID(Long.parseLong(buffer.toString()));
			break;
		case "countryCode":
			user.getUserPhoneNumber().setCountryCode(Integer.parseInt(buffer.toString()));
			break;
		case "phoneNumber":
			user.getUserPhoneNumber().setPhoneNumber(Long.parseLong(buffer.toString()));
			break;
		case "adressID":
			user.getUserAdress().setAdressID(Long.parseLong(buffer.toString()));
			break;
		case "streetNumber":
			user.getUserAdress().setStreetNumber(Integer.parseInt(buffer.toString()));
			break;
		case "streetName":
			user.getUserAdress().setStreetName(buffer.toString());
			break;
		case "city":
			user.getUserAdress().setCity(buffer.toString());
			break;
		case "userGender":
			user.setUserGender(Genders.valueOf(buffer.toString().toUpperCase()));
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		buffer.append(ch, start, length);
	}

}
