package com.wygeeks.controller;

import com.wygeeks.dto.request.UserRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @PostMapping(value = "/", headers = "apiKey=v1.0")
    @RequestMapping(value = "/", method = RequestMethod.POST, headers ="apiKey=v1.0")
    public String addUser(@RequestBody UserRequestDTO user) {
        return "User added";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody UserRequestDTO user) {
        System.out.println("Update user");
        return "User updated";
    }

    @PatchMapping("/{userId}")
    public String updateStatus(@PathVariable boolean enable) {
        return "User Status changed";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId){
        return "User deleted";
    }

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable int userId) {
        return new UserRequestDTO("Duy", "Le", "lppduy@gmail.com", "0975427777");
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return List.of(
                new UserRequestDTO("Duy", "Le", "lppd1@gmail.com", "0975427777"),
                new UserRequestDTO("Duy", "Le", "lppd2@gmail.com", "0975427777")
        );
    }
}
