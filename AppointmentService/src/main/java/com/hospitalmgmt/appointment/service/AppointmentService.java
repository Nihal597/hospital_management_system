package com.hospitalmgmt.appointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospitalmgmt.appointment.dtos.AppointmentDTO;
import com.hospitalmgmt.appointment.entity.Appointment;
import com.hospitalmgmt.appointment.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(AppointmentDTO dto) {
        dto.setStatus("Booked");
        Appointment appointment = new Appointment();
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setPatient(dto.getPatientId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());
        appointment.setReason(dto.getReason());
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

    public List<Appointment> getAppointmentsForPatient(Integer patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsForDoctor(Integer doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}