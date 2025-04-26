package com.hospitalmgmt.authentication.repository;

import com.hospitalmgmt.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface UserRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
