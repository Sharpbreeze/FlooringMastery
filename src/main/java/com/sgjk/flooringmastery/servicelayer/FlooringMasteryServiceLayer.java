/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.servicelayer;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.dto.Order;
import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaurj
 */
public interface FlooringMasteryServiceLayer {
    public Order getUnconfirmedOrder(Order order)
            throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException;
    public Order addOrder (Order order)
            throws FlooringMasteryPersistenceException;
    public Order editOrder (Order order) 
            throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException;
    Order removeOrder (Order order) 
            throws FlooringMasteryPersistenceException;
    Order getAnOrder (LocalDate date, int orderID) 
            throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException;
    List<Order> getAllOrders (LocalDate date) 
            throws FlooringMasteryPersistenceException;
//    List<Order> getOrdersByDate(LocalDate Date) 
//            throws FlooringMasteryPersistenceException;
    public void exportAllData () 
            throws FlooringMasteryPersistenceException;
//    Inventory getProductType (Inventory Product)
//            throws FlooringMasteryPersistenceException;
//    BigDecimal getMaterialCost (Inventory inventory, Order order)
//            throws FlooringMasteryPersistenceException;
//    BigDecimal getLabourCost (Inventory inventory, Order order)
//            throws FlooringMasteryPersistenceException;
//    Tax getStateName (Tax stateName) 
//            throws FlooringMasteryPersistenceException;
//    public Order orderSummary (Order order, Tax tax)
//            throws FlooringMasteryPersistenceException;
//    BigDecimal grandTotal (Order order, Tax tax, Inventory inventory) 
//            throws FlooringMasteryPersistenceException;
    
}
