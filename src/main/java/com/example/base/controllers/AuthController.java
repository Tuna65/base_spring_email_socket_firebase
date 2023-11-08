package com.example.base.controllers;

import com.example.base.enums.Status;
import com.example.base.exception.InvalidatedException;
import com.example.base.jwt.JwtRequest;
import com.example.base.jwt.JwtResponse;
import com.example.base.jwt.JwtTokenUtil;
import com.example.base.models.User;
import com.example.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserRepository userRepository;


  @PostMapping("/login")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {

    User user = userRepository.findByUserNameAndStatus(authenticationRequest.getEmail(),
        Status.ACTIVE);

    if (user == null) {
      throw new InvalidatedException("Tài khoản/Mật khẩu không chính xác!");
    } else if (Status.INACTIVE == user.getStatus()) {
      throw new InvalidatedException("Tài khoản đã bị khóa!");
    }

    authenticate(authenticationRequest.getEmail(),
        authenticationRequest.getPassword() + user.getSalt());

    final String token = jwtTokenUtil.generateToken(user);
    JwtResponse jwtResponse = new JwtResponse(token, user.getId(), user.getUserName(),
        user.getPermission()
        , jwtTokenUtil.getExpirationDateFromToken(token));
    return ResponseEntity.ok(jwtResponse);
  }

  //Xác thực
  private void authenticate(String username, String password) throws InvalidatedException {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      throw new InvalidatedException("Tài khoản/mật khẩu không chính xác!");
    }
  }
}
