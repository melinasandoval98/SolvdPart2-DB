package com.solvd.computerrepairservice.jdbcmysqlimplementation;

import com.solvd.computerrepairservice.dao.IClientDAO;
import com.solvd.computerrepairservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO {
    public static final Logger LOGGER = LogManager.getLogger(ClientDAO.class);
    private final String GET_BY_ID_QUERY = "SELECT * FROM Clients WHERE id = ?";
    private final String INSERT_QUERY = "INSERT INTO Clients (user_id) VALUES (?)";
    private final String UPDATE_QUERY = "UPDATE Clients user_id = ? WHERE id = ?";
    private final String REMOVE_QUERY = "DELETE FROM Clients WHERE id = ?";
    private final String GET_ALL_VALUES_QUERY = "SELECT * FROM Clients";
    private Connection connection;

    public ClientDAO(Connection connection) {
        super();
        this.connection = connection;
    }

    private Client createEmployee(ResultSet rs) {
        Client client = null;
        try {
            client = new Client(rs.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
        return client;
    }

    @Override
    public Client getEntityByID(long id) {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prepStat = connection.prepareStatement(GET_BY_ID_QUERY);
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                client = createEmployee(resultSet);
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
        return client;
    }

    @Override
    public void insertEntity(Client entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(INSERT_QUERY)) {
            prepStat.setLong(1, entity.getUser().getUserID());
            if (prepStat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }
    }

    @Override
    public void updateEntity(Client entity) {
        try (PreparedStatement prepStat = connection.prepareStatement(UPDATE_QUERY)) {
            prepStat.setLong(1, entity.getUser().getUserID());
            prepStat.setLong(2, entity.getClientID());
            if (prepStat.executeUpdate() != 0) {
                LOGGER.info("Clients data of id = " + entity.getClientID() + " has been updated successfully");
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
                LOGGER.info("Clients data of id = " + id + " has been deleted successfully");
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        }

    }

    @Override
    public List<Client> getAll() throws SQLException {
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        List<Client> clients = new ArrayList<>();
        try {
            prepStat = connection.prepareStatement(GET_ALL_VALUES_QUERY);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                clients.add(createEmployee(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException caught", e);
        } finally {
            if (resultSet.next()) {
                clients.add(createEmployee(resultSet));
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
        return clients;
    }

}
