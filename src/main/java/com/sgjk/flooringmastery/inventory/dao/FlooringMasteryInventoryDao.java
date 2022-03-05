/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.inventory.dao;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;

/**
 *
 * @author kaurj
 */
public interface FlooringMasteryInventoryDao {
    Inventory getProductType (String Product)
            throws FlooringMasteryPersistenceException;
//    BigDecimal getCostPerSqFt (BigDecimal CostPerSqFt)
//            throws FlooringMasteryPersistenceException;
//    BigDecimal getLabourCostPerSqFt (BigDecimal LabourCostPerSqFt)
//           throws FlooringMasteryPersistenceException;
}
