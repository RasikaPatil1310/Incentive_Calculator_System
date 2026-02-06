package com.rasika.incentive_calculator.repository;

import com.rasika.incentive_calculator.entity.StructuredRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructuredRuleRepository extends JpaRepository<StructuredRuleEntity, String> {
}
