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

import com.solvd.computerrepairservice.dao.IGenderDAO;
import com.solvd.computerrepairservice.model.Genders;

public class GenderDAO implements IGenderDAO {
	public static final Logger LOGGER = LogManager.getLogger(AdressDAO.class);
	private final String GET_BY_ID_QUERY = " ";
	private final String INSERT_QUERY = " ";
	private final String UPDATE_QUERY = " ";
	private final String REMOVE_QUERY = " ";
	private final String GET_ALL_VALUES_QUERY = " ";
	private Connection connection;

	public GenderDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public Genders getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		Genders gender = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()
					&& Arrays.asList(Genders.values()).contains(Genders.valueOf(resultSet.getString("gender")))) {
				gender = Genders.valueOf(resultSet.getString("gender"));
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
		return gender;
	}

	@Override
	public void insertEntity(Genders entity) {
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
	public void updateEntity(Genders entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setLong(1, entity.getGenderID());
			prepStat.setString(2, entity.name());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Genders data of id = " + entity.getGenderID() + " has been updated successfully");
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
				LOGGER.info("Genders data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<Genders> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<Genders> genders = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()
					&& Arrays.asList(Genders.values()).contains(Genders.valueOf(resultSet.getString("gender")))) {
				genders.add(Genders.valueOf(resultSet.getString("gender")));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()
					&& Arrays.asList(Genders.values()).contains(Genders.valueOf(resultSet.getString("gender")))) {
				genders.add(Genders.valueOf(resultSet.getString("gender")));
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
		return genders;
	}

}
