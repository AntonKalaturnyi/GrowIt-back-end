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
import java.util.HashSet;
import java.util.Set;

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
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.REGISTERED_USER));
        user.setAge(Period.between(user.getBirthday().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears());
        user.setLastVisit(LocalDateTime.now());
        user.setActive(true);
        return new UserRegistrationDto(userRepo.save(user)); //new UserRegistrationDto(userRepo.save(user));
    }

    public User findByUsernameAndPassword(String email, String encodedPass) {

        User investor = investorRepo.findByEmail(email);

        if (investor !=  null)
        System.out.println("===INVESTOR===" + investor + "==="
                + '\n' + "+++" + investor.getPassword().equals(encodedPass) + "+++");

        User borrower = borrowerRepo.findByEmail(email);

        if (investor != null && investor.getPassword().equals(encodedPass)) { return investor; }
        if (borrower != null && borrower.getPassword().equals(encodedPass)) { return borrower; }
        else return userRepo.findByEmail(email);

    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
  //      User user = userRepo.findByEmail(email);
        User investor = investorRepo.findByEmail(email);
        User borrower = borrowerRepo.findByEmail(email);
        return borrower != null ? borrower : investor;
    }

    static void setRegisteredUserRole(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.REGISTERED_USER);
        user.setRoles(roles);
    }

    static void setUserFields(User user, UserRegistrationDto dto) {
        user.setName(dto.getName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setUserpic(dto.getUserpic());
        user.setGender(dto.getGender());
        user.setBirthday(dto.getBirthday());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setCreated(LocalDateTime.now());
        user.setUpdated(user.getCreated());
        user.setLastVisit(user.getUpdated());
    }
}
