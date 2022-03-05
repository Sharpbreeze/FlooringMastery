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
import com.sgjk.flooringmastery.ui.FlooringMasteryView;
import com.sgjk.flooringmastery.ui.UserIO;
import com.sgjk.flooringmastery.ui.UserIOConsoleImpl;
import java.io.IOException;

/**
 *
 * @author kaurj
 */
public class App {
    
    public static void main(String[] args) throws IOException{
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        FlooringMasteryOrderDao myOrderDao = new FlooringMasteryOrderDaoImpl();
        FlooringMasteryInventoryDao myInventoryDao = new FlooringMasteryInventoryDaoImpl();
        FlooringMasteryTaxDao myTaxDao = new FlooringMasteryTaxDaoImpl();
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoImpl();
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myOrderDao, myAuditDao, myTaxDao, myInventoryDao );
        Controller controller = new Controller(myView, myService);
        controller.run();
    }
    
}
