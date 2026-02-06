package com.rasika.incentive_calculator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeId;
    private String branch;
    private String role;
    private String vehicleType;
    private String vehicleModel;
    private Integer quantity;
    private LocalDate saleDate;
}

