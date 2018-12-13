package com.SpringBoot;

import com.SpringBoot.security.UserAuth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
    public UserAuth getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserAuth) authentication.getPrincipal();
    }
}
