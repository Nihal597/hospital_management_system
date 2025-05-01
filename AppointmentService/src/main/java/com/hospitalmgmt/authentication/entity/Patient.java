package com.hospitalmgmt.authentication.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Patients")
public class Patient extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "insurance_number")
    private Long insuranceNumber;

    /**
     * @param name
     * @param age
     * @param dob
     * @param nationality
     * @param phoneNumber
     * @param email
     */
    public Patient(String name, Integer age, Date dob, String nationality, Long phoneNumber, String email) {
        super(name, age, dob, nationality, phoneNumber, email);
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the registrationDate
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the insuranceNumber
     */
    public Long getInsuranceNumber() {
        return insuranceNumber;
    }

    /**
     * @param insuranceNumber the insuranceNumber to set
     */
    public void setInsuranceNumber(Long insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", bloodGroup=" + bloodGroup + ", registrationDate=" + registrationDate
                + ", insuranceNumber=" + insuranceNumber + "]";
    }

}
