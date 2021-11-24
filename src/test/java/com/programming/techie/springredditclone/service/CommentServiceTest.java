package com.programming.techie.springredditclone.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 public class CommentServiceTest {

    @Test
    @DisplayName("Test should pass when name do not contain sear words")
    void ShouldNotContainSwearWordInsideComment() {
     CommentService commentService = new CommentService(null,null,null,null,null,null,null);
        Assertions.assertFalse(commentService.containsSwearWords("this is a clean comment"));
    }
    @Test
     @DisplayName("Should Throw Exception When Comment Contains Swear Word")

}