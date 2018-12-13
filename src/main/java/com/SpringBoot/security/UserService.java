package com.SpringBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
     PasswordEncoder encode;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user=userRepository.findByEmail(username);
        if (user==null){
            new  ResponseMessage("Fail -> Username is Not Not!");
        }
        return user;
    }

    public void saveUser(UserAuth user){
        user.setPassword(encode.encode(user.getPassword()));
            this.userRepository.save(user);
    }

    public List<UserAuth> findAll(){
        return userRepository.findAll();
    }



}
