package com.example.mainPackage.repositories;

import com.example.mainPackage.entites.ShareOrder;
import com.example.mainPackage.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareOrderRepository extends JpaRepository<ShareOrder, Long> {

    List<ShareOrder> findAllByTargetUser(Users user);

}
