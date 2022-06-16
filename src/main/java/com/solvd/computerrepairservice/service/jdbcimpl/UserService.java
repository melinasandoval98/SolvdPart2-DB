package com.solvd.computerrepairservice.service.jdbcimpl;

import com.solvd.computerrepairservice.dao.IAddressDAO;
import com.solvd.computerrepairservice.dao.IPhoneNumberDAO;
import com.solvd.computerrepairservice.dao.IUserDAO;
import com.solvd.computerrepairservice.model.User;
import com.solvd.computerrepairservice.service.IUserService;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    IUserDAO userDAO;
    IAddressDAO addressDAO;
    IPhoneNumberDAO phoneNumberDAO;

    public UserService(IUserDAO userDAO, IAddressDAO addressDAO, IPhoneNumberDAO phoneNumberDAO) {
        this.userDAO = userDAO;
        this.addressDAO = addressDAO;
        this.phoneNumberDAO = phoneNumberDAO;
    }

    @Override
    public User getFullInformationOfEntityByID(long userID) {
        User user = userDAO.getEntityByID(userID);
        user.setUserAddress(addressDAO.getAddressByUserID(userID));
        user.setUserPhoneNumber(phoneNumberDAO.getPhoneNumberByUserID(userID));
        return user;
    }

    @Override
    public List<User> getFullInformationOfAllEntities() throws SQLException {
        List<User> users = userDAO.getAll();
        users.stream().forEach(user -> {
            user.setUserAddress(addressDAO.getAddressByUserID(user.getUserID()));
            user.setUserPhoneNumber(phoneNumberDAO.getPhoneNumberByUserID(user.getUserID()));
        });
        return users;
    }
}