package com.solvd.computerrepairservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "client")
@XmlType(propOrder = {"clientID", "user", "computersForRepair"})
public class Client {
    @JsonProperty
    private long clientID;
    @JsonProperty
    private User user;
    @JsonProperty
    List<ComputerForRepair> computersForRepair;

    public Client() {

    }

    public Client(long clientID, User user, List<ComputerForRepair> userComputersForRepair) {
        this.clientID = clientID;
        this.user = user;
        this.computersForRepair = userComputersForRepair;
    }

    public Client(long clientID) {
        this.clientID = clientID;
    }

    public long getClientID() {
        return clientID;
    }

    @XmlAttribute(name = "clientID")
    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public User getUser() {
        return user;
    }

    @XmlElement(name = "user")
    public void setUser(User user) {
        this.user = user;
    }

    public List<ComputerForRepair> getComputersForRepair() {
        return computersForRepair;
    }

    @XmlElementWrapper(name = "computersForRepair")
    @XmlElement(name = "computerForRepair")
    public void setComputersForRepair(List<ComputerForRepair> computersForRepair) {
        this.computersForRepair = computersForRepair;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", user=" + user +
                ", userComputersForRepair=" + computersForRepair +
                '}';
    }
}
