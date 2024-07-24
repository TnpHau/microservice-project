package com.tnphuchau.userservice.domain.service;

import com.tnphuchau.userservice.application.common.ResultCode;
import com.tnphuchau.userservice.application.resource.request.RegisterRequest;
import com.tnphuchau.userservice.application.resource.response.BasicResponse;
import com.tnphuchau.userservice.application.resource.response.LoginResponse;
import com.tnphuchau.userservice.application.resource.response.RegisterResponse;
import com.tnphuchau.userservice.application.resource.response.ResultResponse;
import com.tnphuchau.userservice.application.resource.response.data.LoginResponseData;
import com.tnphuchau.userservice.application.resource.response.data.RegisterResponseData;
import com.tnphuchau.userservice.infrastructure.model.Role;
import com.tnphuchau.userservice.infrastructure.model.User;
import com.tnphuchau.userservice.infrastructure.repository.RoleRepository;
import com.tnphuchau.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public ResultResponse<LoginResponse> createLoginResponse(boolean isAuthenticated) {

//        String usernameOrEmail = loginDto.getData().getUsernameOrEmail();
//        String jwtToken = UtilsHelper.encodeJWT(usernameOrEmail, privateKey);
//        String refreshToken = UtilsHelper.encodeRefreshToken(usernameOrEmail, privateKey);
//
//        System.out.println("Authorization: Bearer " + jwtToken);
//        System.out.println("Refresh-Token: Bearer " + refreshToken);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + jwtToken);
//        headers.set("Refresh-Token", "Bearer " + refreshToken);

        LoginResponse loginResponse = new LoginResponse();
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setResponseId(UUID.randomUUID().toString());

        if (!isAuthenticated) {
            basicResponse.setResponseCode(ResultCode.ACCOUNT_OR_PASSWORD_IS_INCORRECT.getCode());
            basicResponse.setResponseMessage(ResultCode.ACCOUNT_OR_PASSWORD_IS_INCORRECT.getMessage());
        } else {
            basicResponse.setResponseCode(ResultCode.SUCCESS.getCode());
            basicResponse.setResponseMessage(ResultCode.SUCCESS.getMessage());
            LoginResponseData responseData = new LoginResponseData("Đăng nhập thành công");
            loginResponse.setData(responseData);
        }

        ResultResponse<LoginResponse> resultResponse = new ResultResponse<>();
        resultResponse.setResult(loginResponse);
        resultResponse.setResponse(basicResponse);
        return resultResponse;
    }

    public ResultResponse<RegisterResponse> createRegisterResponse(RegisterRequest registerRequest) {
        ResultResponse<RegisterResponse> resultResponse = new ResultResponse<>();
        BasicResponse basicResponse = new BasicResponse();
        RegisterResponse registerResponse = new RegisterResponse();
        RegisterResponseData registerResponseData = new RegisterResponseData();

        String username = registerRequest.getData().getUsername();
        String email = registerRequest.getData().getEmail();

        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            basicResponse.setResponseCode(ResultCode.EMAIL_OR_USERNAME_ALREADY_EXISTS.getCode());
            basicResponse.setResponseMessage(ResultCode.EMAIL_OR_USERNAME_ALREADY_EXISTS.getMessage());
        } else {
            // Successful registration
            User user = new User();
            user.setName(registerRequest.getData().getName());
            user.setUsername(registerRequest.getData().getUsername());
            user.setEmail(registerRequest.getData().getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getData().getPassword()));

            Role roles = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role not found: ROLE_USER"));
            user.setRoles(Collections.singleton(roles));
            userRepository.save(user);

            registerResponseData.setName(user.getName());
            registerResponseData.setUsername(user.getUsername());
            registerResponseData.setEmail(user.getEmail());

            registerResponse.setData(registerResponseData);

            basicResponse.setResponseId(UUID.randomUUID().toString());
            basicResponse.setResponseCode(ResultCode.SUCCESS.getCode());
            basicResponse.setResponseMessage(ResultCode.SUCCESS.getMessage());
        }

        resultResponse.setResult(registerResponse);
        resultResponse.setResponse(basicResponse);

        return resultResponse;
    }

}