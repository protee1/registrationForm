package com.softech.netika.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.softech.netika.model.User;
import com.softech.netika.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
User findByEmail(String email);
User Save(UserRegistrationDto registration);
}
