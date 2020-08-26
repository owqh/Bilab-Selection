/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import entityTest.EntityTest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Mario
 */
@Named(value = "tableTestMB")
@ViewScoped
public class TableTestMB implements Serializable {

    /**
     * Creates a new instance of TableTestMB
     */
    List<EntityTest> candidatos;
    List<EntityTest> canFiltro;
    EntityTest entidad;
    
    public TableTestMB() {
    }

    @PostConstruct
    public void init() {
        entidad = new EntityTest();
        candidatos = Arrays.asList(
                new EntityTest(1, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(2, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(3, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(4, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(5, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(6, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(7, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(8, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(9, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(10, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(11, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(12, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(13, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(14, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(15, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(16, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(17, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(18, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(19, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba"),
                new EntityTest(20, "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba", "Prueba")
        );
    }

    public List<EntityTest> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<EntityTest> candidatos) {
        this.candidatos = candidatos;
    }

    public List<EntityTest> getCanFiltro() {
        return canFiltro;
    }

    public void setCanFiltro(List<EntityTest> canFiltro) {
        this.canFiltro = canFiltro;
    }

    public EntityTest getEntidad() {
        return entidad;
    }

    public void setEntidad(EntityTest entidad) {
        this.entidad = entidad;
    }
    
    public void actualizarUs(){
//        int num = entidad.getId();
//        candidatos.remove(num);
//        candidatos.add(num, entidad);
    }
}
