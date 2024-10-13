package com.example.demo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesDataService {

    private final SalesDataRepository salesDataRepository;

    public SalesDataService(SalesDataRepository salesDataRepository) {
        this.salesDataRepository = salesDataRepository;
    }

    public List<SalesData> getAllSalesData() {
        return salesDataRepository.findAll();
    }

    public SalesData saveSalesData(SalesData salesData) {
        return salesDataRepository.save(salesData);
    }

    public void saveSalesDataList(List<SalesData> salesDataList) {
        salesDataRepository.saveAll(salesDataList); // Save all at once
    }
}
