package ru.development.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.development.users.dto.RequestStatusDto;
import ru.development.users.dto.ResponseStatusDto;
import ru.development.users.dto.UserDto;
import ru.development.users.dto.UserFilterDto;
import ru.development.users.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Long> saveUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(service.saveUser(userDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<ResponseStatusDto> changeStatus(@RequestBody RequestStatusDto requestStatusDto){
        return new ResponseEntity<>(service.changeStatus(requestStatusDto), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<UserDto>> getStatistics(UserFilterDto filter){
        return new ResponseEntity<>(service.getStatistics(filter), HttpStatus.OK);
    }
}
