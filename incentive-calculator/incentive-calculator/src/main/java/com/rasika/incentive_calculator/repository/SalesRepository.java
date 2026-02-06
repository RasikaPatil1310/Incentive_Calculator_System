package com.rasika.incentive_calculator.repository;

import com.rasika.incentive_calculator.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}
