package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IPhoneNumberDAO;
import com.solvd.computerrepairservice.model.PhoneNumber;

public class PhoneNumberDAO implements IPhoneNumberDAO {
	public static final Logger LOGGER = LogManager.getLogger(PhoneNumberDAO.class);
	private final String GET_BY_ID_QUERY = "SELECT * FROM Phone_Numbers WHERE id=?";
	private final String INSERT_QUERY = "INSERT INTO Phone_Numbers (country_code, phone_number_body) VALUES (?,?,?)";
	private final String UPDATE_QUERY = "UPDATE Phone_Numbers SET country_code = ?, phone_number_body = ?";
	private final String REMOVE_QUERY = "DELETE FROM Phone_Numbers WHERE id = ?";
	private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Phone_Numbers";
	private Connection connection;

	public PhoneNumberDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	private PhoneNumber createPhoneNumber(ResultSet rs) {
		PhoneNumber phoneNumber = null;
		try {
			phoneNumber = new PhoneNumber(rs.getLong("id"), rs.getInt("country_code"), rs.getInt("phone_number_body"));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return phoneNumber;
	}

	@Override
	public PhoneNumber getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		PhoneNumber phoneNumber = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				phoneNumber = createPhoneNumber(resultSet);
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
		return phoneNumber;
	}

	@Override
	public void insertEntity(PhoneNumber entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			prepStat.setInt(1, entity.getCountryCode());
			prepStat.setLong(2, entity.getPhoneNumber());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(PhoneNumber entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setInt(1, entity.getCountryCode());
			prepStat.setLong(1, entity.getPhoneNumber());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info(
						"Phone Number data of id = " + entity.getPhoneNumberID() + " has been updated successfully");
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
				LOGGER.info("Phone Number data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}
	}

	@Override
	public List<PhoneNumber> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				phoneNumbers.add(createPhoneNumber(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				phoneNumbers.add(createPhoneNumber(resultSet));
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
		return phoneNumbers;
	}

	@Override
	public PhoneNumber getPhoneNumberByUserID(long userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
