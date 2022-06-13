package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerProblemTypeDAO;
import com.solvd.computerrepairservice.model.ComputerProblemTypes;

public class ComputerProblemTypeDAO implements IComputerProblemTypeDAO {
    public static final Logger LOGGER = LogManager.getLogger(ComputerProblemSubTypeDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Computer_Problem_Types WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Computer_Problem_Types (problem_type) VALUES (?) ";
    private final String UPDATE_QUERY = "UPDATE Computer_Problem_Types SET problem_type = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Computer_Problem_Types WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Computer_Problem_Types";
    private ComputerProblemTypeDAO computerProblemTypeDAO;
    private Connection connection;

    public ComputerProblemTypeDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public ComputerProblemTypes getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        ComputerProblemTypes computerProblemType = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next() && Arrays.asList(ComputerProblemTypes.values())
                    .contains(ComputerProblemTypes.valueOf(resultSet.getString("problem_type")))) {
                computerProblemType = ComputerProblemTypes.valueOf(resultSet.getString("problem_type"));
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
        return computerProblemType;
    }

    @Override
    public void insertEntity(ComputerProblemTypes entity) {
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
    public void updateEntity(ComputerProblemTypes entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.name());
            prepStat.setLong(2, entity.getComputerProblemTypeID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Computer Problem Types data of id = " + entity.getComputerProblemTypeID()
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
                LOGGER.info("Genders data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<ComputerProblemTypes> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerProblemTypes> computerProblemType = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next() && Arrays.asList(ComputerProblemTypes.values())
                    .contains(ComputerProblemTypes.valueOf(resultSet.getString("problem_type")))) {
                computerProblemType.add(ComputerProblemTypes.valueOf(resultSet.getString("problem_type")));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next() && Arrays.asList(ComputerProblemTypes.values())
                    .contains(ComputerProblemTypes.valueOf(resultSet.getString("problem_type")))) {
                computerProblemType.add(ComputerProblemTypes.valueOf(resultSet.getString("problem_type")));
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
        return computerProblemType;
    }

    public ComputerProblemTypeDAO getComputerProblemTypeDAO() {
        return computerProblemTypeDAO;
    }

    public void setComputerProblemTypeDAO(ComputerProblemTypeDAO computerProblemTypeDAO) {
        this.computerProblemTypeDAO = computerProblemTypeDAO;
    }

}