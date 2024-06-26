package iocode.web.app.controller;

import iocode.web.app.dto.UserAuth;
import iocode.web.app.dto.UserReg;
import iocode.web.app.entity.User;
import iocode.web.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class UserController {

  private final UserService userService;

  @PostMapping("/auth")
  public ResponseEntity<String> authenticate(@RequestBody UserAuth authDetails){
    return ResponseEntity.ok(userService.authenticate(authDetails));
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody UserReg regDetails){
    return ResponseEntity.ok(userService.register(regDetails));
  }
}
