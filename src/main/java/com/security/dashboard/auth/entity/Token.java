package com.security.dashboard.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tokens")
@Builder
public class Token
{

    @Id
    private String id;
    private String userId;
    private String refreshToken; // store refresh token
    private boolean expired;
    private boolean revoked;
    private Date createdAt;

}
