package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.Genders;

public interface IGenderDAO extends IBaseDAO<Genders> {

    Genders getGenderByEmployeeID(long employeeID);
}
