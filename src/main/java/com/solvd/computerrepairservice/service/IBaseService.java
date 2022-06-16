package com.solvd.computerrepairservice.service;


import java.sql.SQLException;
import java.util.List;

public interface IBaseService<T> {
    T getFullInformationOfEntityByID(long entityID) throws SQLException;

    List<T> getFullInformationOfAllEntities() throws SQLException;
}
