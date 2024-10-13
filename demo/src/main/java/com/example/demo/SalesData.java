package com.example.demo;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_data")
public class SalesData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String retailer;
    private String retailerId;
    private LocalDate invoiceDate;
    private String region;
    private String state;
    private String city;
    private String product;
    private double pricePerUnit;
    private int unitsSold;
    private double totalSales;
    private double operatingProfit;
    private double operatingMargin;
    private String salesMethod;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRetailer() {
        return retailer;
    }
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
    public String getRetailerId() {
        return retailerId;
    }
    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }
    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public int getUnitsSold() {
        return unitsSold;
    }
    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }
    public double getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
    public double getOperatingProfit() {
        return operatingProfit;
    }
    public void setOperatingProfit(double operatingProfit) {
        this.operatingProfit = operatingProfit;
    }
    public double getOperatingMargin() {
        return operatingMargin;
    }
    public void setOperatingMargin(double operatingMargin) {
        this.operatingMargin = operatingMargin;
    }
    public String getSalesMethod() {
        return salesMethod;
    }
    public void setSalesMethod(String salesMethod) {
        this.salesMethod = salesMethod;
    }
    public SalesData(Long id, String retailer, String retailerId, LocalDate invoiceDate, String region, String state,
            String city, String product, double pricePerUnit, int unitsSold, double totalSales, double operatingProfit,
            double operatingMargin, String salesMethod) {
        this.id = id;
        this.retailer = retailer;
        this.retailerId = retailerId;
        this.invoiceDate = invoiceDate;
        this.region = region;
        this.state = state;
        this.city = city;
        this.product = product;
        this.pricePerUnit = pricePerUnit;
        this.unitsSold = unitsSold;
        this.totalSales = totalSales;
        this.operatingProfit = operatingProfit;
        this.operatingMargin = operatingMargin;
        this.salesMethod = salesMethod;
    }
    public SalesData() {
    }

    // Constructors
}