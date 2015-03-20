/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkudos.jasperserver.client;

import com.gkudos.jasperserver.client.JasperserverRestClientTest;

/**
 *
 * @author rochester
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JasperserverRestClientTest js = new JasperserverRestClientTest();
        js.beforeTest();
        js.testGetReportAsFile();
        js.testGetBigReportAsExcelFile();
        System.out.println("--> initialized java");
    }
}
