package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IOperatingSystemDAO;
import com.solvd.computerrepairservice.model.OperatingSystems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperatingSystemDAO implements IOperatingSystemDAO {
    public static final Logger LOGGER = LogManager.getLogger(OperatingSystemDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Operating_Systems WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Operating_Systems (o_s) VALUES (?)";
    private final String UPDATE_QUERY = "UPDATE Operating_Systems SET o_s = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Operating_Systems WHERE id =?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Operating_Systems";
    private final String GET_BY_EMPLOYEE_ID_QUERY = "SELECT * FROM Operating_Systems o JOIN Computers c ON o.id = c.user_id WHERE c.id = ?";

    private Connection connection;

    public OperatingSystemDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public OperatingSystems getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        OperatingSystems oS = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next() && Arrays.asList(OperatingSystems.values())
                    .contains(OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase()))) {
                oS = OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase());
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
        return oS;
    }

    @Override
    public void insertEntity(OperatingSystems entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setString(1, entity.name());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(OperatingSystems entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.name());
            prepStat.setLong(2, entity.getoSID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Operating Systems data of id = " + entity.getoSID()
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
                LOGGER.info("Operating Systems data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<OperatingSystems> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<OperatingSystems> operatingSystems = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next() && Arrays.asList(OperatingSystems.values())
                    .contains(OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase()))) {
                operatingSystems.add(OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase()));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next() && Arrays.asList(OperatingSystems.values())
                    .contains(OperatingSystems.valueOf(resultSet.getString("o_s")))) {
                operatingSystems.add(OperatingSystems.valueOf(resultSet.getString("o_s")));
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
        return operatingSystems;
    }

    @Override
    public OperatingSystems getOSByComputerID(long computerID) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        OperatingSystems oS = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_EMPLOYEE_ID_QUERY);
            prepStat.setLong(1, computerID);
            resultSet = prepStat.executeQuery();
            if (resultSet.next() && Arrays.asList(OperatingSystems.values())
                    .contains(OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase()))) {
                oS = OperatingSystems.valueOf(resultSet.getString("o_s").toUpperCase());
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
        return oS;
    }
}