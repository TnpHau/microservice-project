package com.tnphuchau.userservice.application.resource.request;

import com.tnphuchau.userservice.application.resource.request.data.RegisterRequestData;
import lombok.Data;

@Data
public class RegisterRequest {
    private RegisterRequestData data;
}
