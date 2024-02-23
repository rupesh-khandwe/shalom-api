package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userfollow", schema="shalom")
public class UserFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_seq_id")
    private Long followSeqId;
    private Long userId;
    private Long followId;
    //private Boolean followFlag;

    public UserFollow(){

    }

    @Override
    public String toString() {
        return "UserFollow{" +
                "followSeqId=" + followSeqId +
                ", userId=" + userId +
                ", followId=" + followId +
                '}';
    }
}
