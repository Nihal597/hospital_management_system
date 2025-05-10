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
import com.hospitalmgmt.authentication.entity.Patient;
import com.hospitalmgmt.authentication.repository.PatientRepository;

@RestController
public class PatientController {

    @Autowired
    PatientRepository PatientRepository;

    @Autowired
    private AppointmentClient appointmentClient;

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
    public Patient createPatient(@RequestBody Patient Patient) {
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

    @GetMapping("/patients/{id}/appointments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Object getDoctorAppointments(@PathVariable int id) {
        try {
            return appointmentClient.getAppointmentsByPatientId(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving appointments: " + e.getMessage());
        }
    }
}
