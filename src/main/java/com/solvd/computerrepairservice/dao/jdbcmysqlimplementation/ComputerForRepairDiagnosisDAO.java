package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IComputerForRepairDiagnosisDAO;
import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerForRepairDiagnosisDAO implements IComputerForRepairDiagnosisDAO {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDiagnosisDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Computers_For_Repair_Diagnosis WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Computers_For_Repair_Diagnosis (computer_for_repair_id, computer_problem_sub_type_id) VALUES (?,?)";
    private final String UPDATE_QUERY = "UPDATE Computers_For_Repair_Diagnosis computer_for_repair_id = ?, computer_problem_sub_type_id = ?, WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Computers_For_Repair_Diagnosis WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Computers_For_Repair_Diagnosis";

    private final String GET_BY_C_F_R_ID_QUERY = "SELECT * FROM Computers_For_Repair_Diagnosis cfrd JOIN Computers_For_Repair cfr ON cfrd.id = cfr.computer_id WHERE cfr.id = ?";
    private Connection connection;

    public ComputerForRepairDiagnosisDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private ComputerForRepairDiagnosis createComputerForRepairDiagnosis(ResultSet rs) {
        ComputerForRepairDiagnosis computerForRepairDiagnosis = null;
        try {
            computerForRepairDiagnosis = new ComputerForRepairDiagnosis(rs.getLong("id"), rs.getLong("computer_for_repair_id"), rs.getLong("computer_problem_sub_type_id"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return computerForRepairDiagnosis;
    }

    @Override
    public ComputerForRepairDiagnosis getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        ComputerForRepairDiagnosis computerForRepairDiagnosis = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                computerForRepairDiagnosis = createComputerForRepairDiagnosis(resultSet);
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
        return computerForRepairDiagnosis;
    }

    @Override
    public void insertEntity(ComputerForRepairDiagnosis entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setLong(1, entity.getComputerForRepairID());
            prepStat.setLong(2, entity.getProblemSubTypeID());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(ComputerForRepairDiagnosis entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setLong(1, entity.getComputerForRepairID());
            prepStat.setLong(2, entity.getProblemSubTypeID());
            prepStat.setLong(3, entity.getComputerForRepairDiagnosisID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Employee Work Schedule data of id = " + entity.getComputerForRepairDiagnosisID() + " has been updated successfully");
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
                LOGGER.info("Computers For Repair Diagnosis data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
    }

    @Override
    public List<ComputerForRepairDiagnosis> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computerForRepairDiagnosis.add(createComputerForRepairDiagnosis(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computerForRepairDiagnosis.add(createComputerForRepairDiagnosis(resultSet));
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
        return computerForRepairDiagnosis;
    }

    @Override
    public List<ComputerForRepairDiagnosis> getComputerForRepairDiagnosisByComputerForRepairID(long id) throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_BY_C_F_R_ID_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                computerForRepairDiagnosis.add(createComputerForRepairDiagnosis(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                computerForRepairDiagnosis.add(createComputerForRepairDiagnosis(resultSet));
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
        return computerForRepairDiagnosis;
    }
}