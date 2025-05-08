package com.hospitalmgmt.appointment.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;
    protected Integer age;
    protected Date dob;
    protected String nationality;
    @Column(name = "phone_number")
    protected Long phoneNumber;
    protected String email;

    public User(String name, Integer age, Date dob, String nationality, Long phoneNumber, String email) {
        super();
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
