package ru.development.users.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.development.users.model.UserStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseStatusDto extends RequestStatusDto {

    private UserStatus oldStatus;
}
