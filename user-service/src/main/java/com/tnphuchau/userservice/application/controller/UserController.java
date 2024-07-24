package com.tnphuchau.userservice.application.controller;

import com.tnphuchau.userservice.application.common.ResultCode;
import com.tnphuchau.userservice.application.resource.request.LoginRequest;
import com.tnphuchau.userservice.application.resource.request.RegisterRequest;
import com.tnphuchau.userservice.application.resource.response.BasicResponse;
import com.tnphuchau.userservice.application.resource.response.LoginResponse;
import com.tnphuchau.userservice.application.resource.response.RegisterResponse;
import com.tnphuchau.userservice.application.resource.response.ResultResponse;
import com.tnphuchau.userservice.infrastructure.repository.UserRepository;
import com.tnphuchau.userservice.domain.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Value("${jwt.privateKey}")
//    private String privateKey;
//
//    @Value("${jwt.publicKey}")
//    private String publicKey;
//
//    private final HttpSession session;
//
//    public UserController(HttpSession session) {
//        this.session = session;
//    }

    @PostMapping("/login")
    public ResponseEntity<ResultResponse<LoginResponse>> authenticateUser(@RequestBody LoginRequest loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getData().getUsernameOrEmail(),
                            loginDto.getData().getPassword()
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);

//            String jwtToken = UtilsHelper.encodeJWT(loginDto.getData().getUsernameOrEmail(), privateKey);
//            String refreshToken = UtilsHelper.encodeRefreshToken(loginDto.getData().getUsernameOrEmail(), privateKey);
//
//            session.setAttribute("jwtToken: ", jwtToken);
//            session.setAttribute("refreshToken: ", refreshToken);
//
//            System.out.println("Token: Bearer " + jwtToken);
//            System.out.println("Refresh-Token: Bearer " + jwtToken);

            ResultResponse<LoginResponse> resultResponse = userService.createLoginResponse(true);

            return new ResponseEntity<>(resultResponse, HttpStatus.OK);
        } catch (Exception e) {
            ResultResponse<LoginResponse> resultResponse = userService.createLoginResponse(false);
            return new ResponseEntity<>(resultResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResultResponse<RegisterResponse>> registerUser(@RequestBody RegisterRequest registerDto) {
        try {
            ResultResponse<RegisterResponse> resultResponse = userService.createRegisterResponse(registerDto);
            HttpStatus status = resultResponse.getResponse().getResponseCode().equals(ResultCode.SUCCESS.getCode())
                    ? HttpStatus.OK
                    : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(resultResponse, status);
        } catch (Exception e) {
            ResultResponse<RegisterResponse> resultResponse = new ResultResponse<>();
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResponseId(UUID.randomUUID().toString());
            basicResponse.setResponseCode(ResultCode.SYSTEM_ERROR.getCode());
            basicResponse.setResponseMessage(ResultCode.SYSTEM_ERROR.getMessage());
            resultResponse.setResponse(basicResponse);

            return new ResponseEntity<>(resultResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
