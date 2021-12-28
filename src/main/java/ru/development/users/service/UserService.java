package ru.development.users.service;

import ru.development.users.dto.RequestStatusDto;
import ru.development.users.dto.ResponseStatusDto;
import ru.development.users.dto.UserDto;
import ru.development.users.dto.UserFilterDto;
import java.util.List;

public interface UserService {

    Long saveUser(UserDto userDto);
    UserDto getUser(Long id);
    ResponseStatusDto changeStatus(RequestStatusDto requestStatusDto);
    List<UserDto> getStatistics(UserFilterDto filter);
}
