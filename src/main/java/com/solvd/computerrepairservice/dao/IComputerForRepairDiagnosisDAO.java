package com.solvd.computerrepairservice.dao;

import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;

import java.sql.SQLException;
import java.util.List;

public interface IComputerForRepairDiagnosisDAO extends IBaseDAO<ComputerForRepairDiagnosis> {
    List<ComputerForRepairDiagnosis> getComputerForRepairDiagnosisByComputerForRepairID(long id) throws SQLException;

}
