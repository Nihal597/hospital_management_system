package com.hospitalmgmt.authentication.controller;

import com.hospitalmgmt.authentication.entity.Patient;
import com.hospitalmgmt.authentication.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class PatientController {

    @Autowired
    PatientRepository PatientRepository;


    @GetMapping("/patients")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Patient> getAllpatients() {

        return PatientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Patient getPatient(@PathVariable int id) {
        return PatientRepository.findById(id).orElse(null);
    }

    @PostMapping("/patients")
    @PreAuthorize("hasRole('ADMIN')")
    public Patient createPatient(@RequestBody Patient Patient){
        return PatientRepository.save(Patient);
    }

    @PutMapping("/patients")
    @PreAuthorize("hasRole('ADMIN')")
    public Patient updatePatient(@RequestBody Patient Patient) {
        return PatientRepository.save(Patient);
    }

    @DeleteMapping("/patients/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePatient(@PathVariable int id) {
        PatientRepository.deleteById(id);
        return "Patient with id : " + id + "deleted";
    }
}
