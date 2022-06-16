package com.solvd.computerrepairservice.service.jdbcimpl;

import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerForRepairDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerForRepairDiagnosisDAO;
import com.solvd.computerrepairservice.model.ComputerForRepair;
import com.solvd.computerrepairservice.service.IComputerForRepairService;

import java.sql.SQLException;
import java.util.List;

public class ComputerForRepairService implements IComputerForRepairService {
    ComputerForRepairDAO computerForRepairDAO;
    ComputerDAO computerDAO;
    ComputerForRepairDiagnosisDAO computerForRepairDiagnosisDAO;

    @Override
    public ComputerForRepair getFullInformationOfEntityByID(long entityID) throws SQLException {
        ComputerForRepair computerForRepair = computerForRepairDAO.getEntityByID(entityID);
        computerForRepair.setComputer(computerDAO.getComputerByComputerForRepairID(entityID));
        computerForRepair.setComputerForRepairDiagnoses(computerForRepairDiagnosisDAO.getComputerForRepairDiagnosisByComputerForRepairID(entityID));
        return computerForRepair;
    }

    @Override
    public List<ComputerForRepair> getFullInformationOfAllEntities() throws SQLException {
        List<ComputerForRepair> computersForRepairs = computerForRepairDAO.getAll();
        computersForRepairs.stream().forEach(computerForRepair -> {
                    computerForRepair.setComputer(computerDAO.getComputerByComputerForRepairID(computerForRepair.getComputerForRepairID()));
                    try {
                        computerForRepair.setComputerForRepairDiagnoses(computerForRepairDiagnosisDAO.getComputerForRepairDiagnosisByComputerForRepairID(computerForRepair.getComputerForRepairID()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return null;
    }


}
