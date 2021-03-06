/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.tax.dao;

import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao{
    private Map<String, Tax> stateTaxMap = new HashMap<>();
    private String COMMA = ",";
    private String TAX_FILE_PATH;
    
   
    public FlooringMasteryTaxDaoImpl() {
        this.TAX_FILE_PATH = "Tax.txt";
        }

    public FlooringMasteryTaxDaoImpl(String filePath){
        this.TAX_FILE_PATH = filePath;
    }
    
    @Override
    public Tax getTax(String stateAbb) throws FlooringMasteryPersistenceException {
        
        
            loadStateTax();
        
        return stateTaxMap.get(stateAbb);
       } 
    
    @Override
    public List<Tax> getAllTax() throws FlooringMasteryPersistenceException{
        loadStateTax();
        return new ArrayList<>(stateTaxMap.values());
    }


    private Tax unmarshallStateTax(String taxItem) {
        String[] token = taxItem.split(COMMA);
        String stateCode = (token[0]);
        String stateName = (token[1]);
        BigDecimal stateTaxRate = new BigDecimal(token[2]);

        Tax taxItemFromFile = new Tax(stateCode, stateName, stateTaxRate);

        return taxItemFromFile;
    }

    private void loadStateTax() throws FlooringMasteryPersistenceException{
        Scanner reader;
        try {
            reader = new Scanner(new BufferedReader(new FileReader(TAX_FILE_PATH)));
        } catch (FileNotFoundException ex) {
          throw new FlooringMasteryPersistenceException("Could not read Tax file");
        }

        String currentLine;
        Tax currentTax;
        reader.nextLine();
        while (reader.hasNextLine()) {
            currentLine = reader.nextLine();
            currentTax = unmarshallStateTax(currentLine);
            stateTaxMap.put(currentTax.getStateCode(), currentTax);
        }
        reader.close();
    }



}
