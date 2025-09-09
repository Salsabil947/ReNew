package com.project.renew.controller;


import com.project.renew.DTO.LogInDto;
import com.project.renew.DTO.SignUPDto;
import com.project.renew.model.User;
import com.project.renew.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//handling incoming requests
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users") //: http://localhost:8080/api/users
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUPDto dto) {
        try {
            userService.signup(dto);
            return ResponseEntity.ok(Map.of("message", "Success"));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "That email is already registered."));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LogInDto dto) {
        User user = userService.login(dto);

        if (user != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "userId", user.getId(),
                    "email", user.getEmail()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }
}













