package com.temp.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.temp.core.BaseMsgCode;
import com.temp.jpa.UserRepository;
import com.temp.jpa.entity.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            List<UserEntity> userList = userRepository.getJpaRepository().findByUserName(username);
            if (userList.isEmpty()) {
            	throw new UsernameNotFoundException(BaseMsgCode.LOGIN__ERROR__USERNAME__NOT_FOUND.getMemo());
            }
            UserEntity user = userList.get(0);
            String userName = user.getUserName();
            String password = user.getPassword();
            return new User(userName, password, new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + e.toString());
        }
    }
}