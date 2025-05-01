package com.hospitalmgmt.authentication.controller;

import com.hospitalmgmt.authentication.entity.Doctor;
import com.hospitalmgmt.authentication.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class DoctorController {

    @Autowired
    DoctorRepository DoctorRepository;


    @GetMapping("/doctors")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Doctor> getAllDoctors() {

        return DoctorRepository.findAll();
    }

    @GetMapping("/doctors/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Doctor getDoctor(@PathVariable int id) {
        return DoctorRepository.findById(id).orElse(null);
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor createDoctor(@RequestBody Doctor Doctor){
        return DoctorRepository.save(Doctor);
    }

    @PutMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor updateDoctor(@RequestBody Doctor Doctor) {
        return DoctorRepository.save(Doctor);
    }

    @DeleteMapping("/doctors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDoctor(@PathVariable int id) {
        DoctorRepository.deleteById(id);
        return "Doctor with id : " + id + "deleted";
    }
}
