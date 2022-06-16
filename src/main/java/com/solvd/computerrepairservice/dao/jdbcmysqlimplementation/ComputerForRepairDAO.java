package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IComputerForRepairDAO;
import com.solvd.computerrepairservice.model.ComputerForRepair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerForRepairDAO implements IComputerForRepairDAO {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT client_id, employee_id, entry_date FROM Computers_For_Repair WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Computers_For_Repair (computer_id, client_id, employee_id entry_date) VALUES (?,?,?,?)";
    private final String UPDATE_QUERY = "UPDATE Computers_For_Repair SET computer_id = ?, client_id = ?, employee_id = ?, entry_date = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Computers_For_Repair WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT client_id, employee_id, entry_date FROM Computers_For_Repair";

    private final String GET_ALL_BY_CLIENT_ID_QUERY = "SELECT client_id, employee_id, entry_date FROM Computers_For_Repair cfr JOIN Clients c ON c.id = cfr.client_id";
    private final String GET_ALL_BY_EMPLOYEE_ID_QUERY = "SELECT client_id, employee_id, entry_date FROM Computers_For_Repair cfr JOIN Employees e ON e.id = cfr.employee_id";


    private Connection connection;

    public ComputerForRepairDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private ComputerForRepair createComputerForRepair(ResultSet rs) {
        ComputerForRepair computerForRepair = null;
        try {
            computerForRepair = new ComputerForRepair(rs.getLong("id"), rs.getDate("entry_date"), rs.getLong("client_id"),
                    rs.getLong("employee_id"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return computerForRepair;
    }

    @Override
    public ComputerForRepair getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        ComputerForRepair computerForRepair = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                computerForRepair = createComputerForRepair(resultSet);
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
        return computerForRepair;
    }

    @Override
    public void insertEntity(ComputerForRepair entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setLong(1, entity.getComputer().getComputerID());
            prepStat.setLong(2, entity.getClientID());
            prepStat.setLong(3, entity.getRepairerID());
            prepStat.setDate(4, (Date) entity.getEntryDate());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(ComputerForRepair entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setLong(1, entity.getComputer().getComputerID());
            prepStat.setLong(2, entity.getClientID());
            prepStat.setLong(3, entity.getRepairerID());
            prepStat.setDate(4, (Date) entity.getEntryDate());
            prepStat.setLong(5, entity.getComputerForRepairID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Computer For Repair data of id = " + entity.getComputerForRepairID()
                        + " has been updated successfully");
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
                LOGGER.info("Address data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<ComputerForRepair> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerForRepair> computersForRepair = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            } else {
                throw new SQLException();
            }
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
        return computersForRepair;
    }

    @Override
    public List<ComputerForRepair> getComputersForRepairByClientID(long clientID) throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerForRepair> computersForRepair = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_BY_CLIENT_ID_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            } else {
                throw new SQLException();
            }
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
        return computersForRepair;
    }

    @Override
    public List<ComputerForRepair> getComputersForRepairByEmployeeID(long employeeID) throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerForRepair> computersForRepair = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_BY_EMPLOYEE_ID_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computersForRepair.add(createComputerForRepair(resultSet));
            } else {
                throw new SQLException();
            }
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
        return computersForRepair;
    }
}