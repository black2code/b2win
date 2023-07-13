package com.example.b2win.user.mapper;

import com.example.b2win.user.domain.Sex;
import com.example.b2win.user.domain.User;
import com.example.b2win.user.dto.AddUserRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T04:21:45+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperClassImpl implements UserMapperClass {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0159776256 = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

    @Override
    public User userCreateDtoToUser(AddUserRequest addUserRequest) {
        if ( addUserRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( addUserRequest.getBirthday() != null ) {
            user.birthday( LocalDate.parse( addUserRequest.getBirthday(), dateTimeFormatter_yyyy_MM_dd_0159776256 ) );
        }
        if ( addUserRequest.getSex() != null ) {
            user.sex( Enum.valueOf( Sex.class, addUserRequest.getSex() ) );
        }
        user.name( addUserRequest.getName() );
        user.account( addUserRequest.getAccount() );
        user.password( addUserRequest.getPassword() );
        user.email( addUserRequest.getEmail() );

        return user.build();
    }
}
