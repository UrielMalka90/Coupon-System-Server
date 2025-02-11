package com.authforge.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authforge.service.AuthForgeService;
import com.authforge.web.dto.LoginRequest;
import com.authforge.web.dto.SignUpRequest;
import com.authforge.web.dto.UserDto;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthForgeService authForgeService;

  @PostMapping("/sign-up")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
    if (authForgeService.signUp(signUpRequest.getUsername(), signUpRequest.getPassword())) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest)
      throws AuthException {
    String token = authForgeService.authenticateAndGenerateToken(loginRequest.getUsername(),
        loginRequest.getPassword());
    return ResponseEntity.ok(token);
  }

  @GetMapping("/parse-token")
  public ResponseEntity<UserDto> parseToken(@RequestHeader("Authorization") String token) {
    UserDto userDto = authForgeService.parseTokenAndGetUser(token);
    return ResponseEntity.ok(userDto);
  }


  @PutMapping("/update-details")
  public void updateDetails(@RequestHeader("Authorization") String token,@RequestBody SignUpRequest signUpRequest) {
    authForgeService.updateDetails(token, signUpRequest.getUsername(), signUpRequest.getPassword());
  }

}
