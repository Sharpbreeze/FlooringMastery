/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.servicelayer;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryOrderNotFoundException extends Exception{
    public FlooringMasteryOrderNotFoundException(String message) {
        super(message);
    }
    
    public FlooringMasteryOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
