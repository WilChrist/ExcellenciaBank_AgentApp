package com.jsfstarterproject.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Administrator.class)
public class Administrator extends User {


    private List<Agency> createdAgencies;

    private List<Agent> createdAgents;


    private List<Currency> currencies;

    public List<Agency> getCreatedAgencies() {
        return createdAgencies;
    }

    public void setCreatedAgencies(List<Agency> createdAgencies) {
        this.createdAgencies = createdAgencies;
    }

    public List<Agent> getCreatedAgents() {
        return createdAgents;
    }

    public void setCreatedAgents(List<Agent> createdAgents) {
        this.createdAgents = createdAgents;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

}
