package com.wygeeks.controller;

import com.wygeeks.dto.request.UserRequestDTO;
import com.wygeeks.dto.response.ResponseData;
import com.wygeeks.dto.response.ResponseError;
import com.wygeeks.util.Gender;
import com.wygeeks.util.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @PostMapping(value = "/", headers = "apiKey=v1.0")
//    @RequestMapping(value = "/", method = RequestMethod.POST, headers ="apiKey=v1.0")
    @PostMapping("/")
    public ResponseData<UserRequestDTO> addUser(@Valid @RequestBody UserRequestDTO user) {

        if (user.getFirstName().equals("Duy")) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "User already exists");
        }

        return new ResponseData<>(HttpStatus.CREATED.value(), "User created", user);
    }

    @PutMapping("/{userId}")
    public ResponseData<UserRequestDTO> updateUser(@PathVariable int userId, @Valid @RequestBody UserRequestDTO user) {
        return new ResponseData<>(HttpStatus.OK.value(), "User updated", user);
    }

    @PatchMapping("/{userId}")
    public ResponseData<String> updateStatus(@PathVariable @Min(1) int userId, @RequestParam @Min(1) int status) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status updated");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<String> deleteUser(@Min(1) @PathVariable int userId){
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted");
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
