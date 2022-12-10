package com.exza.tech.repository;
import com.exza.tech.model.Entity.Clients;
import com.exza.tech.model.Entity.Requested;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface ClientRepo extends CrudRepository<Clients, Long> {


    @Query(value = "SELECT  c from clients c where c.clientName = :name")
    Clients findByKeywords(String clientName);



}




