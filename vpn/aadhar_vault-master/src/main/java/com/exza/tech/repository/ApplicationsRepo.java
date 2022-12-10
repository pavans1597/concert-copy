package com.exza.tech.repository;
import com.exza.tech.model.Entity.Applications;
import com.exza.tech.model.Entity.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepo extends CrudRepository<Applications, Integer> {

    @Query("SELECT a FROM APPLICATIONS a WHERE appId = :appId ")
    Applications findByAppId(String appId);

    @Query("SELECT a FROM APPLICATIONS a WHERE clientCode = :client")
    List<Applications> findAllApp(Clients client);
}
