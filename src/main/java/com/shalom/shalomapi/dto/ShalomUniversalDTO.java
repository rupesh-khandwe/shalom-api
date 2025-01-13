package com.shalom.shalomapi.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class ShalomUniversalDTO {

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
    private String profileImageUrl;
    private String[] imageList;
    private Boolean likeFlag;

    public ShalomUniversalDTO(){

    }

    @Override
    public String toString() {
        return "ShalomLikeCommentDTO{" +
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
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", imageList=" + Arrays.toString(imageList) +
                ", getLikeFlag=" + likeFlag +
                '}';
    }
}
