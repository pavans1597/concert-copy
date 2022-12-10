package com.exza.tech.repository;

import com.exza.tech.model.Entity.Clients;
import com.exza.tech.model.Entity.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo, Long> {


//    @Query(value = "SELECT distinct u from userinfo u where u.userId = :userId and u:companyCode")
//    UserInfo findByUserId(String userId, Long companyCode);

}