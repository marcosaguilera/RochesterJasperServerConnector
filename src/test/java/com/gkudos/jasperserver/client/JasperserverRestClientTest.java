package com.gkudos.jasperserver.client;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;

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
        Init init =  new Init();
        
       /*Funcion para crear un nuevo Directorio y nombrarlo con la fecha y hora actual
        **ej. LIBRO_20150523_120351 == Carpeta creada el 2015-05-23 a las 12:03:51
        ***/
        @BeforeTest
        public void FileIO(String sufix){
            
            Calendar date   = new GregorianCalendar();
            int day         = date.get(Calendar.DAY_OF_MONTH);
            int month       = date.get(Calendar.MONTH)+1;
            int year        = date.get(Calendar.YEAR);
            int hour        = date.get(Calendar.HOUR_OF_DAY);
            int mins        = date.get(Calendar.MINUTE);
            int secs        = date.get(Calendar.SECOND);
            String dirName  = sufix+year+month+day+"_"+hour+mins+secs;
        
            outPutDir = new File("/Users/rochester/Desktop/"+dirName);
            if (!outPutDir.exists()) {
                    if (outPutDir.mkdir()) {
                            System.out.println("Directorio creado en: "+outPutDir.getAbsolutePath());
                    } else {
                            System.out.println("Error creando el directorio!");
                    }
            }
        }
	
	/*@BeforeTest
	public void beforeTest() {
		//LOGGER.debug("beforeTest...");
		outPutDir = new File(System.getProperty("java.io.tmpdir"));
                //LOGGER.debug("--> File location: "+outPutDir);
		assertNotNull(outPutDir);
	}*/

	@AfterTest
	public void afterTest() {
		//LOGGER.debug("afterTest...");
	}
	
        /*Función para generar un reporte por Año escolar y estudiante PDF*/
        public void getFinalBookPdf(String code, Integer school_year, String fname){
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
                        client.getFileName(fname);
			File reportFile  = client.getReportAsFile(report);
			assertNotNull(reportFile);
			//LOGGER.debug("reportFile: "+reportFile.getAbsolutePath());
                        System.out.println("reportFile: "+reportFile.getAbsolutePath());
		} catch (Exception e) {
                        try {
                            init.setLogReports(code, school_year, fname);
                        } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(JasperserverRestClientTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("error in: "+code);
			fail(e.getMessage(), e);
                        
		}
        }
        
        /*Función para generar un reporte por Año escolar y estudiante DOCX*/
        public void getFinalGradeCert(String code, Integer school_year, String fname){
                //LOGGER.debug("testGetReportAsFile");
		try {
			Report report = new Report();
                        report.setFormat(Report.FORMAT_PDF);
                        report.setUrl("/aprendozreports/SEC014GDRIVE");
			report.setOutputFolder(outPutDir.getAbsolutePath());
                        report.addParameter("codigo", code);
                        report.addParameter("string_sy", school_year.toString());
                        
			//LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
                        client.getFileName(fname);
			File reportFile  = client.getReportAsFile(report);
			assertNotNull(reportFile);
			//LOGGER.debug("reportFile: "+reportFile.getAbsolutePath());
                        System.out.println("reportFile: "+reportFile.getAbsolutePath());
		} catch (Exception e) {
                    try {
                        init.setLogReports(code, school_year, fname);
                    } catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(JasperserverRestClientTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        System.out.println("error in: "+code);
			fail(e.getMessage(), e);
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
