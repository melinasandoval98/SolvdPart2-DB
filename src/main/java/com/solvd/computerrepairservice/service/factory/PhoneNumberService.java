package com.solvd.computerrepairservice.service.factory;

import com.solvd.computerrepairservice.dao.IPhoneNumberDAO;
import com.solvd.computerrepairservice.dao.IUserDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.PhoneNumberDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.PhoneNumber;

import java.sql.Connection;

public class PhoneNumberService {
    Connection conn;

    public PhoneNumber getPhoneNumberByEmployeeID(long id) {
        IUserDAO userDAO = new UserDAO();
        IPhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO(conn);
        PhoneNumber phoneNumber = phoneNumberDAO.getPhoneNumberByUserID(userDAO.getUserByEmployeeID(id).getUserID());
        return null;
    }

    public PhoneNumber getPhoneNumberByClientID(long id) {
        IUserDAO userDAO = new UserDAO();
        IPhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO(conn);
        PhoneNumber phoneNumber = phoneNumberDAO.getPhoneNumberByUserID(userDAO.getUserByClientID(id).getUserID());
        return null;
    }

}
