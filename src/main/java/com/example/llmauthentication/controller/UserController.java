package com.example.llmauthentication.controller;

import com.example.llmauthentication.model.User;
import com.example.llmauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{externalUserId}")
    public ResponseEntity<User> getUserByExternalUserId(@PathVariable String externalUserId) {
        User user = userService.findByExternalUserId(externalUserId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.insertUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}/access")
    public ResponseEntity<Void> updateCanAccess(@PathVariable String studentId, @RequestParam("canAccess") int canAccess) {
        userService.updateCanAccess(studentId, canAccess);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{externalUserId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String externalUserId) {
        userService.deleteUser(externalUserId);
        return ResponseEntity.ok().build();
    }
}
