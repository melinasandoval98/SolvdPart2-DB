package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.DataStorageDeviceTypes;

public interface IDataStorageTypeDAO extends IBaseDAO<DataStorageDeviceTypes> {
    DataStorageDeviceTypes getDataStorageTypeByComputerID(long computerID);

}
