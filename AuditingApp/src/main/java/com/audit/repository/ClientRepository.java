package com.audit.repository;

import org.springframework.data.repository.CrudRepository;

import com.audit.domain.ClientDomain;

public interface ClientRepository extends CrudRepository<ClientDomain, Integer> {

}
