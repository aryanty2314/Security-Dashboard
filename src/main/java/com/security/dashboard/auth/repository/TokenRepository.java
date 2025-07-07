package com.security.dashboard.auth.repository;

import com.security.dashboard.auth.entity.Token;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token,String>
{
    Optional<Token> findByRefreshTokenAndRevokedFalseAndExpiredFalse(String refreshToken);
    List<Token> findAllByUserId(String userId);
}

