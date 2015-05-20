/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkudos.jasperserver.client;

import com.gkudos.jasperserver.client.JasperserverRestClientTest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            st = conn.prepareStatement("SELECT Curso.Curso as curso, SY.Id_SY as idsy, SY.School_year as sy, PERSONA.Codigo as codigo FROM SY INNER JOIN Insc_Alum_Curso ON SY.Id_SY = Insc_Alum_Curso.SY_Id_SY INNER JOIN PERSONA ON PERSONA.Id_Persona = Insc_Alum_Curso.Persona_Id_Persona INNER JOIN Curso ON Curso.Id_Curso = Insc_Alum_Curso.Curso_Id_Curso WHERE SY.Id_SY = ? AND curso.Id_Curso >= 10101 AND curso.Id_Curso <= 40403");
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
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Init init = new Init();
        List<String> codes = init.codigosEstudiantesSy(4);
        Integer size = codes.size();
        System.out.println("---> "+size);
        
        JasperserverRestClientTest js = new JasperserverRestClientTest();
        js.beforeTest();
        
        /*for(String codeId : codes ){
            js.getFinalBook( codeId, 4 );
            System.out.println(" ====> "+ codeId +" ===> Report completed!");
        }*/
        
        String codeId;
        for(int i=0; i<=10; i++ ){
           codeId = codes.get(i);
           System.out.println("i "+i +"  ===> CODIGO: "+ codes.get(i));
           js.getFinalBook( codeId, 4 );
           System.out.println(" ====> "+ codeId +" ===> Report completed!");
        }
        //js.testGetReportAsFile();
        //js.testGetBigReportAsExcelFile();
        //System.out.println("--> initialized java");
    }
}
