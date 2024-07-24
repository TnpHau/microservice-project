package com.tnphuchau.userservice.application.resource.request.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestData {
    private String usernameOrEmail;
    private String password;
}
