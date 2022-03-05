/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.tax.dao;

import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;

/**
 *
 * @author kaurj
 */
public interface FlooringMasteryTaxDao {
    Tax getTax (String stateAbb)  
           throws FlooringMasteryPersistenceException;
//    String getStateCode (String stateCode)
//            throws FlooringMasteryPersistenceException;
//    BigDecimal getTaxRate (BigDecimal taxRate)
//            throws FlooringMasteryPersistenceException;
//    
}
