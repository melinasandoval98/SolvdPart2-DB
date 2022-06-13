package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerDAO;
import com.solvd.computerrepairservice.model.Computer;

public class ComputerDAO implements IComputerDAO {
    public static final Logger LOGGER = LogManager.getLogger(ComputerDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT model, year, data_storage_device_capacity FROM Computers WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Computers (model, year, operating_system_id, processor_id, data_storage_device_type_id, data_storage_device_capacity) VALUES (?,?,?,?,?,?) ";
    private final String UPDATE_QUERY = "UPDATE Computers SET model = ?, year = ?, operating_system_id = ?, processor_id = ?, data_storage_device_type_id = ?, data_storage_device_type_capacity = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Computers WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT model, year, data_storage_device_capacity FROM Computers";
    private Connection connection;

    public ComputerDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private Computer createComputer(ResultSet rs) {
        Computer computer = null;
        try {
            computer = new Computer(rs.getLong("id"), rs.getString("model"), rs.getInt("year"), rs.getString("data_storage_device_type_id"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return computer;
    }

    @Override
    public Computer getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        Computer computer = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                computer = createComputer(resultSet);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException caught while closing the PreparedStatement connection", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException caught while closing the ResultSet connection", e);
                }
            }
        }
        return computer;
    }

    @Override
    public void insertEntity(Computer entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setString(1, entity.getComputerModel());
            prepStat.setInt(2, entity.getComputerYear());
            prepStat.setLong(3, entity.getComputerOS().getOSID());
            prepStat.setLong(4, entity.getComputerProcessor().getProcessorID());
            prepStat.setLong(5, entity.getComputerDataStorageDevice().getDataStorageTypeID());
            prepStat.setString(6, entity.getComputerDataStorageDeviceCapacity());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(Computer entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.getComputerModel());
            prepStat.setInt(2, entity.getComputerYear());
            prepStat.setLong(3, entity.getComputerOS().getOSID());
            prepStat.setLong(4, entity.getComputerProcessor().getProcessorID());
            prepStat.setLong(5, entity.getComputerDataStorageDevice().getDataStorageTypeID());
            prepStat.setString(6, entity.getComputerDataStorageDeviceCapacity());
            prepStat.setLong(7, entity.getComputerID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Computers data of id = " + entity.getComputerID() + " has been updated successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void removeEntity(long id) {
        try (PreparedStatement prepStat = connection.prepareStatement(REMOVE_QUERY)) {
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Computers data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<Computer> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<Computer> computers = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computers.add(createComputer(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
//			if (resultSet.next()) {
//				computers.add(createComputer(resultSet));
//			} else {
//				throw new SQLException();
//			}
            if (prepStat != null) {
                try {
                    prepStat.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException caught while closing the PreparedStatement connection", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException caught while closing the ResultSet connection", e);
                }
            }
        }
        return computers;
    }

}
