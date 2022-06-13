package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerProblemSubTypeDAO;
import com.solvd.computerrepairservice.model.ComputerProblemSubType;

public class ComputerProblemSubTypeDAO implements IComputerProblemSubTypeDAO {
    public static final Logger LOGGER = LogManager.getLogger(ComputerProblemSubTypeDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Computer_Problems_Sub_Types WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Computer_Problems_Sub_Types (sub_type_problem_description, sub_price, computer_problem_type_id) VALUES (?,?,?) ";
    private final String UPDATE_QUERY = "UPDATE Computer_Problems_Sub_Types SET sub_type_problem_description= ?, sub_price= ?, computer_problem_type_id = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Computer_Problems_Sub_Types WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Computer_Problems_Sub_Types";
    private Connection connection;

    public ComputerProblemSubTypeDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private ComputerProblemSubType createComputerProblemSubType(ResultSet rs) {
        ComputerProblemSubType computerProblemSubType = null;
        try {
            computerProblemSubType = new ComputerProblemSubType(rs.getLong("id"),
                    rs.getString("sub_type_problem_description"), rs.getLong("sub_price"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return computerProblemSubType;
    }

    @Override
    public ComputerProblemSubType getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        ComputerProblemSubType computerProblemSubType = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                computerProblemSubType = createComputerProblemSubType(resultSet);
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
        return computerProblemSubType;
    }

    @Override
    public void insertEntity(ComputerProblemSubType entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setString(1, entity.getProblemDescription());
            prepStat.setLong(2, entity.getServiceSubPrice());
            prepStat.setLong(3, entity.getProblemType().getComputerProblemTypeID());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
    }

    @Override
    public void updateEntity(ComputerProblemSubType entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.getProblemDescription());
            prepStat.setLong(2, entity.getServiceSubPrice());
            prepStat.setLong(3, entity.getProblemType().getComputerProblemTypeID());
            prepStat.setLong(4, entity.getId());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Computer ProblemS Sub Types data of id = " + entity.getId()
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
    public List<ComputerProblemSubType> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerProblemSubType> computerProblemSubTypes = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computerProblemSubTypes.add(createComputerProblemSubType(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computerProblemSubTypes.add(createComputerProblemSubType(resultSet));
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
        return computerProblemSubTypes;
    }

}
