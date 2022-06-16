package com.solvd.computerrepairservice.dao.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IDataStorageTypeDAO;
import com.solvd.computerrepairservice.model.DataStorageDeviceTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStorageDeviceTypeDAO implements IDataStorageTypeDAO {
    public static final Logger LOGGER = LogManager.getLogger(DataStorageDeviceTypeDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Data_Storage_Devices_Types WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO  Data_Storage_Devices_Types (data_storage_type) VALUES (?)";
    private final String UPDATE_QUERY = "UPDATE Data_Storage_Devices_Types SET data_storage_type = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM DataStorageDeviceTypeDAO WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Data_Storage_Devices_Types";
    private final String GET_BY_EMPLOYEE_ID_QUERY = "SELECT * FROM Data_Storage_Devices_Types d JOIN Computers c ON d.id = c.user_id WHERE c.id = ?";

    private Connection connection;

    public DataStorageDeviceTypeDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public DataStorageDeviceTypes getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        DataStorageDeviceTypes dataStorageType = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next() && Arrays.asList(DataStorageDeviceTypes.values())
                    .contains(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")))) {
                dataStorageType = DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type"));
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
        return dataStorageType;
    }

    @Override
    public void insertEntity(DataStorageDeviceTypes entity) {
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
    public void updateEntity(DataStorageDeviceTypes entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.name());
            prepStat.setLong(2, entity.getDataStorageTypeID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Data Storage Types data of id = " + entity.getDataStorageTypeID()
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
                LOGGER.info("Data Storage Types data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<DataStorageDeviceTypes> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<DataStorageDeviceTypes> dataStorageTypes = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next() && Arrays.asList(DataStorageDeviceTypes.values())
                    .contains(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")))) {
                dataStorageTypes.add(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next() && Arrays.asList(DataStorageDeviceTypes.values())
                    .contains(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")))) {
                dataStorageTypes.add(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")));
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
        return dataStorageTypes;
    }

    @Override
    public DataStorageDeviceTypes getDataStorageTypeByComputerID(long computerID) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        DataStorageDeviceTypes dataStorageType = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_EMPLOYEE_ID_QUERY);
            prepStat.setLong(1, computerID);
            resultSet = prepStat.executeQuery();
            if (resultSet.next() && Arrays.asList(DataStorageDeviceTypes.values())
                    .contains(DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type")))) {
                dataStorageType = DataStorageDeviceTypes.valueOf(resultSet.getString("data_storage_type"));
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
        return dataStorageType;
    }
}
