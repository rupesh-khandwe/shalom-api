package com.shalom.shalomapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="state", schema="shalom")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id")
    @Getter
    private Long stateId;
    @Getter
    private Long countryId;
    @Getter
    private String stateName;

    public State(){

    }

    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", countryId=" + countryId +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
