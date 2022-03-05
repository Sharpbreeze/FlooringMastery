/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.inventory.dao;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
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
    private Inventory inventory;
    
    public FlooringMasteryInventoryDaoImpl() {
        this.INVENTORY_FILE_PATH = "Inventory.txt";
    }

    
    @Override
    public Inventory getProductType(String productType) throws FlooringMasteryPersistenceException {
       
            loadInventory();
        
        return inventoryMap.get(productType);
    }
    
    // if (inventory.getProductType().equalsIgnoreCase(INVENTORY_FILE_PATH))  {
//        return inventoryMap.values().stream().map(item -> marshallInventory(item)) + "";
//    }  else {
//        System.out.println("No Match Found, Please try again!");
//    } 
//    return Product;
//
//    @Override
//    public BigDecimal getCostPerSqFt(BigDecimal CostPerSqFt) throws FlooringMasteryPersistenceException {//check out all these methods
//        if (inventory.getCostPerSqFt().equals(INVENTORY_FILE_PATH)) {
//        return (BigDecimal) inventoryMap.values().stream().map(item -> marshallInventory(item));
//    }   else {
//        System.out.println("No Match Found, Please try again!");
//    } 
//    return CostPerSqFt;}
//
//    @Override
//    public BigDecimal getLabourCostPerSqFt(BigDecimal LabourCostPerSqFt) throws FlooringMasteryPersistenceException {//check out all these methods
//        if (inventory.getLabourCostPerSqFt().equals(INVENTORY_FILE_PATH)) {
//        return (BigDecimal) inventoryMap.values().stream().map(item -> marshallInventory(item));
//    }   else {
//        System.out.println("No Match Found, Please try again!");
//    } 
//        return LabourCostPerSqFt;
//    }
    
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
    
//    private String marshallInventory(Inventory anItem) {// i do not need this method for this app?
//        String itemAsText = anItem.getProductType()+ COMMA;
//        itemAsText += anItem.getCostPerSqFt() + COMMA;
//        itemAsText += anItem.getLabourCostPerSqFt() + COMMA;
//
//        return itemAsText;
//    }
//
//    private void writeInventory() throws FlooringMasteryPersistenceException {// i do not need this method for this app?
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(INVENTORY_FILE_PATH));
//        } catch (IOException e) {
//            throw new FlooringMasteryPersistenceException(
//                    "Could not save data ", e);
//        }
//
//        inventoryMap.values().stream()
//                .map(item -> marshallInventory(item))
//                .forEachOrdered((lineItem) -> {
//                    out.println(lineItem);
//                    out.flush();
//                });
//        out.close();
//    }
    
}
