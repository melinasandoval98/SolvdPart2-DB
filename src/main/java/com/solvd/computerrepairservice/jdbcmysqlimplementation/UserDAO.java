package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IUserDAO;
import com.solvd.computerrepairservice.model.User;

public class UserDAO implements IUserDAO {
	public static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
	private final String GET_BY_ID_QUERY = "SELECT * FROM Users WHERE id = ?";
	private final String INSERT_QUERY = "INSERT INTO Users (name, email, age, user_phone_number, adress_id, gender_id) VALUES (?,?,?,?,?,?)";
	private final String UPDATE_QUERY = "UPDATE Users SET name = 7, email = ?, age=?, user_phone_number=?, adress_id=?, gender_id=?";
	private final String REMOVE_QUERY = "DELETE FROM Users WHERE id = ?";
	private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Users";
	private Connection connection;
	private PhoneNumberDAO userPhoneNumberDAO;
	private AdressDAO userAdressDAO;
	private GenderDAO userGenderDAO;
	private ComputerForRepairDAO computerForRepairDAO;

	public UserDAO() {

	}

	public UserDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	private User createUser(ResultSet rs) {
		User user = null;
		try {
			long userID = rs.getLong("id");
			user = new User(userID, rs.getString("name"), rs.getInt("age"), rs.getString("email"),
					userPhoneNumberDAO.getEntityByID(rs.getLong("user_phone_number_id")),
					userAdressDAO.getEntityByID(rs.getLong("adress_id")),
					userGenderDAO.getEntityByID(rs.getLong("gender_id")),
					computerForRepairDAO.getComputerForRepairByUserID(userID));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return user;
	}

	@Override
	public User getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				user = createUser(resultSet);
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
		return user;
	}

	@Override
	public void insertEntity(User entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			prepStat.setString(1, entity.getUserName());
			prepStat.setString(2, entity.getUserEMail());
			prepStat.setInt(3, entity.getUserAge());
			prepStat.setLong(4, entity.getUserPhoneNumber().getPhoneNumberID());
			prepStat.setLong(5, entity.getUserAdress().getAdressID());
			prepStat.setLong(6, entity.getUserGender().getGenderID());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}
	}

	@Override
	public void updateEntity(User entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setString(1, entity.getUserName());
			prepStat.setString(2, entity.getUserEMail());
			prepStat.setInt(3, entity.getUserAge());
			prepStat.setLong(4, entity.getUserPhoneNumber().getPhoneNumberID());
			prepStat.setLong(5, entity.getUserAdress().getAdressID());
			prepStat.setLong(6, entity.getUserGender().getGenderID());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Address data of id = " + entity.getUserID() + " has been updated successfully");
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
				LOGGER.info("Address data of id = " + id + " has been deleted successfully");
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public List<User> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<User> users = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				users.add(createUser(resultSet));
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
		return users;
	}

}
