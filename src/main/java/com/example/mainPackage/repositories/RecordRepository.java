package com.example.mainPackage.repositories;

import com.example.mainPackage.entites.Record;
import com.example.mainPackage.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {


   public List<Record> findAllByUser(Users user);
   public boolean existsById(Long id);

}
