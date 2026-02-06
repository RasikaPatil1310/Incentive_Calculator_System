package com.rasika.incentive_calculator.controller;

import com.rasika.incentive_calculator.service.IncentiveCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController   //
public class IncentiveController {

    private final IncentiveCalculationService service;

    public IncentiveController(IncentiveCalculationService service) {
        this.service = service;
    }

    @GetMapping("/incentives")
    public Map<String, Integer> getIncentives() {
        return service.calculateIncentives();
    }
}

