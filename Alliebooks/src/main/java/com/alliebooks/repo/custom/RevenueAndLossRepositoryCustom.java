package com.alliebooks.repo.custom;

import com.alliebooks.entities.RevenueAndLoss;

public interface RevenueAndLossRepositoryCustom {

	public Iterable<RevenueAndLoss> findByYear(int year);
}
