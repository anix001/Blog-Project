package com.blog.service.mapper;

import com.blog.domain.AppUser;
import com.blog.domain.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO domainTODto(AppUser user);
}
