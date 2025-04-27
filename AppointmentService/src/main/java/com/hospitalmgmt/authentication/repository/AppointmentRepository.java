package com.hospitalmgmt.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmgmt.authentication.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}