package com.rasika.incentive_calculator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "structured_rules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructuredRuleEntity {

    @Id
    private String ruleId;

    private String role;
    private String vehicleType;
    private Integer minUnits;
    private Integer maxUnits;
    private Integer baseIncentive;
    private Integer bonusPerUnit;

    private LocalDate validFrom;
    private LocalDate validTo;
}

