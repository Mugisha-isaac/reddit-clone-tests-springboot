package com.programming.techie.springredditclone.Repository;

import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTestEmbedded {
    @Autowired
    private UserRepository userRepository;

    @Test

    public void ShouldSaveUser(){
        User user = new User(null,"test user","secret password","user@gmail.com", Instant.now(),true);
        User savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void ShouldSaveUserThroughSqlFile(){
        Optional<User> test = userRepository.findByUsername("testuser_sql");
        assertThat(test).isNotEmpty();
    }
}
