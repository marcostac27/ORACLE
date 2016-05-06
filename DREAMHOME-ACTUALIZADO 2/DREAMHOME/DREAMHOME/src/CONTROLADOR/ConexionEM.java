/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cetecom
 */
public class ConexionEM {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public static EntityManager getConexion(){
        if(em==null){
            emf = Persistence.createEntityManagerFactory("DREAMHOMEPU");
            em = emf.createEntityManager( );
        }
        
        return em;
     
    }
    
}
