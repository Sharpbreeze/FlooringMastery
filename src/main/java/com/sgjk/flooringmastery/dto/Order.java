/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author kaurj
 */
public class Order {
    int orderID;
    String customerName;
    String stateCode;
    BigDecimal taxRate;
    String productType;
    BigDecimal area;
    BigDecimal costPerSqFt;
    BigDecimal labourCostPerSqFt;
    BigDecimal materialCost;
    BigDecimal labourCost;
    BigDecimal taxAmount;
    BigDecimal grandTotal;
    LocalDate dueDate;
    LocalDate localDate;
    
    public Order(int orderID, String customerName, String stateCode, BigDecimal taxRate, String productType, BigDecimal area, BigDecimal costPerSqFt, BigDecimal labourCostPerSqFt, BigDecimal materialCost, BigDecimal labourCost, BigDecimal taxAmount, BigDecimal grandTotal, LocalDate dueDtae, LocalDate localDate) {
       this.orderID = orderID;
        this.customerName = customerName;
        this.stateCode = stateCode;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSqFt = costPerSqFt;
        this.labourCostPerSqFt = labourCostPerSqFt;
        this.materialCost = materialCost;
        this.labourCost = labourCost;
        this.taxAmount = taxAmount;
        this.grandTotal = grandTotal;
        this.dueDate = dueDate;
        this.localDate = localDate;
    }
    
    public Order (){
        
    }

    

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLabourCostPerSqFt() {
        return labourCostPerSqFt;
    }

    public void setLabourCostPerSqFt(BigDecimal labourCostPerSqFt) {
        this.labourCostPerSqFt = labourCostPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.orderID;
        hash = 83 * hash + Objects.hashCode(this.customerName);
        hash = 83 * hash + Objects.hashCode(this.stateCode);
        hash = 83 * hash + Objects.hashCode(this.taxRate);
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + Objects.hashCode(this.area);
        hash = 83 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 83 * hash + Objects.hashCode(this.labourCostPerSqFt);
        hash = 83 * hash + Objects.hashCode(this.materialCost);
        hash = 83 * hash + Objects.hashCode(this.labourCost);
        hash = 83 * hash + Objects.hashCode(this.taxAmount);
        hash = 83 * hash + Objects.hashCode(this.grandTotal);
        hash = 83 * hash + Objects.hashCode(this.dueDate);
        hash = 83 * hash + Objects.hashCode(this.localDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateCode, other.stateCode)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labourCostPerSqFt, other.labourCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.labourCost, other.labourCost)) {
            return false;
        }
        if (!Objects.equals(this.taxAmount, other.taxAmount)) {
            return false;
        }
        if (!Objects.equals(this.grandTotal, other.grandTotal)) {
            return false;
        }
        if (!Objects.equals(this.dueDate, other.dueDate)) {
            return false;
        }
        if (!Objects.equals(this.localDate, other.localDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerName=" + customerName + ", stateCode=" + stateCode + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area + ", costPerSqFt=" + costPerSqFt + ", labourCostPerSqFt=" + labourCostPerSqFt + ", materialCost=" + materialCost + ", labourCost=" + labourCost + ", taxAmount=" + taxAmount + ", grandTotal=" + grandTotal + ", dueDate=" + dueDate + ", localDate=" + localDate + '}';
    }

    
    
}