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

import com.solvd.computerrepairservice.dao.IProcessorDAO;
import com.solvd.computerrepairservice.model.Processors;

public class ProcessorDAO implements IProcessorDAO {
	public static final Logger LOGGER = LogManager.getLogger(ProcessorDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private Connection connection;

	public ProcessorDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Processors getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		Processors processor = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()
					&& Arrays.asList(Processors.values()).contains(Processors.valueOf(resultSet.getString("model")))) {
				processor = Processors.valueOf(resultSet.getString("model"));
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
		return processor;
	}

	@Override
	public void insertEntity(Processors entity) {
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
	public void updateEntity(Processors entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getProcesorID());
			prepStat.setString(2, entity.name());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Processors data of id = " + entity.getProcesorID() + " has been updated successfully");
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
				LOGGER.info("Processors data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<Processors> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<Processors> processors = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()
					&& Arrays.asList(Processors.values()).contains(Processors.valueOf(resultSet.getString("model")))) {
				processors.add(Processors.valueOf(resultSet.getString("model")));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()
					&& Arrays.asList(Processors.values()).contains(Processors.valueOf(resultSet.getString("model")))) {
				processors.add(Processors.valueOf(resultSet.getString("model")));
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
		return processors;
	}

}
