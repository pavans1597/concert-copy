package com.exza.tech.repository;

import com.exza.tech.model.Entity.Secured;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuredRepo  extends CrudRepository<Secured, Integer> {

}
