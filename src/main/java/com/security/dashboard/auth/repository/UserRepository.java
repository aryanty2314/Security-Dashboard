package com.security.dashboard.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.security.dashboard.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
