package com.example.LMS.Library.repository;

import com.example.LMS.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by email for login
    User findByEmail(String email);
}