package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.PhoneNumber;

public interface IPhoneNumberDAO extends IBaseDAO<PhoneNumber> {

	PhoneNumber getPhoneNumberByUserID(long userID);

}
