package com.hospitalmgmt.authentication.controller;

import com.hospitalmgmt.authentication.entity.Doctor;
import com.hospitalmgmt.authentication.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    DoctorRepository DoctorRepository;

    @GetMapping("/doctors")
    public List<Doctor> getAlldoctors() {

        return DoctorRepository.findAll();
    }

    @GetMapping("/doctors/{id}")
    public Doctor getDoctor(@PathVariable int id) {
        return DoctorRepository.findById(id).orElse(null);
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor Doctor){
        return DoctorRepository.save(Doctor);
    }

    @PutMapping("/doctors")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return DoctorRepository.save(doctor);
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable int id) {
        DoctorRepository.deleteById(id);
        return "Doctor with id : " + id + "deleted";
    }
}
