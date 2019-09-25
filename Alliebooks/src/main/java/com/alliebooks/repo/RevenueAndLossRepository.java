package com.alliebooks.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.RevenueAndLoss;
import com.alliebooks.repo.custom.RevenueAndLossRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "v_revenue_and_loss", path = "v_revenue_and_loss")
public interface RevenueAndLossRepository extends CrudRepository<RevenueAndLoss, String>, RevenueAndLossRepositoryCustom{
	
}