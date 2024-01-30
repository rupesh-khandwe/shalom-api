package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ShalomLikeCommentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_id")
    private Long shalomId;
    private Long userId;
    private Boolean shalomFlag;
    private String shalom;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String imageUrl;
    private String videoUrl;
    private Long likeCount;
    private Long commentCount;
    private String userName;

    public ShalomLikeCommentDTO(){

    }

    @Override
    public String toString() {
        return "Shalom{" +
                "shalomId=" + shalomId +
                ", userId=" + userId +
                ", shalomFlag=" + shalomFlag +
                ", shalom='" + shalom + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", userName='" + userName + '\'' +
                '}';
    }
}
