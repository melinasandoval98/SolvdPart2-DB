package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.Address;

public interface IAddressDAO extends IBaseDAO<Address> {

    Address getAddressByUserID(long userID);
}
