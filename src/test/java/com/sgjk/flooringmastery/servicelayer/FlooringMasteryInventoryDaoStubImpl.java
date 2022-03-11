/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.servicelayer;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.inventory.dao.FlooringMasteryInventoryDao;
import com.sgjk.flooringmastery.inventory.dao.FlooringMasteryInventoryDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryInventoryDaoStubImpl implements FlooringMasteryInventoryDao {

    public Inventory testInventory;

    //Wood,5.15,4.75
    FlooringMasteryInventoryDaoStubImpl() {
        this.testInventory = new Inventory("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
   }

    @Override
    public Inventory getProductType(String Product) throws FlooringMasteryPersistenceException {

        return testInventory;

    }

    @Override
    public List<Inventory> getAllInventory() throws FlooringMasteryPersistenceException {

        List<Inventory> allInventory = new ArrayList<>();
        allInventory.add(testInventory);
        return allInventory;
        
    }

}
