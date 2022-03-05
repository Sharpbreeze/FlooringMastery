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
public class Tax {
  
    String stateCode;
    String stateName;
    BigDecimal taxRate;
    
    public Tax(String stateCode, String stateName, BigDecimal taxRate) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }
    
    public Tax() {
        
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
    
}
//State,StateName,TaxRate