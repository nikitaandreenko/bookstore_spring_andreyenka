package com.company.controller.rest;

import com.company.controller.rest.exception_handling.EntityIncorrectData;
import com.company.controller.rest.exception_handling.NoSuchEntityException;
import com.company.service.UserService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    @GetMapping ("/users")
    public List<UserDto> getAll (){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        if (userDto == null){
            throw new NoSuchEntityException("There is no user with id = " + id + " in database");
        }

        return userDto;
    }

    @PostMapping ("/users")
    public UserDto addUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.create(userDto);
        return userDto1;
    }

    @PutMapping("/users")
    public UserDto updateUser (@RequestBody UserDto userDto){
        UserDto userDto1 = userService.update(userDto);
        return userDto1;
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        if(userDto == null){
            throw new NoSuchEntityException("There is no user with id = " + id + " in database");
        }
        userService.delete(id);
        return "User with id = " + id + " was deleted";
    }


    @ExceptionHandler
    public ResponseEntity <EntityIncorrectData> handleException (NoSuchEntityException exception){
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity <EntityIncorrectData> handleException (Exception exception){
        EntityIncorrectData data = new EntityIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
