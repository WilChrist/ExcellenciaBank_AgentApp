package com.jsfstarterproject.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Currency.class)
public class Currency {
    private Long pk;
    private String name;
    private String code;
    private Double changeRateFromEuro;
    private Administrator author;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getChangeRateFromEuro() {
        return changeRateFromEuro;
    }

    public void setChangeRateFromEuro(Double changeRateFromEuro) {
        this.changeRateFromEuro = changeRateFromEuro;
    }

    public Administrator getAuthor() {
        return author;
    }

    public void setAuthor(Administrator author) {
        this.author = author;
    }
}
