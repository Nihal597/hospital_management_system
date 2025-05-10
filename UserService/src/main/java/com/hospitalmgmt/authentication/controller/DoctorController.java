package com.hospitalmgmt.authentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmgmt.authentication.client.AppointmentClient;
import com.hospitalmgmt.authentication.entity.Doctor;
import com.hospitalmgmt.authentication.repository.DoctorRepository;

@RestController
public class DoctorController {

    @Autowired
    DoctorRepository DoctorRepository;

    @Autowired
    private AppointmentClient appointmentClient;

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

    @GetMapping("/doctors/{id}/appointments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Object getDoctorAppointments(@PathVariable int id) {
        try {
            return appointmentClient.getAppointmentsByDoctorId(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving appointments: " + e.getMessage());
        }
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor createDoctor(@RequestBody Doctor Doctor) {
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
