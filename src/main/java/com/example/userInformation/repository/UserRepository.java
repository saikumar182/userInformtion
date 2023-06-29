package com.example.userInformation.repository;

import com.example.userInformation.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Page<User> findByIdOrFirstNameOrLastNameOrEmailContaining(String id, String firstName, String lastName, String email, Pageable pageable);
    List<User> findByFirstName(String firstName);

    CharSequence findByFirstNameOrLastName(String firstName, String lastName);

    Page<User> findAll(Specification<User> specification, Pageable pageable);

//    Page<User> findAll(Pageable pageable);



}