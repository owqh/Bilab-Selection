/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Mario
 */
@Named(value = "testMB")
@SessionScoped
public class TestMB implements Serializable {

    /**
     * Creates a new instance of TestMB
     */
    
//    private final String ROL = "Admin";
    private final String ROL = "Admin";
    
    private final String nUsuario = "Nombre Prueba"; 

    
    public TestMB() {
    }
    public String getROL() {
        return ROL;
    }
    
    public String getnUsuario() {
        return nUsuario;
    }
 
    public void validarSesion(){
        
    }
    
    public void validarRol(){
        
    }
}
