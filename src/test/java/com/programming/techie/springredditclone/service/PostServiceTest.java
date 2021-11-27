package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.mapper.PostMapper;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import com.programming.techie.springredditclone.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private  PostRepository postRepository;
    @Mock
    private  SubredditRepository subredditRepository;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  AuthService authService;
    @Mock
    private  PostMapper postMapper;
    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

//    @BeforeEach
//    public void setUp(){
//
//    }

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

    @Test
    @DisplayName("Should save the post")
    public void ShouldSavePosts(){
        PostService postService = new PostService(postRepository,subredditRepository,userRepository,authService,postMapper);
        User currentUser = new User(123L,"test user","secret password","user@gmail.com",Instant.now(),true);
        Subreddit subreddit = new Subreddit(123L,"First Subreddit","Subreddit Description",emptyList(),Instant.now(),currentUser);
        Post post = new Post(123L,"First Post","http://url.site","Test",0,null, Instant.now(),null);
        PostRequest postRequest = new PostRequest(null,"First Subreddit","First Post","http://url.site","Test");
        Mockito.when(subredditRepository.findByName(("First Subreddit"))).thenReturn(Optional.of(subreddit));
        Mockito.when(postMapper.map(postRequest,subreddit,currentUser)).thenReturn(post);
        Mockito.when(authService.getCurrentUser()).thenReturn(currentUser);
        postService.save(postRequest);
//        Mockito.verify(postRepository,Mockito.times(1)).save(ArgumentMatchers.any(Post.class));
        Mockito.verify(postRepository,Mockito.times(1)).save(postArgumentCaptor.capture());
        assertThat(postArgumentCaptor.getValue().getPostId()).isEqualTo(123L);
        assertThat(postArgumentCaptor.getValue().getPostName()).isEqualTo("First Post");
    }
}