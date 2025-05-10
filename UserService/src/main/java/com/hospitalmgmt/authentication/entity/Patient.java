package com.hospitalmgmt.authentication.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Patients")
public class Patient extends User {
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "insurance_number")
    private Long insuranceNumber;

    public Patient() {
        super();
    }

    public Patient(String name, Integer age, Date dob, String nationality, Long phoneNumber, String email,
            String bloodGroup, Date registrationDate, Long insuranceNumber) {
        super(name, age, dob, nationality, phoneNumber, email);
        this.bloodGroup = bloodGroup;
        this.registrationDate = registrationDate;
        this.insuranceNumber = insuranceNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(Long insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
