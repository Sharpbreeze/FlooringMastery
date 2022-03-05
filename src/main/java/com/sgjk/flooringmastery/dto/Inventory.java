/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author kaurj
 */
public class Inventory {
    
    String productType;
    BigDecimal costPerSqFt;
    BigDecimal labourCostPerSqFt;
    
    public Inventory(String productType, BigDecimal costPerSqFt, BigDecimal labourCostPerSqFt) {
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.labourCostPerSqFt = labourCostPerSqFt;
    }
    
    public Inventory() {
        
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
    
}
