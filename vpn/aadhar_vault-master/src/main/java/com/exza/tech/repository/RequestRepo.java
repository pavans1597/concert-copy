package com.exza.tech.repository;

import com.exza.tech.model.Entity.Applications;
import com.exza.tech.model.Entity.Clients;
import com.exza.tech.model.Entity.Requested;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo  extends CrudRepository<Requested, Long> {

    @Query("SELECT r FROM REQUESTED r WHERE AppId = :appId AND clientCode = :client")
    List<Requested> findByAppId(String appId ,Clients client);

    @Query("SELECT r FROM REQUESTED r WHERE EMPLOYEEID = :empId AND clientCode = :client ")
    List<Requested> findByEmpId(String empId , Clients client);

    @Query("SELECT r FROM REQUESTED r WHERE EMPLOYEEID LIKE '%'||:emp||'%'  AND clientCode = :client ")
    List<Requested> searchEmp(String emp , Clients client);

    @Query("SELECT r FROM REQUESTED r WHERE AppId LIKE '%'||:app||'%' AND clientCode = :client ")
    List<Requested> searchApp(String app , Clients client);

}
