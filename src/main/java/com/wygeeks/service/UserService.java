package com.wygeeks.service;

import com.wygeeks.dto.request.UserRequestDTO;
import com.wygeeks.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    public void deleteUser(int id) {
        if (id == 1) {
            throw new ResourceNotFoundException("Not found userId=" + id);
        }
    }
}
