package com.tnphuchau.userservice.application.resource.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseData {
    private String name;
    private String username;
    private String email;
}
