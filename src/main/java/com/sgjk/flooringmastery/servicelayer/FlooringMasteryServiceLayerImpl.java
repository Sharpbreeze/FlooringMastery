/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.servicelayer;

import com.sgjk.flooringmastery.dto.Inventory;
import com.sgjk.flooringmastery.dto.Order;
import com.sgjk.flooringmastery.dto.Tax;
import com.sgjk.flooringmastery.inventory.dao.FlooringMasteryInventoryDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryAuditDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryOrderDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryPersistenceException;
import com.sgjk.flooringmastery.tax.dao.FlooringMasteryTaxDao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao dao;
    FlooringMasteryAuditDao auditDao;
    FlooringMasteryTaxDao taxDao;
    FlooringMasteryInventoryDao inventoryDao;
    private int orderIndex = 0;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao dao, FlooringMasteryAuditDao auditDao, FlooringMasteryTaxDao taxDao, FlooringMasteryInventoryDao inventoryDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        this.taxDao = taxDao;
        this.inventoryDao = inventoryDao;
    }

    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMDDYYYY");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM-DD-YYYY");

    @Override
    public Order getUnconfirmedOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {

        validateOrder(order);
        Inventory product = inventoryDao.getProductType(order.getProductType());
        Tax state = taxDao.getTax(order.getStateCode());
        order.setTaxRate(state.getTaxRate());
        order.setStateCode(state.getStateCode());
        order.setProductType(product.getProductType());
        order.setLabourCostPerSqFt(product.getLabourCostPerSqFt());
        order.setCostPerSqFt(product.getCostPerSqFt());
        BigDecimal cost = order.getCostPerSqFt().multiply(order.getArea());
        BigDecimal labourCost = order.getLabourCostPerSqFt().multiply(order.getArea());

        order.setLabourCost(labourCost);
        order.setMaterialCost(cost);

        BigDecimal stateTax = (cost.add(labourCost)).multiply(order.getTaxRate());
        order.setTaxAmount(stateTax);
        BigDecimal grandTotal = (cost).add(labourCost).add(stateTax);
        order.setGrandTotal(grandTotal);
        return order;

    }

    public Order addOrder(Order confirmedOrder) throws FlooringMasteryPersistenceException {
        LocalDate orderDate = confirmedOrder.getDueDate();
        List<Order> allOrders = dao.getAllOrders(orderDate);
        int defaultOrderID = 0;
        for (Order order : allOrders) {
            if (order.getOrderID() > defaultOrderID) {
                defaultOrderID = order.getOrderID();
            }
        }
        defaultOrderID++;
        confirmedOrder.setOrderID(defaultOrderID);
        Order completedOrder = dao.addOrder(confirmedOrder);
        auditDao.writeAuditEntry("Order" + confirmedOrder.getOrderID() + " has been placed!");
        return completedOrder;
    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {
        Order editedOrder = dao.editOrder(order);
        auditDao.writeAuditEntry("Order" + order.getOrderID() + " has been edited!");
        return editedOrder;
    }

    @Override
    public Order removeOrder(Order order) throws FlooringMasteryPersistenceException {

        Order removedOrder = dao.removeOrder(order);
        auditDao.writeAuditEntry("Order" + order + "Removed");
        return removedOrder;
    }

    @Override
    public Order getAnOrder(LocalDate date, int orderID) throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException {
        Order getOrder = dao.getAnOrder(date, orderID);
        if(getOrder == null) {
            throw new FlooringMasteryOrderNotFoundException("Order " + orderID + " Does not exist for " + date);
        }
        return getOrder;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return dao.getAllOrders(date);
    }

//    @Override
//    public List<Order> getOrdersByDate(LocalDate Date) throws FlooringMasteryPersistenceException {
//        return dao.getOrdersByDate(Date);
//    }
    @Override
    public void exportAllData() throws FlooringMasteryPersistenceException {
        dao.exportAllData();
    }
    public Inventory getProductType(String productName) throws FlooringMasteryPersistenceException {
        return inventoryDao.getProductType(productName);
    }

    public BigDecimal getMaterialCost(Inventory inventory, Order order) throws FlooringMasteryPersistenceException {
        BigDecimal materialCost = inventory.getCostPerSqFt().multiply(order.getArea());
        return materialCost;
    }

    public BigDecimal getLabourCost(Inventory inventory, Order order) throws FlooringMasteryPersistenceException {
        BigDecimal labourCost = inventory.getLabourCostPerSqFt().multiply(order.getArea());
        return labourCost;
    }

    public Tax getStateName(String stateName) throws FlooringMasteryPersistenceException {
        return taxDao.getTax(stateName);
    }

    public void orderSummary(Order order) throws FlooringMasteryPersistenceException {
        System.out.println(order.getOrderID()
                + order.getCustomerName()
                + order.getDueDate()
                + order.getArea()
                + order.getProductType()
                + order.getStateCode());

    }

    public BigDecimal grandTotal(Order order) throws FlooringMasteryPersistenceException {
        BigDecimal cost = order.getCostPerSqFt().multiply(order.getArea());
        BigDecimal labourCost = order.getLabourCostPerSqFt().multiply(order.getArea());
        //BigDecimal totaCost = (cost).add(labourCost);
        BigDecimal stateTax = ((cost).add(labourCost)).multiply(order.getTaxRate());
        BigDecimal grandTotal = (cost).add(labourCost).add(stateTax);

        return grandTotal;
    }

    public void validateOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {
        String cusName = order.getCustomerName();
        BigDecimal orderArea = order.getArea();
        BigDecimal area = new BigDecimal("100");
        String productType = order.getProductType().toLowerCase();
        productType = productType.substring(0, 1).toUpperCase() + productType.substring(1, productType.length());
        String stateCode = order.getStateCode();
        LocalDate dueDate = order.getDueDate();
        LocalDate prevDate = LocalDate.now();

        if (cusName == null || cusName.trim().length() == 0) {
            throw new FlooringMasteryDataValidationException("Please enter valid name without using special characters ");
        }

        if (orderArea.compareTo(area) < 0) {
            throw new FlooringMasteryDataValidationException("Please enter more than 100sq.ft. of an area");
        }

        if (inventoryDao.getProductType(productType) == null) {
            throw new FlooringMasteryDataValidationException("Please enter a valid product");
        }
        if (taxDao.getTax(stateCode.toUpperCase()) == null) {
            throw new FlooringMasteryDataValidationException("Please enter a valid State");
        }

        if (!dueDate.isAfter(prevDate)) {
            throw new FlooringMasteryDataValidationException("Please enter a valid future Date");
        }

    }
}