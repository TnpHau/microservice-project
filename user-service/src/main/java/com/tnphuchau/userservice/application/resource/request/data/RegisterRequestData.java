package com.tnphuchau.userservice.application.resource.request.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestData {
    private String name;
    private String username;
    private String email;
    private String password;
}
