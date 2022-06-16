package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IEmployeeWorkScheduleDAO;
import com.solvd.computerrepairservice.model.EmployeeWorkSchedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeWorkScheduleDAO implements IEmployeeWorkScheduleDAO {
    public static final Logger LOGGER = LogManager.getLogger(EmployeeWorkScheduleDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Employees_Work_Schedules WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Employees_Work_Schedules (employee_id, work_schedule_id) VALUES (?,?)";
    private final String UPDATE_QUERY = "UPDATE Employees employee_id = ?, work_schedule_id = ?, WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Employees WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Employees_Work_Schedules";
    private Connection connection;

    public EmployeeWorkScheduleDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private EmployeeWorkSchedule createEmployeeWorkSchedule(ResultSet rs) {
        EmployeeWorkSchedule employeeWorkSchedule = null;
        try {
            employeeWorkSchedule = new EmployeeWorkSchedule(rs.getLong("id"), rs.getLong("employee_id"), rs.getLong("work_schedule_id"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return employeeWorkSchedule;
    }

    @Override
    public EmployeeWorkSchedule getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        EmployeeWorkSchedule employeeWorkSchedule = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                employeeWorkSchedule = createEmployeeWorkSchedule(resultSet);
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
        return employeeWorkSchedule;
    }

    @Override
    public void insertEntity(EmployeeWorkSchedule entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setLong(1, entity.getEmployeeID());
            prepStat.setLong(2, entity.getWorkScheduleID());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(EmployeeWorkSchedule entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setLong(1, entity.getEmployeeID());
            prepStat.setLong(2, entity.getWorkScheduleID());
            prepStat.setLong(3, entity.getId());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Employee Work Schedule data of id = " + entity.getId() + " has been updated successfully");
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
                LOGGER.info("Employee Work Schedule data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
    }

    @Override
    public List<EmployeeWorkSchedule> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<EmployeeWorkSchedule> employeeWorkSchedule = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                employeeWorkSchedule.add(createEmployeeWorkSchedule(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                employeeWorkSchedule.add(createEmployeeWorkSchedule(resultSet));
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
        return employeeWorkSchedule;
    }

}
