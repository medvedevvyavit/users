package ru.development.users.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.development.users.model.UserStatus;

@Data
@RequiredArgsConstructor
public class UserFilterDto {

    private Long id;
    private UserStatus status;
}
