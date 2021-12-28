package ru.development.users.mapper;

import org.mapstruct.Mapper;
import ru.development.users.dto.UserDto;
import ru.development.users.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
