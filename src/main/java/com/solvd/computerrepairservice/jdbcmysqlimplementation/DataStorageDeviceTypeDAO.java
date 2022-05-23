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

import com.solvd.computerrepairservice.dao.IDataStorageTypeDAO;
import com.solvd.computerrepairservice.model.DataStorageTypes;

public class DataStorageDeviceTypeDAO implements IDataStorageTypeDAO {
	public static final Logger LOGGER = LogManager.getLogger(ProcessorDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private Connection connection;

	public DataStorageDeviceTypeDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public DataStorageTypes getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		DataStorageTypes dataStorageType = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next() && Arrays.asList(DataStorageTypes.values())
					.contains(DataStorageTypes.valueOf(resultSet.getString("data_storage_type")))) {
				dataStorageType = DataStorageTypes.valueOf(resultSet.getString("data_storage_type"));
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
		return dataStorageType;
	}

	@Override
	public void insertEntity(DataStorageTypes entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			prepStat.setLong(1, getAll().size() + 1);
			prepStat.setString(2, entity.name());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(DataStorageTypes entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getDataStorageTypeID());
			prepStat.setString(2, entity.name());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Data Storage Types data of id = " + entity.getDataStorageTypeID()
						+ " has been updated successfully");
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
				LOGGER.info("Data Storage Types data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<DataStorageTypes> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<DataStorageTypes> dataStorageTypes = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next() && Arrays.asList(DataStorageTypes.values())
					.contains(DataStorageTypes.valueOf(resultSet.getString("data_storage_type")))) {
				dataStorageTypes.add(DataStorageTypes.valueOf(resultSet.getString("data_storage_type")));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next() && Arrays.asList(DataStorageTypes.values())
					.contains(DataStorageTypes.valueOf(resultSet.getString("data_storage_type")))) {
				dataStorageTypes.add(DataStorageTypes.valueOf(resultSet.getString("data_storage_type")));
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
		return dataStorageTypes;
	}

}