/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgjk.flooringmastery;

import com.sgjk.flooringmastery.controller.Controller;
import com.sgjk.flooringmastery.inventory.dao.FlooringMasteryInventoryDao;
import com.sgjk.flooringmastery.inventory.dao.FlooringMasteryInventoryDaoImpl;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryAuditDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryAuditDaoImpl;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryOrderDao;
import com.sgjk.flooringmastery.order.dao.FlooringMasteryOrderDaoImpl;
import com.sgjk.flooringmastery.servicelayer.FlooringMasteryServiceLayer;
import com.sgjk.flooringmastery.servicelayer.FlooringMasteryServiceLayerImpl;
import com.sgjk.flooringmastery.tax.dao.FlooringMasteryTaxDao;
import com.sgjk.flooringmastery.tax.dao.FlooringMasteryTaxDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kaurj
 */
public class App {
    
    public static void main(String[] args) {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = appContext.getBean("controller", Controller.class);
        controller.run();
    }
    
}
