package com.wygeeks.dto.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
