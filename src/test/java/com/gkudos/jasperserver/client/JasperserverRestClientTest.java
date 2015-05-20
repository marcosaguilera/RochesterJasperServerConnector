package com.gkudos.jasperserver.client;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * @author juanmendez@gkudos.com
 * 
 */

public class JasperserverRestClientTest {
	public static final Logger LOGGER = LoggerFactory.getLogger(JasperserverRestClientTest.class);
	
	/*private final static String serverUrl = "http://myserver/jasperserver/";
	private final static String serverUser = "joeuser";
	private final static String serverPassword = "joeuser";*/
        
        private final static String serverUrl = "http://leon:8090/jasperserver/";
	private final static String serverUser = "jasperadmin";
	private final static String serverPassword = "jasperadmin";
	
	private File outPutDir;
	
	@BeforeTest
	public void beforeTest() {
		//LOGGER.debug("beforeTest...");
		outPutDir = new File(System.getProperty("java.io.tmpdir"));
                //LOGGER.debug("--> File location: "+outPutDir);
		assertNotNull(outPutDir);
	}

	@AfterTest
	public void afterTest() {
		//LOGGER.debug("afterTest...");
	}
	
        /*Función para generar un reporte por Año escolar*/
        
        public void getFinalBook(String code, Integer school_year){
                //LOGGER.debug("testGetReportAsFile");
		try {
			Report report = new Report();
                        report.setFormat(Report.FORMAT_PDF);
                        report.setUrl("/aprendozreports/SEC013GDRIVE");
			report.setOutputFolder(outPutDir.getAbsolutePath());
                        report.addParameter("codigo", code);
                        report.addParameter("sy_string", school_year.toString());
			//LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile  = client.getReportAsFile(report);
			assertNotNull(reportFile);
			//LOGGER.debug("reportFile: "+reportFile.getAbsolutePath());
                        System.out.println("reportFile: "+reportFile.getAbsolutePath());
		} catch (Exception e) {
			fail(e.getMessage(), e);
                        System.out.println("error in: "+code);
		}
        }
        
        /**
	 * Ejecuta el reporte y retorna un archivo
	 **/
	/*@Test
	public void testGetReportAsFile() {
		LOGGER.debug("testGetReportAsFile");
		try {
			Report report = new Report();
			//report.setUrl("/reports/samples/Employees");
                        report.setUrl("/aprendozreports/EXT001");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile  = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}*/
        
       
	/*@Test
	public void testGetBigReportAsFile() {
		LOGGER.debug("testGetBigReportAsFile");
		try {
			Report report = new Report();
			report.setUrl("/reports/samples/SalesByMonth");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}*/
	
	/*@Test
	public void testGetBigReportAsExcelFile() {
		LOGGER.debug("testGetBigReportAsExcelFile");
		try {
			Report report = new Report();
			report.setFormat(Report.FORMAT_EXCEL);
			//report.setUrl("/reports/samples/SalesByMonth");
                        report.setUrl("/aprendozreports/EXT001");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}*/
	
	/*@Test
	public void testGetReportWithParamsAsFile() {
		LOGGER.debug("testGetReportWithParamsAsFile");
		try {
			Report report = new Report();
			report.setUrl("/reports/samples/Department");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			report.addParameter("department", "11");
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}*/

}
