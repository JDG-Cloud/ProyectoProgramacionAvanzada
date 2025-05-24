package co.edu.uniquindio.CommuSafe.modules.user.controller;

import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserResponse;
import co.edu.uniquindio.CommuSafe.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<UserCreationResponse> create(@RequestBody UserCreationRequest userCreationRequest) {
        var userCreationResponse = userService.create(userCreationRequest);
        return ResponseEntity.ok(userCreationResponse);
    }

    @PutMapping("update")
    public ResponseEntity<UserResponse> update(@RequestBody UserModificationRequest userModificationRequest) {
        var userModificationResponse = userService.update(userModificationRequest);
        return ResponseEntity.ok(userModificationResponse);
    }

    @DeleteMapping("/user")
    public ResponseEntity<UserResponse> deleteUser(@RequestBody UserModificationRequest request) {
        UserResponse response = userService.delete(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("profile_image")
    public ResponseEntity<UserResponse> uploadUserProfileImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("userId") String userId) {
        var userResponse = userService.uploadUserProfileImage(file,userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
