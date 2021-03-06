/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.inventory.dao;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryInventoryDaoImpl implements FlooringMasteryInventoryDao{
    
    private Map<String, Inventory> inventoryMap = new HashMap<>();
    private String COMMA = ",";
    private String INVENTORY_FILE_PATH;
    
    public FlooringMasteryInventoryDaoImpl() {
        this.INVENTORY_FILE_PATH = "Inventory.txt";
    }
    
    public FlooringMasteryInventoryDaoImpl(String filePath){
        this.INVENTORY_FILE_PATH = filePath;
    }

    
    @Override
    public Inventory getProductType(String productType) throws FlooringMasteryPersistenceException {
       
            loadInventory();
        
        return inventoryMap.get(productType);
    }
    
    @Override
    public List<Inventory> getAllInventory() throws FlooringMasteryPersistenceException{
        loadInventory();
        return new ArrayList<>(inventoryMap.values());
    }
    
    
    private Inventory unmarshallInventory(String inventoryItem) {
        String[] token = inventoryItem.split(COMMA);
        String ProductID = (token[0]);
        BigDecimal costPerSqFt = new BigDecimal(token[1]);
        BigDecimal laborCostPerSqFt = new BigDecimal(token[2]);

        Inventory productItemFromFile = new Inventory(ProductID, costPerSqFt, laborCostPerSqFt);

        return productItemFromFile;
    }
    
    private void loadInventory() throws FlooringMasteryPersistenceException{
        Scanner reader;
        try {
            reader = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE_PATH)));
        } catch (FileNotFoundException ex) {
        throw new FlooringMasteryPersistenceException("Cannot read the inventory file");    
        }

        String currentLine;
        Inventory currentProduct;
        reader.nextLine();
        while (reader.hasNextLine()) {
            currentLine = reader.nextLine();
            currentProduct = unmarshallInventory(currentLine);
            inventoryMap.put(currentProduct.getProductType(), currentProduct);
           
        }
        
        reader.close();
    }
    
}
