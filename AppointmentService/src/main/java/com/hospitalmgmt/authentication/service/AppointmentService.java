package com.hospitalmgmt.authentication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospitalmgmt.authentication.entity.Appointment;
import com.hospitalmgmt.authentication.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus("Booked");
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> updateAppointment(Integer id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
            appointment.setReason(updatedAppointment.getReason());
            return appointmentRepository.save(appointment);
        });
    }

    public boolean cancelAppointment(Integer id) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setStatus("Cancelled");
            appointmentRepository.save(appointment);
            return true;
        }).orElse(false);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}