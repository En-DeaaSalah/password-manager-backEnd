package com.example.mainPackage.repositories;

import com.example.mainPackage.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<Users, Long> {



    boolean existsByEmail(String email);

    Users findByName(String userName);

    boolean existsByName(String userName);

}
