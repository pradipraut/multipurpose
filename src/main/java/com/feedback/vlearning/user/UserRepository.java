package com.feedback.vlearning.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndStatus(String username,Status status);

    @Query("from User u where u.id=?1")
    User findUserById(Long id);

    List<User> findByStatus(Status status);

}
