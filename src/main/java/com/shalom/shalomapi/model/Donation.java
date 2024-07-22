package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="donation", schema="shalom")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;
    private Long userId;
    private String phone;
    private Long amount;

    public Donation(){

    }

    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                '}';
    }
}
