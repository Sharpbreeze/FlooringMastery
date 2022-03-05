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
public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao{
    private Map<String, Tax> stateTaxMap = new HashMap<>();
    private String COMMA = ",";
    private String TAX_FILE_PATH;
    //private Tax tax;
    
   
    public FlooringMasteryTaxDaoImpl() {
        this.TAX_FILE_PATH = "Tax.txt";
        }

    @Override
    public Tax getTax(String stateAbb) throws FlooringMasteryPersistenceException {
        
        
            loadStateTax();
        
        return stateTaxMap.get(stateAbb);
       } 

//    if (tax.getStateName().equals(TAX_FILE_PATH)) {
//          return stateTaxMap.values().stream().map(item -> marshallStateTax(item)) + "";
//      } else {
//          System.out.println("No Match Found, Please try again!");
//      }
//      return stateName;// how can I match, if user input(statename) equals (statename) in the file that I have created, then return the "statename".
//    
    
//    @Override
//    public String getStateCode(String stateCode) throws FlooringMasteryPersistenceException {
//        if (tax.getStateName().equals(TAX_FILE_PATH)) {
//            return stateTaxMap.values().stream().map(item -> marshallStateTax(item)) +"";
//        } else {
//          System.out.println("No Match Found, Please try again!");
//        }
//        return stateCode;// if user input (statename) equals (statename) in the file that I have created, then return the "statecode" that matches the (statename)
//    }
//
//    @Override
//    public BigDecimal getTaxRate(BigDecimal taxRate) throws FlooringMasteryPersistenceException {
//       if (tax.getStateName().equals(TAX_FILE_PATH)) {
//            return (BigDecimal) stateTaxMap.values().stream().map(item -> marshallStateTax(item));
//       }else {
//          System.out.println("No Match Found, Please try again!");
//        }
//        return taxRate;// if user input (statename) equals (statename) in the file that I have created, then return the "taxrate" that matches the (statename)
//    }
    
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

//    private String marshallStateTax(Tax anItem) {// i do not need this method for this app?
//        String itemAsText = anItem.getStateCode() + COMMA;
//        itemAsText += anItem.getStateName() + COMMA;
//        itemAsText += anItem.getTaxRate() + COMMA;
//
//        return itemAsText;
//    }
//
//    private void writeStateTax() throws FlooringMasteryPersistenceException {// i do not need this method for this app?
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(TAX_FILE_PATH));
//        } catch (IOException e) {
//            throw new FlooringMasteryPersistenceException(
//                    "Could not save data ", e);
//        }
//
//        stateTaxMap.values().stream()
//                .map(item -> marshallStateTax(item))
//                .forEachOrdered((lineItem) -> {
//                    out.println(lineItem);
//                    out.flush();
//                });
//        out.close();
//    }

}
