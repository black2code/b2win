package com.example.b2win.user.mapper;


import com.example.b2win.user.domain.User;
import com.example.b2win.user.dto.AddUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperClass {
    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "sex", target = "sex")
    User userCreateDtoToUser(AddUserRequest addUserRequest);
}
