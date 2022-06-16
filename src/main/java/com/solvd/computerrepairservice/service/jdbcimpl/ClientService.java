package com.solvd.computerrepairservice.service.jdbcimpl;

import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ClientDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerForRepairDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.Client;
import com.solvd.computerrepairservice.service.IClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientService implements IClientService {
    ClientDAO clientDAO;
    UserDAO userDAO;
    ComputerForRepairDAO computerForRepairDAO;

    @Override
    public Client getFullInformationOfEntityByID(long entityID) throws SQLException {
        Client client = clientDAO.getEntityByID(entityID);
        client.setUser(userDAO.getUserByClientID(entityID));
        client.setComputersForRepair(computerForRepairDAO.getComputersForRepairByClientID(entityID));
        return client;
    }

    @Override
    public List<Client> getFullInformationOfAllEntities() throws SQLException {
        List<Client> clients = clientDAO.getAll();
        clients.stream().forEach(client -> {
            client.setUser(userDAO.getUserByClientID(client.getClientID()));
            try {
                client.setComputersForRepair(computerForRepairDAO.getComputersForRepairByClientID(client.getClientID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return clients;
    }
}