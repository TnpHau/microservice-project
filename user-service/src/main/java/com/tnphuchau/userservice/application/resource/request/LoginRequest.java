package com.tnphuchau.userservice.application.resource.request;

import com.tnphuchau.userservice.application.resource.request.data.LoginRequestData;
import lombok.Data;

@Data
public class LoginRequest extends BasicRequest {
    private LoginRequestData data;
}
