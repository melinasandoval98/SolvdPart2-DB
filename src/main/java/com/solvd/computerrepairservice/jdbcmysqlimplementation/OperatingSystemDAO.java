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

import com.solvd.computerrepairservice.dao.IOperatingSystemDAO;
import com.solvd.computerrepairservice.model.OperatingSystems;

public class OperatingSystemDAO implements IOperatingSystemDAO {
	public static final Logger LOGGER = LogManager.getLogger(OperatingSystemDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private int oSVersionIndex;
	private Connection connection;

	public OperatingSystemDAO(int oSVersionIndex, Connection connection) {
		super();
		this.oSVersionIndex = oSVersionIndex;
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
					.contains(OperatingSystems.valueOf(resultSet.getString("o_s")))) {
				oS = OperatingSystems.valueOf(resultSet.getString("o_s"));
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
		return oS;
	}

	@Override
	public void insertEntity(OperatingSystems entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			prepStat.setLong(1, getAll().size() + 1);
			prepStat.setString(2, entity.name());
			prepStat.setString(3, entity.getVersions()[oSVersionIndex]);
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(OperatingSystems entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getOperatingSystemID());
			prepStat.setString(2, entity.name());
			prepStat.setString(3, entity.getVersions()[oSVersionIndex]);
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Operating Systems data of id = " + entity.getOperatingSystemID()
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
				LOGGER.info("Operating Systems data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
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
					.contains(OperatingSystems.valueOf(resultSet.getString("o_s")))) {
				operatingSystems.add(OperatingSystems.valueOf(resultSet.getString("o_s")));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
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
		return operatingSystems;
	}
}
