package com.hospitalmgmt.authentication.service;

import com.hospitalmgmt.authentication.entity.Admin;
import com.hospitalmgmt.authentication.entity.UserInfoDetails;
import com.hospitalmgmt.authentication.repository.AdminRepository;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class UserInfoService implements UserDetailsService {

        @Autowired
        private AdminRepository repository;

        @Autowired
        PasswordEncoder encoder;

        @Override
        public UserInfoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Admin> userDetail = repository.findByusername(username); // Assuming 'email' is used as username

            // Converting UserInfo to UserDetails
            return userDetail.map(UserInfoDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        }

        public String addUser(Admin userInfo) {
            // Encode password before saving the user
            userInfo.setPassword((encoder.encode(userInfo.getPassword())));
            repository.save(userInfo);
            return "User Added Successfully";
        }
}
