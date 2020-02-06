package com.growit.api.service;

import com.growit.api.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserDetails")
public class UserService implements UserDetailsService {

//

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      //  User user = userRepo.findByUsername(username); // look in both inv and b repos
    /*    if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }*/
       // return user;
        return null;
    }
}
