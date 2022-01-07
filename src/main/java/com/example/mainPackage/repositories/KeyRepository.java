package com.example.mainPackage.repositories;


import com.example.mainPackage.entites.SecureKey;
import com.example.mainPackage.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<SecureKey,Long> {

    SecureKey findByUser(Users user);
}
