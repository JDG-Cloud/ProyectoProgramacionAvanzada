package co.edu.uniquindio.CommuSafe.modules.user.controller;

import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserModificationResponse> update(@RequestBody UserModificationRequest userModificationRequest) {
        var userModificationResponse = userService.update(userModificationRequest);
        return ResponseEntity.ok(userModificationResponse);
    }
}
