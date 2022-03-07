/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.inventory.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryInventoryDaoImplTest {
    
  FlooringMasteryInventoryDao dao;  
  
    public FlooringMasteryInventoryDaoImplTest() {
        dao = new FlooringMasteryInventoryDaoImpl("testInventory.txt");
    }
    
    
    
    @Test
    public void testSomeMethod() {
    }
    
}
