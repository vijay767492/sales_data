package com.example.demo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesDataService {

    private final SalesDataRepository salesDataRepository;

    public SalesDataService(SalesDataRepository salesDataRepository) {
        this.salesDataRepository = salesDataRepository;
    }

    public void saveAll(List<SalesData> salesDataList) {
        salesDataRepository.saveAll(salesDataList);
    }
}
