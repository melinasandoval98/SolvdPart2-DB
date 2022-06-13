package com.solvd.computerrepairservice.service;

import com.solvd.computerrepairservice.dao.IAddressDAO;
import com.solvd.computerrepairservice.dao.IPhoneNumberDAO;
import com.solvd.computerrepairservice.dao.IUserDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.AddressDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.PhoneNumberDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.User;

import java.sql.Connection;

public class UserService {
    Connection con;
    IUserDAO userDAO = new UserDAO(con);
    IAddressDAO addressDAO = new AddressDAO(con);
    IPhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO(con);

    public User getUserByID(long userID) {
        User user = userDAO.getEntityByID(userID);
        user.setUserAddress(addressDAO.getAddressByUserID(userID));
        user.setUserPhoneNumber(phoneNumberDAO.getPhoneNumberByUserID(userID));
        return user;
    }

}
