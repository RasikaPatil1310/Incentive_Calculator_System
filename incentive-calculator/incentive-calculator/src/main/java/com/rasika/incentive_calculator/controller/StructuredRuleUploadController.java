package com.rasika.incentive_calculator.controller;

import com.rasika.incentive_calculator.entity.StructuredRuleEntity;
import com.rasika.incentive_calculator.repository.StructuredRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/upload")
public class StructuredRuleUploadController {

    @Autowired
    private StructuredRuleRepository ruleRepository;

    @PostMapping("/structured-rules")
    public String uploadRules(@RequestParam("file") MultipartFile file) {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                StructuredRuleEntity rule = new StructuredRuleEntity();
                rule.setRuleId(data[0]);
                rule.setRole(data[1]);
                rule.setVehicleType(data[2]);
                rule.setMinUnits(Integer.parseInt(data[3]));
                rule.setMaxUnits(Integer.parseInt(data[4]));
                rule.setBaseIncentive(Integer.parseInt(data[5]));
                rule.setBonusPerUnit(Integer.parseInt(data[6]));
                rule.setValidFrom(LocalDate.parse(data[7]));
                rule.setValidTo(LocalDate.parse(data[8]));

                ruleRepository.save(rule);
            }

            return "Structured rules uploaded successfully";

        } catch (Exception e) {
            return "Error uploading structured rules CSV: " + e.getMessage();
        }
    }
}
