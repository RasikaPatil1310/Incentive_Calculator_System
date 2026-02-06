package com.rasika.incentive_calculator.controller;

import com.rasika.incentive_calculator.entity.SalesEntity;
import com.rasika.incentive_calculator.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

@RestController
@RequestMapping("/upload")
public class SalesUploadController {

    @Autowired
    private SalesRepository salesRepository;

    @PostMapping("/sales")
    public String uploadSales(@RequestParam("file") MultipartFile file) {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                SalesEntity sale = new SalesEntity();
                sale.setEmployeeId(data[0]);
                sale.setBranch(data[1]);
                sale.setRole(data[2]);
                sale.setVehicleModel(data[3]);
                sale.setQuantity(Integer.parseInt(data[4]));
                sale.setSaleDate(LocalDate.parse(data[5]));
                sale.setVehicleType(data[6]);

                salesRepository.save(sale);
            }

            return "Sales CSV uploaded successfully";

        } catch (Exception e) {
            return "Error uploading sales CSV: " + e.getMessage();
        }
    }
}

