package com.shalom.shalomapi.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="shalomlike", schema="shalom")
public class ShalomLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_like_id")
    private Long shalomLikeId;
    private Long userId;
    private Long shalomId;
    private Boolean likeFlag;
    private LocalDateTime createdOn;

    public ShalomLike(){

    }

    @Override
    public String toString() {
        return "ShalomLike{" +
                "shalomLikeId=" + shalomLikeId +
                ", userId=" + userId +
                ", shalomId=" + shalomId +
                ", likeFlag=" + likeFlag +
                ", createdOn=" + createdOn +
                '}';
    }
}
