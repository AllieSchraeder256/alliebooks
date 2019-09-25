package com.alliebooks.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.RentPayment;
import com.alliebooks.repo.custom.RentPaymentRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "rent_payments", path = "rent_payments")
public interface RentPaymentRepository extends CrudRepository<RentPayment, UUID>, RentPaymentRepositoryCustom {
	
}