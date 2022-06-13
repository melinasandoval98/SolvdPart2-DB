package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.solvd.computerrepairservice.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IUserDAO;

public class UserDAO implements IUserDAO {
    public static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT id, name, email FROM Users WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Users (name, email, user_phone_number_id, address_id) VALUES (?,?,?,?)";
    private final String UPDATE_QUERY = "UPDATE Users SET name = ?, email = ?, user_phone_number_id=?, address_id=? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Users WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT id, name, email FROM Users";

    private final String GET_BY_CLIENT_ID_QUERY = "SELECT id, name, email FROM Users u JOIN Clients c ON u.id = c.user_id WHERE c.id = ?";

    private final String GET_BY_EMPLOYEE_ID_QUERY = "SELECT id, name, email FROM Users u JOIN Employees e ON u.id = e.user_id WHERE e.id = ?";

    private Connection connection;

    public UserDAO() {

    }

    public UserDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private User createUser(ResultSet rs) {
        User user = null;
        try {
            user = new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
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
        return user;
    }

    @Override
    public void insertEntity(User entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setString(1, entity.getUserName());
            prepStat.setString(2, entity.getUserEMail());
            prepStat.setLong(3, entity.getUserPhoneNumber().getPhoneNumberID());
            prepStat.setLong(4, entity.getUserAddress().getAddressID());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
    }

    @Override
    public void updateEntity(User entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setString(1, entity.getUserName());
            prepStat.setString(2, entity.getUserEMail());
            prepStat.setLong(3, entity.getUserPhoneNumber().getPhoneNumberID());
            prepStat.setLong(4, entity.getUserAddress().getAddressID());
            prepStat.setLong(5, entity.getUserID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Address data of id = " + entity.getUserID() + " has been updated successfully");
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
            LOGGER.error("SQLException caught", e);
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
        return users;
    }

    @Override
    public User getUserByClientID(long clientID) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_CLIENT_ID_QUERY);
            prepStat.setLong(1, clientID);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
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
        return user;
    }

    @Override
    public User getUserByEmployeeID(long employeeID) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_EMPLOYEE_ID_QUERY);
            prepStat.setLong(1, employeeID);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
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
        return user;
    }
}
