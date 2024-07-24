package com.tnphuchau.userservice.application.resource.response;

import com.tnphuchau.userservice.application.resource.response.data.RegisterResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private RegisterResponseData data;
}
