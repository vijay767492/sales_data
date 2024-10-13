package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesDataController {

    private final SalesDataService salesDataService;

    

    public SalesDataController(SalesDataService salesDataService) {
        this.salesDataService = salesDataService;
    }

    @GetMapping("/all")
    public List<SalesData> getAllSalesData() {
        return salesDataService.getAllSalesData();
    }

    @PostMapping
    public SalesData createSalesData(@RequestBody SalesData salesData) {
        return salesDataService.saveSalesData(salesData);
    }

    // Endpoint to upload CSV
    @PostMapping("/uploadcsv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        List<SalesData> salesDataList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming CSV is comma-separated
                // Create SalesData object from values (ensure you have the correct index)
                SalesData salesData = new SalesData();
                salesData.setRetailer(values[0]);
                salesData.setRetailerId(values[1]);
                salesData.setInvoiceDate(LocalDate.parse(values[2])); // Adjust date format as necessary
                salesData.setRegion(values[3]);
                salesData.setState(values[4]);
                salesData.setCity(values[5]);
                salesData.setProduct(values[6]);
                salesData.setPricePerUnit(Double.parseDouble(values[7].replace("$", "").replace(",", "")));
                salesData.setUnitsSold(Integer.parseInt(values[8].replace(",", "")));
                salesData.setTotalSales(Double.parseDouble(values[9].replace("$", "").replace(",", "")));
                salesData.setOperatingProfit(Double.parseDouble(values[10].replace("$", "").replace(",", "")));
                salesData.setOperatingMargin(Double.parseDouble(values[11].replace("%", "")) / 100);
                salesData.setSalesMethod(values[12]);

                salesDataList.add(salesData);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }

        // Save all SalesData objects to the database
        salesDataService.saveSalesDataList(salesDataList);
        return ResponseEntity.ok("CSV file uploaded successfully");
    }
}
