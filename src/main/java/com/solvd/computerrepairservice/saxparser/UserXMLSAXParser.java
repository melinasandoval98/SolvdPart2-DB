package com.solvd.computerrepairservice.saxparser;

import java.util.ArrayList;

import com.solvd.computerrepairservice.model.Address;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import com.solvd.computerrepairservice.model.Genders;
import com.solvd.computerrepairservice.model.PhoneNumber;
import com.solvd.computerrepairservice.model.User;

public class UserXMLSAXParser extends DefaultHandler {
    private User user;
    private PhoneNumber phoneNumber;
    private Address address;
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
            case "userAddress":
                address = new Address();
                user.setUserAddress(address);
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
            case "userEMail":
                user.setUserEMail(buffer.toString());
                break;
            case "phoneNumberID":
                user.getUserPhoneNumber().setPhoneNumberID(Long.parseLong(buffer.toString()));
                break;
            case "countryCode":
                user.getUserPhoneNumber().setCountryCode(Integer.parseInt(buffer.toString()));
                break;
            case "phoneNumberBody":
                user.getUserPhoneNumber().setPhoneNumberBody(Long.parseLong(buffer.toString()));
                break;
            case "addressID":
                user.getUserAddress().setAddressID(Long.parseLong(buffer.toString()));
                break;
            case "streetNumber":
                user.getUserAddress().setStreetNumber(Integer.parseInt(buffer.toString()));
                break;
            case "streetName":
                user.getUserAddress().setStreetName(buffer.toString());
                break;
            case "city":
                user.getUserAddress().setCity(buffer.toString());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
    }

}
