package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesDataController {

    private final SalesDataService salesDataService;

   
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("M/d/yyyy");  // e.g. 1/21/2020
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("MM-dd-yyyy"); // e.g. 01-01-2020

    public SalesDataController(SalesDataService salesDataService) {
        this.salesDataService = salesDataService;
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            CSVParser csvParser = CSVFormat.Builder
                    .create(CSVFormat.DEFAULT)
                    .setHeader()  
                    .setSkipHeaderRecord(true)  
                    .build()
                    .parse(reader);

            List<SalesData> salesDataList = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                SalesData salesData = new SalesData();
                salesData.setRetailer(record.get("Retailer"));
                salesData.setRetailerId(record.get("Retailer ID"));

       
                String invoiceDate = record.get("Invoice Date");
                salesData.setInvoiceDate(parseDate(invoiceDate));

                salesData.setRegion(record.get("Region"));
                salesData.setState(record.get("State"));
                salesData.setCity(record.get("City"));
                salesData.setProduct(record.get("Product"));

               
                salesData.setPricePerUnit(parseCurrency(record.get("Price per Unit")));
                salesData.setUnitsSold(parseNumber(record.get("Units Sold")));
                salesData.setTotalSales(parseCurrency(record.get("Total Sales")));
                salesData.setOperatingProfit(parseCurrency(record.get("Operating Profit")));

             
                salesData.setOperatingMargin(parsePercentage(record.get("Operating Margin")));
                
                salesData.setSalesMethod(record.get("Sales Method"));

                salesDataList.add(salesData);
            }

            
            salesDataService.saveAll(salesDataList);

            return "File uploaded and data saved successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            return "Failed to upload file: " + e.getMessage();
        }
    }

   
    private double parseCurrency(String value) {
        return Double.parseDouble(value.replace("$", "").replace(",", "").trim());
    }

   
    private int parseNumber(String value) {
        return Integer.parseInt(value.replace(",", "").trim());
    }

 
    private double parsePercentage(String value) {
        return Double.parseDouble(value.replace("%", "").trim());
    }

   
    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER_1); 
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(dateStr, FORMATTER_2); 
            } catch (DateTimeParseException ex) {
                throw new RuntimeException("Invalid date format: " + dateStr);
            }
        }
    }
}
