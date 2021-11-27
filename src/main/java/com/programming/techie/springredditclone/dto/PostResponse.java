package com.programming.techie.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;

//    public PostResponse(Long id, String postName, String url, String description, String userName, String subredditName, Integer voteCount, Integer commentCount, String duration, boolean upVote, boolean downVote) {
//        this.id = id;
//        this.postName = postName;
//        this.url = url;
//        this.description = description;
//        this.userName = userName;
//        this.subredditName = subredditName;
//        this.voteCount = voteCount;
//        this.commentCount = commentCount;
//        this.duration = duration;
//        this.upVote = upVote;
//        this.downVote = downVote;
//    }
}
