package com.wygeeks.controller;

import com.wygeeks.dto.request.UserRequestDTO;
import com.wygeeks.dto.response.ResponseData;
import com.wygeeks.dto.response.ResponseError;
import com.wygeeks.exception.ResourceNotFoundException;
import com.wygeeks.service.UserService;
import com.wygeeks.util.Gender;
import com.wygeeks.util.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(value = "/", headers = "apiKey=v1.0")
//    @RequestMapping(value = "/", method = RequestMethod.POST, headers ="apiKey=v1.0")
    @PostMapping("/")
    public ResponseData<UserRequestDTO> addUser(@Valid @RequestBody UserRequestDTO user) {

//        if (user.getFirstName().equals("Duy")) {
//            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "User already exists");
//        }
        log.info("Creating user= {}", user.getFirstName());
        return new ResponseData<>(HttpStatus.CREATED.value(), "User created", user);
    }

    @PutMapping("/{userId}")
    public ResponseData<UserRequestDTO> updateUser(@PathVariable("userId") @Min(value = 1, message = "userId must be greater than 0") int userId, @Valid @RequestBody UserRequestDTO user) {
        return new ResponseData<>(HttpStatus.OK.value(), "User updated", user);
    }

    @PatchMapping("/{userId}")
    public ResponseData<String> updateStatus(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId, @RequestParam @Min(1) int status) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status updated");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId) {
        log.info("Deleting user with id={}", userId);
        userService.deleteUser(userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully");
//        try {
//            userService.deleteUser(userId);
//            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully");
//        } catch (ResourceNotFoundException e) {
//            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
//        }
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId) {
        UserRequestDTO data = new UserRequestDTO("Duy", "Le", "lppduy@gmail.com", "0975427777",
                new Date("07/27/1997"),
                UserStatus.ACTIVE,
                Gender.MALE,
                "ADMIN",
                List.of("ADMIN", "USER")
        );
        return new ResponseData<>(HttpStatus.OK.value(), "User found", data);
    }

    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        List<UserRequestDTO> dataList = List.of(
                new UserRequestDTO("Duy", "Le", "lppd1@gmail.com", "0975427777",
                        new Date("07/27/1997"),
                        UserStatus.ACTIVE,
                        Gender.MALE,
                        "ADMIN",
                        List.of("ADMIN", "USER")
                ),
                new UserRequestDTO("Duy", "Le", "lppd2@gmail.com", "0975427777",
                        new Date("07/27/1997"),
                        UserStatus.ACTIVE,
                        Gender.MALE,
                        "ADMIN",
                        List.of("ADMIN", "USER")
                )
        );

        return new ResponseData<>(HttpStatus.OK.value(), "Users found", dataList);
    }
}
