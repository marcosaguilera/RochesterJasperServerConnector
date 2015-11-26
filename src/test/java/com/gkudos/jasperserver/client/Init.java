/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkudos.jasperserver.client;

import com.gkudos.jasperserver.client.JasperserverRestClientTest;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rochester
 */
public class Init {
    
    public List<String> codigosEstudiantesSy(Integer sy){
        System.out.println(sy);
        Array data = null;
        
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        List<String> CodeDataList = null;
        
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://yoda/aprendoz_desarrollo";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "irc4Quag");

            st = conn.prepareStatement("SELECT Curso.Curso as curso, SY.Id_SY as idsy, SY.School_year as sy, PERSONA.Codigo as codigo FROM SY INNER JOIN Insc_Alum_Curso ON SY.Id_SY = Insc_Alum_Curso.SY_Id_SY INNER JOIN PERSONA ON PERSONA.Id_Persona = Insc_Alum_Curso.Persona_Id_Persona INNER JOIN Curso ON Curso.Id_Curso = Insc_Alum_Curso.Curso_Id_Curso WHERE SY.Id_SY = ? AND curso.Id_Curso >= 10101 AND curso.Id_Curso <= 40403 ORDER BY Curso.Id_Curso, PERSONA.Apellido1, PERSONA.Apellido2, PERSONA.Nombre1, PERSONA.Nombre2");
            st.setInt(1, sy);
            rs = st.executeQuery();
            
            ArrayList<String> list = new ArrayList<String>();
            CodeDataList = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString("codigo"));
                CodeDataList.add(rs.getString("codigo"));
            }
            
            System.out.println("-> size "+CodeDataList.size());
            for( int i=0; i<CodeDataList.size(); i++){
                System.out.print(CodeDataList);              
            }
            st.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }finally{
            
            try{
              if(rs != null){
                 rs.close(); 
              }      
              if(st != null){
                 st.close();
              }
              if(conn != null){
                 conn.close(); 
              }

            }catch(SQLException se){   
            }
        }
        return CodeDataList;
    }
    
    public void setLogReports(String code, Integer school_year, String fname) throws SQLException{
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://yoda/aprendoz_desarrollo";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "irc4Quag");
            
            //insert into `aprendoz_desarrollo`.`reporte_log_generacion` ( `codigo`, `sy`, `nombre_reporte`, `origen_generacion`, `id_rep_generator_log`) values ( '05045', '4', '52_05045_00000', 'java_netbeans', '0')
            
            String insertTableSQL = "INSERT INTO aprendoz_desarrollo.reporte_log_generacion"
                                  + "(codigo, sy, nombre_reporte, origen_generacion) VALUES"
				  + "(?,?,?,?)";
            
            st = conn.prepareStatement(insertTableSQL);

            st.setString(1, code);
            st.setInt(2, school_year);
            st.setString(3, fname);
            st.setString(4, "java_netbeans");

            // execute insert SQL stetement
            st.executeUpdate();

            System.out.println("Record is inserted into DBUSER table!");
            
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }finally{
                try{
                if(rs != null){
                    rs.close(); 
                }      
                if(st != null){
                    st.close();
                }
                if(conn != null){
                    conn.close(); 
                }

            }catch(SQLException se){   
            }
        }
    }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Init init = new Init();
        List<String> codes = init.codigosEstudiantesSy(4);
        Integer size = codes.size();
        System.out.println("---> "+size);
        Integer tipo = 1;
        Integer tipo2 = 2;
        
        JasperserverRestClientTest js = new JasperserverRestClientTest();
        
        String codeId;
        String fileName;
        
        int value1 = 1;
        int value2 = 2;
        
        if(value1 == tipo ){
           js.FileIO("LIBRO_FINAL_");
           for(int i=0; i<size; i++ ){
                codeId = codes.get(i);
                System.out.println("i "+i +"  ===> CODIGO: "+ codes.get(i));
                fileName = i+"_"+codeId+"_";
                js.getFinalBookPdf( codeId, 4, fileName);
                System.out.println(" ====> "+ codeId +" ===> Report completed!");
           }
        }
        
        if(value2 == tipo2){
            js.FileIO("CERTIFICADO_FINAL_");
            for(int i=0; i<size; i++ ){
                codeId = codes.get(i);
                System.out.println("i "+i +"  ===> CODIGO: "+ codes.get(i));
                fileName = i+"_"+codeId+"_";
                js.getFinalGradeCert( codeId, 4, fileName);
                System.out.println(" ====> "+ codeId +" ===> Report completed!");
            }
        }
    }
}
