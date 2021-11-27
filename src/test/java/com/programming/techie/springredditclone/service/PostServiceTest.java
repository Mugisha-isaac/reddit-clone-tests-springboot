package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.mapper.PostMapper;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import com.programming.techie.springredditclone.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private  PostRepository postRepository = Mockito.mock(PostRepository.class);
    private  SubredditRepository subredditRepository = Mockito.mock(SubredditRepository.class);
    private  UserRepository userRepository = Mockito.mock(UserRepository.class);
    private  AuthService authService = Mockito.mock(AuthService.class);
    private  PostMapper postMapper = Mockito.mock(PostMapper.class);

    @Test
    @DisplayName("Should Find Post By Id")
    void ShouldFindPostById() {
        PostService postService = new PostService(postRepository,subredditRepository,userRepository,authService,postMapper);
//        Post post = new Post(123L,"First Post","http://url.site","Test");
        Post post = new Post(123L,"First Post","http://url.site","Test",0,null, Instant.now(),null);
        PostResponse expectedPostResponse = new PostResponse(123L,"First Post","http://url.site","Test","Test User","Test Subredit",0,0,"1 Hour Ago",false,false);
        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));
        Mockito.when(postMapper.mapToDto(Mockito.any(Post.class))).thenReturn(expectedPostResponse);

        PostResponse actualResponse = postService.getPost(123L);
        assertThat(actualResponse.getId()).isEqualTo(expectedPostResponse.getId());
        assertThat(actualResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());
    }
}