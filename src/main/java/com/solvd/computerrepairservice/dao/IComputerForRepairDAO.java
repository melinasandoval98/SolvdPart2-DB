package com.solvd.computerrepairservice.dao;

import java.sql.SQLException;
import java.util.List;

import com.solvd.computerrepairservice.model.ComputerForRepair;

public interface IComputerForRepairDAO extends IBaseDAO<ComputerForRepair> {

    List<ComputerForRepair> getComputersForRepairByClientID(long clientID) throws SQLException;

    List<ComputerForRepair> getComputersForRepairByEmployeeID(long employeeID) throws SQLException;

}