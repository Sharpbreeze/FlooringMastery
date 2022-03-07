/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.order.dao;

import com.sgjk.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kaurj
 */
public interface FlooringMasteryOrderDao {
    Order addOrder (Order order)
            throws FlooringMasteryPersistenceException;
    Order editOrder (Order order) 
            throws FlooringMasteryPersistenceException;
    Order removeOrder (Order order) 
            throws FlooringMasteryPersistenceException;
    Order getAnOrder (LocalDate date, int orderID) 
            throws FlooringMasteryPersistenceException;
    List<Order> getAllOrders (LocalDate date) 
            throws FlooringMasteryPersistenceException;
    public void exportAllData () 
            throws FlooringMasteryPersistenceException;
}
