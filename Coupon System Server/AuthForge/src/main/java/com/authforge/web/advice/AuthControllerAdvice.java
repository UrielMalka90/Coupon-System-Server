package com.authforge.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.authforge.web.controller.AuthController;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.security.auth.message.AuthException;

@ControllerAdvice(assignableTypes = AuthController.class)
public class AuthControllerAdvice {

  @ExceptionHandler(AuthException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ProblemDetail handleAuthException(AuthException ex) {
    System.out.println(ex.getMessage());

    return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

  @ExceptionHandler({ ExpiredJwtException.class, UnsupportedJwtException.class, MalformedJwtException.class,
      SignatureException.class, IllegalArgumentException.class })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ProblemDetail handleJWTParseException() {
    return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,
        "Authentication failed: Invalid token.");
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ProblemDetail handleGeneralException(Exception ex) {
    System.out.println(ex.getMessage());
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
  }


}
