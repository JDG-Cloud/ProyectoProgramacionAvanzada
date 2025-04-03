package co.edu.uniquindio.CommuSafe.user.controller;

import co.edu.uniquindio.CommuSafe.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.user.implementation.UserServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceInterface _userService;

    public UserController(UserServiceInterface userService) {
        _userService = userService;
    }

    @PostMapping("/create")
    public UserCreationResponse login(@RequestBody UserCreationRequest userCreationRequest) {
        return _userService.create(userCreationRequest);
    }
}
