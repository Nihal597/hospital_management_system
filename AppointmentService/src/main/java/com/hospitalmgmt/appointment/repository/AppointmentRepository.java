package com.hospitalmgmt.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmgmt.appointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Get appointments by patient ID
    List<Appointment> findByPatientId(Integer patientId);

    // Get appointments by doctor ID
    List<Appointment> findByDoctorId(Integer doctorId);
}