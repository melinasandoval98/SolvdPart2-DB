package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IAdressDAO;
import com.solvd.computerrepairservice.model.Adress;

public class AdressDAO implements IAdressDAO {
	public static final Logger LOGGER = LogManager.getLogger(AdressDAO.class);
	private final String GET_BY_ID_QUERY = "SELECT * FROM Adresses WHERE id = ?";
	private final String INSERT_QUERY = "INSERT INTO Adresses (street_number, street_name, city) VALUES (?,?,?)";
	private final String UPDATE_QUERY = "UPDATE Users SET street_number = ?, street_name = ?, city = ?";
	private final String REMOVE_QUERY = "DELETE FROM Adresses WHERE id = ?";
	private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Adresses";
	private Connection connection;

	public AdressDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	private Adress createAdress(ResultSet rs) {
		Adress adress = null;
		try {
			adress = new Adress(rs.getLong("id"), rs.getInt("street_number"), rs.getString("street_name"),
					rs.getString("city"));
		} catch (SQLException e) {
			LOGGER.error("SQLEception catched", e);
		}
		return adress;
	}

	@Override
	public Adress getEntityByID(long id) {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		Adress adress = null;
		try {
			prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
			prepStat.setLong(1, id);
			resultSet = prepStat.executeQuery();
			if (resultSet.next()) {
				adress = createAdress(resultSet);
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
		return adress;
	}

	@Override
	public void insertEntity(Adress entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
			prepStat.setInt(1, entity.getStreetNumber());
			prepStat.setString(2, entity.getStreetName());
			prepStat.setString(3, entity.getCity());
			if (prepStat.executeUpdate() == 0) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		}

	}

	@Override
	public void updateEntity(Adress entity) {
		try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
			prepStat.setInt(1, entity.getStreetNumber());
			prepStat.setString(2, entity.getStreetName());
			prepStat.setString(3, entity.getCity());
			if (prepStat.executeUpdate() != 0) {
				LOGGER.info("Address data of id = " + entity.getAdressID() + " has been updated successfully");
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
	public List<Adress> getAll() throws SQLException {
		PreparedStatement prepStat = null;
		ResultSet resultSet = null;
		List<Adress> adresses = new ArrayList<>();
		try {
			prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
			resultSet = prepStat.executeQuery();
			while (resultSet.next()) {
				adresses.add(createAdress(resultSet));
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException catched", e);
		} finally {
			if (resultSet.next()) {
				adresses.add(createAdress(resultSet));
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
		return adresses;
	}

	@Override
	public Adress getAdressByUserID(long userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
