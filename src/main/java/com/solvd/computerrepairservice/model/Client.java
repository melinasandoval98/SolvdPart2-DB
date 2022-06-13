package com.solvd.computerrepairservice.model;

import java.util.List;

public class Client {
    private long clientID;
    private User user;

    List<ComputerForRepair> userComputersForRepair;

    public Client() {

    }

    public Client(long clientID, User user, List<ComputerForRepair> userComputersForRepair) {
        this.clientID = clientID;
        this.user = user;
        this.userComputersForRepair = userComputersForRepair;
    }

    public Client(long clientID) {
        this.clientID = clientID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ComputerForRepair> getUserComputersForRepair() {
        return userComputersForRepair;
    }

    public void setUserComputersForRepair(List<ComputerForRepair> userComputersForRepair) {
        this.userComputersForRepair = userComputersForRepair;
    }
}
