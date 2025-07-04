package com.security.dashboard.auth.mapper;
import java.util.Set;
import com.security.dashboard.auth.dto.request.RegisterRequest;
import com.security.dashboard.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // encoded later in service
    @Mapping(target = "role", constant = "USER") // default to USER role
    @Mapping(target = "twoFactorEnabled", constant = "false")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "enabled", constant = "true")
    User toUser(RegisterRequest request);
}
