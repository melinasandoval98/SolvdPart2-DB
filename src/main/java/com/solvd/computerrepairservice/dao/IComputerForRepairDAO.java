package com.solvd.computerrepairservice.dao;

import java.util.List;

import com.solvd.computerrepairservice.model.ComputerForRepair;

public interface IComputerForRepairDAO extends IBaseDAO<ComputerForRepair> {

	List<ComputerForRepair> getComputerForRepairByUserID(long userID);

}