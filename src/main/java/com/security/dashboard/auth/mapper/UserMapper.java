package com.security.dashboard.auth.mapper;

import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.entity.User;
import com.security.dashboard.auth.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // encoded later in service
    @Mapping(target = "role", expression = "java(com.security.dashboard.auth.Roles.USER)")
    @Mapping(target = "twoFactorEnabled", constant = "false")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "enabled", constant = "true")
    User toUser(RegisterRequest request);
}