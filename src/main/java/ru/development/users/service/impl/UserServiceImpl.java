package ru.development.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.development.users.dto.RequestStatusDto;
import ru.development.users.dto.ResponseStatusDto;
import ru.development.users.dto.UserDto;
import ru.development.users.dto.UserFilterDto;
import ru.development.users.exception.ExceptionConstants;
import ru.development.users.mapper.UserMapper;
import ru.development.users.model.User;
import ru.development.users.model.UserStatus;
import ru.development.users.repository.UserRepository;
import ru.development.users.repository.UserSpecification;
import ru.development.users.service.PhotoService;
import ru.development.users.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PhotoService photoService;
    private final UserRepository repository;
    private final UserMapper mapper;

    @Value("${default.photo.user}")
    private String defaultUserPhoto;

    private void doIntegrationStub() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long saveUser(UserDto userDto) {
        doIntegrationStub();
        if (photoService.isPhotoNotExists(userDto.getUri()) || userDto.getUri() == null){
            userDto.setUri(defaultUserPhoto);
        }
        return repository.save(mapper.toUser(userDto)).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        doIntegrationStub();
        return repository
                .findById(id)
                .map(mapper::toUserDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        ExceptionConstants.ENTITY_NOT_FOUND_MSG,
                        "User"
                )));
    }

    @Override
    public ResponseStatusDto changeStatus(RequestStatusDto requestStatusDto) {
        doIntegrationStub();
        User user = mapper.toUser(getUser(requestStatusDto.getId()));
        UserStatus oldStatus = user.getStatus();
        user.setStatus(requestStatusDto.getNewStatus());

        return toResponseStatusDto(repository.save(user), oldStatus);
    }

    private ResponseStatusDto toResponseStatusDto(User changedUser, UserStatus oldStatus) {
        ResponseStatusDto responseStatusDto = new ResponseStatusDto();
        responseStatusDto.setId(changedUser.getId());
        responseStatusDto.setNewStatus(changedUser.getStatus());
        responseStatusDto.setOldStatus(oldStatus);

        return responseStatusDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getStatistics(UserFilterDto filter) {
        doIntegrationStub();
        return repository
                .findAll(new UserSpecification(filter))
                .stream().map(mapper::toUserDto)
                .collect(Collectors.toList());
    }
}
