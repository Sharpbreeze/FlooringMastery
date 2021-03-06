/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery.order.dao;

import com.sgjk.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author kaurj
 */
public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    List<Order> exportDataMap = new ArrayList<>();
    private Map<Integer, Order> orderMap = new HashMap<>();
    private String COMMA = ",";
    private String ORDER_FILE_PATH;
    private String EXPORT_DATA_FILE_PATH = "ExportAllData.txt";
    

    public FlooringMasteryOrderDaoImpl() {
        this.ORDER_FILE_PATH = "Orders/";// do I label all the order files seperately or build a code line that can sort it out
    }

    public FlooringMasteryOrderDaoImpl(String filePath) {
        this.ORDER_FILE_PATH = filePath;

    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {

        loadOrder(order.getDueDate());

        orderMap.put(order.getOrderID(), order);
        writeOrder(order.getDueDate());
        return order;
    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryPersistenceException {

        loadOrder(order.getDueDate());

        orderMap.put(order.getOrderID(), order);

        writeOrder(order.getDueDate());

        return order;
    }

    @Override
    public Order removeOrder(Order order) throws FlooringMasteryPersistenceException {

        loadOrder(order.getDueDate());

        Order removedOrder = orderMap.remove(order.getOrderID());

        writeOrder(order.getDueDate());

        return removedOrder;
    }

    @Override
    public Order getAnOrder(LocalDate date, int orderID) throws FlooringMasteryPersistenceException {

        loadOrder(date);

        Order getOrder = orderMap.get(orderID);
        return getOrder;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException {

        loadOrder(date);

        return new ArrayList(orderMap.values());

    }

    @Override
    public void exportAllData() throws FlooringMasteryPersistenceException {

        File ordersFolder = new File(ORDER_FILE_PATH);
        String[] files = ordersFolder.list();
        Scanner reader;
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(EXPORT_DATA_FILE_PATH));
        } catch (IOException ex) {
            throw new FlooringMasteryPersistenceException(
                    "Unable To Save Data Exprt" + ex);
        }
        writer.println("OrderDate" + COMMA + "OrderNumber" + COMMA + "CustomerName" + COMMA + "State" + COMMA + "TaxRate" + COMMA + "ProductType"
                + COMMA + "Area" + COMMA + "CostPerSquareFoot" + COMMA + "LaboutCostPerSquareFoot" + COMMA + "MaterialCost" + COMMA
                + "LabourCost" + COMMA + "Tax" + COMMA + "Total");

        for (String fileName : files) {

            try {
                reader = new Scanner(new BufferedReader(new FileReader(ORDER_FILE_PATH + fileName)));// skip the header row, order file
            } catch (IOException e) {
                throw new FlooringMasteryPersistenceException("Could not save data ", e);
            }
            String dateString = fileName.substring(7, 15);
            String currentLine;
            reader.nextLine();
            while (reader.hasNextLine()) {
                currentLine = reader.nextLine();
                currentLine = dateString + COMMA + currentLine;
                writer.println(currentLine);
                writer.flush();
            }
        }

        writer.close();
    }

    private Order unmarshallOrder(String orderItem) {
        int normalCommaCount = 11;
        String[] token = orderItem.split(COMMA);
        int actualCommaCount = token.length - 1;
        int index = 0;
        Order OrderFromFile = new Order();
        OrderFromFile.setOrderID(Integer.parseInt(token[index++]));
        String customerName = "";
        for (int i = 0; i <= actualCommaCount - normalCommaCount; i++) {
            if (i != actualCommaCount - normalCommaCount) {
                customerName += token[index++] + COMMA;
            } else {
                customerName += token[index++];
            }

        }
        OrderFromFile.setCustomerName(customerName);
        OrderFromFile.setStateCode(token[index++]);
        OrderFromFile.setTaxRate(new BigDecimal(token[index++]));
        OrderFromFile.setProductType(token[index++]);
        OrderFromFile.setArea(new BigDecimal(token[index++]));
        OrderFromFile.setCostPerSqFt(new BigDecimal(token[index++]));
        OrderFromFile.setLabourCostPerSqFt(new BigDecimal(token[index++]));
        OrderFromFile.setMaterialCost(new BigDecimal(token[index++]));
        OrderFromFile.setLabourCost(new BigDecimal(token[index++]));
        OrderFromFile.setTaxAmount(new BigDecimal(token[index++]));
        OrderFromFile.setGrandTotal(new BigDecimal(token[index++]));

        return OrderFromFile;
    }

    private void loadOrder(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner reader;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(formatter);
        String filePath = ORDER_FILE_PATH + "Orders_" + dateString + ".txt";
        try {

            reader = new Scanner(new BufferedReader(new FileReader(filePath)));// skip the header row, order file
            String currentLine;
            Order currentOrder;
            orderMap.clear();
            reader.nextLine();
            while (reader.hasNextLine()) {
                currentLine = reader.nextLine();
                currentOrder = unmarshallOrder(currentLine);
                currentOrder.setDueDate(date);
                orderMap.put(currentOrder.getOrderID(), currentOrder);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            orderMap.clear();

        }

    }

    private String marshallOrder(Order anItem) {
        String itemAsText = "";
        itemAsText += anItem.getOrderID() + COMMA;
        itemAsText += anItem.getCustomerName() + COMMA;
        itemAsText += anItem.getStateCode() + COMMA;
        itemAsText += anItem.getTaxRate() + COMMA;
        itemAsText += anItem.getProductType() + COMMA;
        itemAsText += anItem.getArea() + COMMA;
        itemAsText += anItem.getCostPerSqFt() + COMMA;
        itemAsText += anItem.getLabourCostPerSqFt() + COMMA;
        itemAsText += anItem.getMaterialCost() + COMMA;
        itemAsText += anItem.getLabourCost() + COMMA;
        itemAsText += anItem.getTaxAmount() + COMMA;
        itemAsText += anItem.getGrandTotal();

        return itemAsText;
    }

    private void writeOrder(LocalDate date) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(formatter);
        String filePath = ORDER_FILE_PATH + "Orders_" + dateString + ".txt";

        try {
            out = new PrintWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save data ", e);
        }
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        out.flush();
        orderMap.values().stream()
                .map(item -> marshallOrder(item))
                .forEachOrdered((lineItem) -> {
                    out.println(lineItem);
                    out.flush();
                });
        out.close();
    }

}
