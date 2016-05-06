/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Propiedad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class PropiedadDB {
    
    private Connection cnx;

    public PropiedadDB(Connection cnx) {
        this.cnx = cnx;
    }
    
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
    
    
        public Propiedad obtenerPropiedad (Propiedad pr)
        {
        String sql = "SELECT * PROPIEDAD where NUMPROPIEDAD = ?";
        Propiedad prop = null;
        try{
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setString(1, pr.getNumpropiedad());
            ResultSet resultado = stm.executeQuery();
            if(resultado.next()){
                prop = new Propiedad(resultado.getString(1), resultado.getString(2));
            }
            return prop;
        }catch(SQLException ex){
            throw new RuntimeException("Error al recuperar la propiedad",ex);
        }
    }
    
        public int eliminarPropiedad(Propiedad pro){
        String sql = "DELETE FROM PROPIEDAD WHERE NUMPROPIEDAD = ?";
        int respuesta = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setString(1, pro.getNumpropiedad());
            respuesta = stm.executeUpdate();
            return respuesta;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al recuperar la propiedad", ex);
        }
    }
    }

