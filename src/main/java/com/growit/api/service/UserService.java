package com.growit.api.service;

import com.growit.api.domain.Role;
import com.growit.api.domain.User;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;

@Service
@Qualifier("UserDetails")
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public UserRegistrationDto create(UserRegistrationDto userDto) {
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.REGISTERED_USER));
        user.setAge(Period.between(user.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        user.setLastVisit(LocalDateTime.now());
        user.setActive(true);
        return new UserRegistrationDto(userRepo.save(user)); //new UserRegistrationDto(userRepo.save(user));
    }
    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
      //  User user = userRepo.findByUsername(username); // look in both inv and b repos
    /*    if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }*/
       // return user;
        return null;
    }
}
