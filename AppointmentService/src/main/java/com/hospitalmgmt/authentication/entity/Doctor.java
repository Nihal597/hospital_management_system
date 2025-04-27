package com.hospitalmgmt.authentication.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Doctors")
public class Doctor extends User {
    private String employmentType;
    private String specialisation;
    @Column(name = "joined_date")
    private Date joinedDate;
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    /**
     * @param name
     * @param age
     * @param dob
     * @param nationality
     * @param phoneNumber
     * @param email
     */
    public Doctor(String name, Integer age, Date dob, String nationality, Long phoneNumber, String email) {
        super(name, age, dob, nationality, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Doctor [employmentType=" + employmentType + ", specialisation=" + specialisation + ", joinedDate="
                + joinedDate + ", yearsOfExperience=" + yearsOfExperience + "]";
    }

    /**
     * @return the employmentType
     */
    public String getEmploymentType() {
        return employmentType;
    }

    /**
     * @param employmentType the employmentType to set
     */
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    /**
     * @return the specialisation
     */
    public String getSpecialisation() {
        return specialisation;
    }

    /**
     * @param specialisation the specialisation to set
     */
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * @return the joinedDate
     */
    public Date getJoinedDate() {
        return joinedDate;
    }

    /**
     * @param joinedDate the joinedDate to set
     */
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /**
     * @return the yearsOfExperience
     */
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * @param yearsOfExperience the yearsOfExperience to set
     */
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

}
