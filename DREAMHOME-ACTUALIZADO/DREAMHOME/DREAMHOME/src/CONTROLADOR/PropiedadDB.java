/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Propiedad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class PropiedadDB {
    
    public ArrayList<Propiedad> ListPropiedad(){
        ArrayList<Propiedad> propiedad = new ArrayList();
        
        try {
            Connection cnx = (Connection) ConexionEM.getConexion();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("  SELECT NUMPROPIEDAD,CALLE,CIUDAD,CODIGOPOSTAL,TIPO,HAB,RENTA,NUMPROPIETARIO,NUMEMPLEADO"
                    + "                       FROM PROPIEDAD"
                    + "                       ORDER BY NUMPROPIEDAD");
            
            while(rs.next()){
               /*Propiedad prd = new Propiedad();
                prd.setNumpropiedad(rs.getString("NUMPROPIEDAD"));
                prd.setCalle(rs.getString("CALLE"));
                prd.setCiudad(rs.getString("CIUDAD"));
                prd.setCodigopostal(rs.getString("CODIGOPOSTAL"));
                prd.setTipo(rs.getString("TIPO"));
                prd.setHab(r s.getInt("HAB"));
                prd.setRenta(rs.getInt("RENTA"));
                prd.setNumpropietario(rs.getString("NUMPROPIETARIO"));
                prd.setNumempleado(rs.getString("NUMEMPLEADO"));
                propiedad.add(prd);
                */
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            System.out.println("Error en Listado");
        }
        
       return  propiedad;
    }
    
    //Insertar Datos
    
    public void InsertarPropiedad(Propiedad propiedad){
    
         /*try {
            Connection cnx = Conexion.getConnection();
            PreparedStatement pst = cnx.prepareStatement(" INSERT INTO PROPIEDAD(NUMPROPIEDAD,CALLE,CIUDAD,CODIGOPOSTAL,TIPO,HAB,RENTA,NUMPROPIETARIO,NUMEMPLEADO)"
                    + "                                    VALUES(?,?,?,?,?,?,?,?,?)");
           pst.setString(1,propiedad.getNumpropiedad());
            pst.setString(2,propiedad.getCalle());
            pst.setString(3,propiedad.getCiudad());
            pst.setString(4,propiedad.getCodigopostal());
            pst.setString(5,propiedad.getTipo());
            pst.setInt(6,propiedad.getHab());
            pst.setInt(7,propiedad.getRenta());
            pst.setString(8,propiedad.getNumpropietario());
            pst.setString(9,propiedad.getNumempleado());
            pst.executeUpdate();          
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Insert");
            */      
           
        }
    }

