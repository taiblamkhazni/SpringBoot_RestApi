package com.SpringBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private TokenUser tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;



    @PostMapping(value = "/Login")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInUser signInUser) {

        if (!userRepository.existsByEmail(signInUser.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Check Your Email!"),
                    HttpStatus.BAD_REQUEST);
        }

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInUser.getUsername(), signInUser.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails =userService.loadUserByUsername(signInUser.getUsername());

            String token= tokenUtil.genereteToken(userDetails);
            // JwtResponse jwtResponse= new JwtResponse(token);
            return  ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Check Your Password!"),HttpStatus.BAD_REQUEST);
        }




    }
    @PostMapping("/Register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserAuth signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }


        if (userRepository.existsByEmail(signUpRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserAuth user = new UserAuth(signUpRequest.getUsername(),signUpRequest.getName(),signUpRequest.getPassword());


        userService.saveUser(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }




}
