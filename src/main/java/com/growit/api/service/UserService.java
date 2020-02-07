package com.growit.api.service;

import com.growit.api.domain.Role;
import com.growit.api.domain.User;
import com.growit.api.dto.UserRegistrationDto;
import com.growit.api.repo.BorrowerRepo;
import com.growit.api.repo.InvestorRepo;
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

    private final UserRepo userRepo;
    private final InvestorRepo investorRepo;
    private final BorrowerRepo borrowerRepo;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepo userRepo, InvestorRepo investorRepo, BorrowerRepo borrowerRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.investorRepo = investorRepo;
        this.borrowerRepo = borrowerRepo;
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
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        User investor = investorRepo.findByEmail(email);
        User borrower = borrowerRepo.findByEmail(email);
        return borrower != null ? borrower : investor != null ? investor : user;
    }
}
