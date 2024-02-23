package com.shalom.shalomapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="shalomcomment", schema="shalom")
public class ShalomComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shalom_comment_id")
    private Long shalomCommentId;
    private Long userId;
    private String userName;
    private Long shalomId;
    private String shalomComment;
    //private Boolean commentFlag;

    public ShalomComment(){

    }

    @Override
    public String toString() {
        return "ShalomComment{" +
                "shalomCommentId=" + shalomCommentId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", shalomId=" + shalomId +
                ", shalomComment='" + shalomComment + '\'' +
                '}';
    }
}
