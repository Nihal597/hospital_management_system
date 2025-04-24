package com.hospitalmgmt.authentication.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Date dob;
    private String nationality;
    @Column(name = "phone_number")
    private Long phoneNumber;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User () {
        super();
    }

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
