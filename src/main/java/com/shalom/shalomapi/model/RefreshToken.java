package com.shalom.shalomapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refreshtokens", schema="shalom")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;
    private String token;
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserProfile userInfo;
}
