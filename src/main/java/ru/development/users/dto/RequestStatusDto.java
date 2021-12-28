package ru.development.users.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.development.users.model.UserStatus;

@Data
public class RequestStatusDto {

    private Long id;
    private UserStatus newStatus;
}
