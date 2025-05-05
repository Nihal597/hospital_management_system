package com.hospitalmgmt.authentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmgmt.authentication.dtos.AppointmentDTO;
import com.hospitalmgmt.authentication.entity.Appointment;
import com.hospitalmgmt.authentication.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment bookAppointment(@RequestBody AppointmentDTO appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Optional<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public boolean cancelAppointment(@PathVariable Integer id) {
        return appointmentService.cancelAppointment(id);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsForPatient(@PathVariable Integer patientId) {
        return appointmentService.getAppointmentsForPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsForDoctor(@PathVariable Integer doctorId) {
        return appointmentService.getAppointmentsForDoctor(doctorId);
    }
}