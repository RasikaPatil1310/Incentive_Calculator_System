package com.rasika.incentive_calculator.service;

import com.rasika.incentive_calculator.entity.SalesEntity;
import com.rasika.incentive_calculator.entity.StructuredRuleEntity;
import com.rasika.incentive_calculator.repository.SalesRepository;
import com.rasika.incentive_calculator.repository.StructuredRuleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncentiveCalculationService {

    private final SalesRepository salesRepository;
    private final StructuredRuleRepository ruleRepository;

    public IncentiveCalculationService(SalesRepository salesRepository,
                                       StructuredRuleRepository ruleRepository) {
        this.salesRepository = salesRepository;
        this.ruleRepository = ruleRepository;
    }

    public Map<String, Integer> calculateIncentives() {

        List<SalesEntity> sales = salesRepository.findAll();
        List<StructuredRuleEntity> rules = ruleRepository.findAll();

        // employeeId -> (vehicleType -> totalQty)
        Map<String, Map<String, Integer>> groupedSales = new HashMap<>();
        Map<String, Integer> incentives = new HashMap<>();

        // 1️⃣ Group sales
        for (SalesEntity sale : sales) {
            groupedSales
                    .computeIfAbsent(sale.getEmployeeId(), k -> new HashMap<>())
                    .merge(sale.getVehicleType(), sale.getQuantity(), Integer::sum);
        }

        // 2️⃣ Apply rules
        for (SalesEntity sale : sales) {

            String employeeId = sale.getEmployeeId();
            String role = sale.getRole();

            for (StructuredRuleEntity rule : rules) {

                boolean roleMatch =
                        role.equalsIgnoreCase(rule.getRole());

                boolean vehicleMatch =
                        sale.getVehicleType().toLowerCase()
                                .contains(rule.getVehicleType().toLowerCase());

                int totalQty =
                        groupedSales.get(employeeId)
                                .get(sale.getVehicleType());

                boolean quantityMatch =
                        totalQty >= rule.getMinUnits()
                                && totalQty <= rule.getMaxUnits();

                if (roleMatch && vehicleMatch && quantityMatch) {

                    int incentive =
                            rule.getBaseIncentive()
                                    + (totalQty * rule.getBonusPerUnit());

                    incentives.merge(employeeId, incentive, Integer::sum);
                }
            }
        }
        return incentives;
    }
}


