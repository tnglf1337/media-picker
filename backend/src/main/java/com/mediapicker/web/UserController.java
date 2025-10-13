package com.mediapicker.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @PostMapping("/benutzer-registrieren")
  public ResponseEntity<Void> registriererUser() {
    //TODO implement
    return null;
  }

  @GetMapping("/login")
  public ResponseEntity<Void> login() {
    //TODO implement
    return null;
  }
}
