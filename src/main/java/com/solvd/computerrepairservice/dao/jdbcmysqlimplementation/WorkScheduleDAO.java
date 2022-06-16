package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IWorkScheduleDAO;
import com.solvd.computerrepairservice.model.Shifts;
import com.solvd.computerrepairservice.model.WorkSchedule;
import com.solvd.computerrepairservice.model.WorkingDays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkScheduleDAO implements IWorkScheduleDAO {
    public static final Logger LOGGER = LogManager.getLogger(WorkScheduleDAO.class);
    private final String GET_BY_ID_QUERY = " ";
    private final String INSERT_QUERY = " ";
    private final String UPDATE_QUERY = " ";
    private final String REMOVE_QUERY = " ";
    private final String GET_ALL_VALUES_QUERY = " ";
    private Connection connection;

    public WorkScheduleDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private WorkSchedule createWorkSchedule(ResultSet rs) {
        WorkSchedule workSchedule = null;
        try {
            workSchedule = new WorkSchedule(rs.getLong("id"),
                    WorkingDays.valueOf(rs.getString("work_day").toUpperCase()),
                    Shifts.valueOf(rs.getString("shift").toUpperCase()));
        } catch (SQLException e) {
            LOGGER.error("SQLEception catched", e);
        }
        return workSchedule;
    }

    @Override
    public WorkSchedule getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        WorkSchedule workSchedule = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                workSchedule = createWorkSchedule(resultSet);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException catched", e);
        } finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException catched while closing the PreparedStatement connection", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException catched while closing the ResultSet connection", e);
                }
            }
        }
        return workSchedule;
    }

    @Override
    public void insertEntity(WorkSchedule entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setLong(1, getAll().size() + 1);
            prepStat.setString(2, entity.getWorkingDay().name());
            prepStat.setString(3, entity.getShift().name());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException catched", e);
        }

    }

    @Override
    public void updateEntity(WorkSchedule entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setLong(1, entity.getId());
            prepStat.setString(2, entity.getWorkingDay().name());
            prepStat.setString(3, entity.getShift().name());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Work Schedule data of id = " + entity.getId() + " has been updated successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException catched", e);
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
            LOGGER.error("SQLException catched", e);
        }

    }

    @Override
    public List<WorkSchedule> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<WorkSchedule> workSchedules = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                workSchedules.add(createWorkSchedule(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException catched", e);
        } finally {
            if (resultSet.next()) {
                workSchedules.add(createWorkSchedule(resultSet));
            } else {
                throw new SQLException();
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException catched while closing the PreparedStatement connection", e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error("SQLException catched while closing the ResultSet connection", e);
                }
            }
        }
        return workSchedules;
    }

}
