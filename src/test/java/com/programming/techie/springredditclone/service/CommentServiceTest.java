package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.exceptions.SpringRedditException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

 public class CommentServiceTest {

    @Test
    @DisplayName("Test should pass when name do not contain sear words")
    void ShouldNotContainSwearWordInsideComment() {
     CommentService commentService = new CommentService(null,null,null,null,null,null,null);
//        Assertions.assertFalse(commentService.containsSwearWords("this is a clean comment"));
        assertThat(commentService.containsSwearWords("this is a clean comment")).isFalse();
    }
    @Test
     @DisplayName("Should Throw Exception When Comment Contains Swear Word")
     public void ShouldFailWhenCommentContainsSwearWord(){
        CommentService commentService = new CommentService(null,null,null,null,null,null,null);
//        System.out.println("Boolean gotten: " +  commentService.containsSwearWords("This is shit comment"));
//        SpringRedditException exception = assertThrows(SpringRedditException.class,() ->{
//            commentService.containsSwearWords("This is shitty comment");
//        });
//        assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));
        assertThatThrownBy(()->{
            commentService.containsSwearWords("this is shitty comment");
        }).isInstanceOf(SpringRedditException.class).hasMessage("Comments contains unacceptable language");
    }
}