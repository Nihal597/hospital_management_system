package com.hospitalmgmt.authentication.repository;

import com.hospitalmgmt.authentication.entity.Patient;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "patients" , collectionResourceRel = "patients")
public interface PatientRepository extends UserRepository<Patient, Integer> {

}
