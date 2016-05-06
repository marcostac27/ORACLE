/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dreamhome;

import CONTROLADOR.ConexionEM;
import MODELO.Empleado;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;

/**
 *
 * @author mariocosta
 */
public class DREAMHOME {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        EntityManager emC = ConexionEM.getConexion();
        Empleado empleado = new Empleado();
        
        empleado.setNumempleado("123");
        empleado.setNombre("Pedro");
        empleado.setApellido("Perez");
        
        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fecha = null;
        java.sql.Date fecha2 = null;
        
        fecha = ft.parse("1999-10-22");
        fecha2 = new java.sql.Date(fecha.getTime());
        empleado.setFechnac(fecha2);
        
        empleado.setCargo("Gerente");
        empleado.setSexo("M");
        empleado.setSalario(new BigInteger("5000"));
        empleado.setNumoficina(new BigInteger("5"));
        
        emC.getTransaction().begin();
        emC.persist(empleado);
        emC.getTransaction().commit();
        
       
    }
    
}
