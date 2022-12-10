package com.exza.tech.repository;

import com.exza.tech.model.Entity.Vault;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaultRepo  extends CrudRepository<Vault, Long> {


    @Query("SELECT v FROM VAULT v WHERE vaultUuid = :request ")
    public Vault getaadhar(String request);


}
