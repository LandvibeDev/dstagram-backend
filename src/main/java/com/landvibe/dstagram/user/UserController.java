package com.landvibe.dstagram.user;

import com.landvibe.dstagram.security.model.AuthUser;
import com.landvibe.dstagram.user.model.DstagramUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DstagramUser> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public DstagramUser createUser(@RequestBody AuthUser user) {
        DstagramUser dstagramUser = new DstagramUser(0, user.getEmail(), passwordEncoder.encode(user.getPassword()), "ROLE_USER", true);
        return this.userService.save(dstagramUser);
    }
}
