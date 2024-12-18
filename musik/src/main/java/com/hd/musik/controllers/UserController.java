package com.hd.musik.controllers;


import com.hd.musik.entity.User;
import com.hd.musik.payload.SignInRequest;
import com.hd.musik.security.JwtTokenProvider;
import com.hd.musik.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        User newUser = this.userService.saveUser(user);
        if(user != null)
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest rq) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(rq.getUsername(), rq.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtCookie = jwtTokenProvider.generateJwtToken(authentication);


        return ResponseEntity.ok(jwtCookie);
    }
}
