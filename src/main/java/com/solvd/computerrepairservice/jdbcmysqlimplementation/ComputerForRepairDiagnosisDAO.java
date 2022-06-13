package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IComputerForRepairDiagnosisDAO;
import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;

import java.sql.SQLException;
import java.util.List;

public class ComputerForRepairDiagnosisDAO implements IComputerForRepairDiagnosisDAO {
    @Override
    public ComputerForRepairDiagnosis getEntityByID(long id) {
        return null;
    }

    @Override
    public void insertEntity(ComputerForRepairDiagnosis entity) {

    }

    @Override
    public void updateEntity(ComputerForRepairDiagnosis entity) {

    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<ComputerForRepairDiagnosis> getAll() throws SQLException {
        return null;
    }
}
