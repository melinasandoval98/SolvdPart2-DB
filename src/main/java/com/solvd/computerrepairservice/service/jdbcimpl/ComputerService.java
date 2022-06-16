package com.solvd.computerrepairservice.service.jdbcimpl;

import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.DataStorageDeviceTypeDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.OperatingSystemDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ProcessorDAO;
import com.solvd.computerrepairservice.model.Computer;
import com.solvd.computerrepairservice.service.IComputerService;

import java.sql.SQLException;
import java.util.List;

public class ComputerService implements IComputerService {
    ComputerDAO computerDAO;
    DataStorageDeviceTypeDAO dataStorageDeviceTypeDAO;
    OperatingSystemDAO operatingSystemDAO;
    ProcessorDAO processorDAO;

    @Override
    public Computer getFullInformationOfEntityByID(long entityID) throws SQLException {
        Computer computer = computerDAO.getEntityByID(entityID);
        computer.setComputerOS(operatingSystemDAO.getOSByComputerID(entityID));
        computer.setComputerProcessor(processorDAO.getProcessorByComputerID(entityID));
        computer.setComputerDataStorageDevice(dataStorageDeviceTypeDAO.getDataStorageTypeByComputerID(entityID));
        return computer;
    }

    @Override
    public List<Computer> getFullInformationOfAllEntities() throws SQLException {
        List<Computer> computers = computerDAO.getAll();
        computers.stream().forEach(computer -> {
            computer.setComputerOS(operatingSystemDAO.getOSByComputerID(computer.getComputerID()));
            computer.setComputerProcessor(processorDAO.getProcessorByComputerID(computer.getComputerID()));
            computer.setComputerDataStorageDevice(dataStorageDeviceTypeDAO.getDataStorageTypeByComputerID(computer.getComputerID()));
        });
        return computers;
    }
}
