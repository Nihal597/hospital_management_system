package com.hospitalmgmt.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "Doctors")
public class Doctor extends User {
    private String employmentType;
    private String specialisation;
    @Column(name = "joined_date")
    private Date joinedDate;
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    public Doctor () {

    }

    public Doctor(String name, Integer age, Date dob, String nationality, Long phoneNumber, String email, String employmentType, String specialisation, Date joinedDate, Integer yearsOfExperience) {
        super(name, age, dob, nationality, phoneNumber, email);
        this.employmentType = employmentType;
        this.specialisation = specialisation;
        this.joinedDate = joinedDate;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
