package ru.development.users.dto;

import lombok.Data;
import ru.development.users.model.UserStatus;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private UserStatus status;
    private String uri;
}
