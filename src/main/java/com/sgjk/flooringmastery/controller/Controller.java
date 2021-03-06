/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.controller;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.dto.Order;
import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import com.sgjk.flooringmastery.servicelayer.FlooringMasteryDataValidationException;
import com.sgjk.flooringmastery.servicelayer.FlooringMasteryOrderNotFoundException;
import com.sgjk.flooringmastery.servicelayer.FlooringMasteryServiceLayer;
import com.sgjk.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kaurj
 */
public class Controller {
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;
    private Order order;
    
    public Controller(FlooringMasteryView view, FlooringMasteryServiceLayer service){
        this.service = service;
        this.view = view;
    }

    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        
            while (keepGoing) {
                try{
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addAnOrder();
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        removeAnOrder();
                        break;
                   case 5:
                        exportAllData();
                       break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                  
                }
                }catch(FlooringMasteryPersistenceException | FlooringMasteryDataValidationException | FlooringMasteryOrderNotFoundException e){
            displayErrorMessage(e.getMessage());
        } 
            }
                exitMessege();
        
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void displayOrders() throws FlooringMasteryPersistenceException{
       LocalDate userDate = view.getUserDate();
        List<Order> orders = service.getAllOrders(userDate);
        view.displayOrderList(orders);
    }
    private void addAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException{
        view.displayMessage("***********Add necessary information*************");
        List<Tax> allTaxes = service.getAllTax();
        List<Inventory> allInventory = service.getAllInventory();
        Order currentOrder = view.getNewOrderInfo(allTaxes, allInventory);
        Order unconfirmedOrder = service.getUnconfirmedOrder(currentOrder);
        if (view.userConfirms(unconfirmedOrder)){
           Order confirmedOrder = service.addOrder(unconfirmedOrder);
            view.displaySuccessfullAdd(confirmedOrder);
}    
       
    }
    private void editAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException, FlooringMasteryOrderNotFoundException{
        view.displayMessage("***********Add necessary information to edit the order*************");
        LocalDate orderDate = view.getUserDate();
        int orderID = view.getOrderID();
        Order order = service.getAnOrder(orderDate, orderID);
        List<Tax> allTaxes = service.getAllTax();
        List<Inventory> allInventory = service.getAllInventory();
        Order orderToConfirm = view.editOrderInfo(order, allTaxes, allInventory);
        Order unconfirmedOrder = service.getUnconfirmedOrder(order);
        if (view.userConfirms(unconfirmedOrder)){
           Order confirmedOrder = service.editOrder(order);
           view.displaySuccessfullEdit(confirmedOrder);
        
    }
    }
    private void removeAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException{
        view.displayMessage("***********Add necessary information to remove order*************");
        LocalDate orderDate = view.getUserDate();
        int orderID = view.getOrderID();
        Order order = service.getAnOrder(orderDate, orderID);
        view.displayOrder(order);
        if(view.confirmDelete(order)){
            service.removeOrder(order);
            view.displayMessage("***********Order is successfully removed*************");
    
        }
       }
    private void exportAllData() throws FlooringMasteryPersistenceException{
        view.displayMessage("\"***********Orders are to be exported to a seperate file*************");
        service.exportAllData();
        view.displayMessage("***********Order is successfully exported*************");
    }
    private void Quit(){
        view.displayMessage("Good Bye!!!");
    }
    private void unknownCommand() {
        view.displayMessage("Unknown Command!!!");
    }

    private void displayErrorMessage(String errorMsg) {
        view.displayMessage("=== ERROR ===" + "\n" + errorMsg);
        }
    private void exitMessege(){
        view.displayexitBanner();
    }

    
}
