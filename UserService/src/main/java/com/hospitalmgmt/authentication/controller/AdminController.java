package com.hospitalmgmt.authentication.controller;

import com.hospitalmgmt.authentication.entity.Admin;
import com.hospitalmgmt.authentication.repository.AdminRepository;
import com.hospitalmgmt.authentication.service.JWTtokenService;
import com.hospitalmgmt.authentication.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
//@PreAuthorize("isAuthenticated()")
public class AdminController {

    @Autowired
    JWTtokenService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoService service;

//    @GetMapping("/admin")
////    @PreAuthorize("hasRole('ADMIN')")
//    public List<Admin> getAlladmin() {
//
//        return adminRepository.findAll();
//    }

//    @GetMapping("/admin/{id}")
////    @PreAuthorize("hasRole('ADMIN')")
//    public Admin getAdmin(@PathVariable int id) {
//        return adminRepository.findById(id).orElse(null);
//    }

//    @PostMapping("/admin")
////    @PreAuthorize("hasRole('ADMIN')")
//    public String createAdmin(@RequestBody Admin Admin){
//        return adminService.addUser(Admin);
//    }

//    @PutMapping("/admin")
////    @PreAuthorize("hasRole('ADMIN')")
//    public Admin updateAdmin(@RequestBody Admin Admin) {
//        return adminRepository.save(Admin);
//    }
//
//    @DeleteMapping("/admin/{id}")
////    @PreAuthorize("hasRole('ADMIN')")
//    public String deleteAdmin(@PathVariable int id) {
//        adminRepository.deleteById(id);
//        return "Admin with id : " + id + "deleted";
//    }

//    }



    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Admin userInfo) {
        return service.addUser(userInfo);
    }
        @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody Admin authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
        }
}
