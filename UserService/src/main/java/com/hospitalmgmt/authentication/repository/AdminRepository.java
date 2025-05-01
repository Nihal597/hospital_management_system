package com.hospitalmgmt.authentication.repository;

import com.hospitalmgmt.authentication.entity.Admin;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "admin", collectionResourceRel = "admin")
public interface AdminRepository extends UserRepository<Admin,Integer> {
    Optional<Admin> findByusername(String username);
}
