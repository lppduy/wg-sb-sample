package com.wygeeks.controller;

import com.wygeeks.dto.request.UserRequestDTO;
import com.wygeeks.util.Gender;
import com.wygeeks.util.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @PostMapping(value = "/", headers = "apiKey=v1.0")
//    @RequestMapping(value = "/", method = RequestMethod.POST, headers ="apiKey=v1.0")
    @PostMapping("/")
    public String addUser(@Valid @RequestBody UserRequestDTO user) {
        return "User added";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable int userId, @Valid @RequestBody UserRequestDTO user) {
        System.out.println("Update user");
        return "User updated";
    }

    @PatchMapping("/{userId}")
    public String updateStatus(@PathVariable @Min(1) int userId, @RequestParam @Min(1) int status) {
        return "User Status changed";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@Min(1) @PathVariable int userId){
        return "User deleted";
    }

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable int userId) {
        return new UserRequestDTO("Duy", "Le", "lppduy@gmail.com", "0975427777",
                new Date("07/27/1997"),
                UserStatus.ACTIVE,
                Gender.MALE,
                "ADMIN",
                List.of("ADMIN", "USER")
        );
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return List.of(
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
    }
}
