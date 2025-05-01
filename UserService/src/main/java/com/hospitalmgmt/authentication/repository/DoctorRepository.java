package com.hospitalmgmt.authentication.repository;

import com.hospitalmgmt.authentication.entity.Doctor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "doctors", collectionResourceRel = "doctors")
public interface DoctorRepository extends UserRepository<Doctor, Integer> {

}
