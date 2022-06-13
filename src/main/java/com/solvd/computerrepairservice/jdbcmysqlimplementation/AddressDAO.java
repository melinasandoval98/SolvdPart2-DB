package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solvd.computerrepairservice.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IAddressDAO;

public class AddressDAO implements IAddressDAO {
    public static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Addresses WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Addresses (street_number, street_name, city) VALUES (?,?,?)";
    private final String UPDATE_QUERY = "UPDATE Users SET street_number = ?, street_name = ?, city = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Addresses WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Addresses";
    private final String GET_BY_USER_ID_QUERY = "SELECT * FROM Address a JOIN Users u ON a.id = u.address_id WHERE u.id = 1";

    private Connection connection;

    public AddressDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private Address createAddress(ResultSet rs) {
        Address address = null;
        try {
            address = new Address(rs.getLong("id"), rs.getInt("street_number"), rs.getString("street_name"),
                    rs.getString("city"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return address;
    }

    @Override
    public Address getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        Address address = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                address = createAddress(resultSet);
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
        return address;
    }

    @Override
    public void insertEntity(Address entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setInt(1, entity.getStreetNumber());
            prepStat.setString(2, entity.getStreetName());
            prepStat.setString(3, entity.getCity());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public void updateEntity(Address entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setInt(1, entity.getStreetNumber());
            prepStat.setString(2, entity.getStreetName());
            prepStat.setString(3, entity.getCity());
            prepStat.setLong(4, entity.getAddressID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Address data of id = " + entity.getAddressID() + " has been updated successfully");
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
    public List<Address> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<Address> adresses = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                adresses.add(createAddress(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                adresses.add(createAddress(resultSet));
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
        return adresses;
    }

    @Override
    public Address getAddressByUserID(long userID) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        Address address = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_USER_ID_QUERY);
            prepStat.setLong(1, userID);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                address = createAddress(resultSet);
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
        return address;
    }

}
