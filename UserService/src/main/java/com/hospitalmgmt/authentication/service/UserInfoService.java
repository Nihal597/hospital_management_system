package com.hospitalmgmt.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hospitalmgmt.authentication.entity.Admin;
import com.hospitalmgmt.authentication.entity.UserInfoDetails;
import com.hospitalmgmt.authentication.repository.AdminRepository;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate to communicate with appointment service

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserInfoDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> userDetail = repository.findByusername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));
    }

    public String addUser(Admin userInfo) {
        // Encode password before saving the user
        userInfo.setPassword((encoder.encode(userInfo.getPassword())));
        repository.save(userInfo);
        return "Admin Added Successfully";
    }

}
