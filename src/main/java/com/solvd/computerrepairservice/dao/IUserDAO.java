package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.User;

public interface IUserDAO extends IBaseDAO<User> {
    User getUserByClientID(long clientID);
    User getUserByEmployeeID(long employeeID);

}
