/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Mario
 */
@Named(value = "indexManaged")
@SessionScoped
public class IndexManaged implements Serializable {

    /**
     * Creates a new instance of IndexManaged
     */
    @Inject
    candidatoManaged candidatomanaged;
    
    @Inject
    UsuariosMG usuariosMG;
    
    @Inject
    DocenteManaged docenteManaged;

    private PolarAreaChartModel polarAreaModel;
    private DonutChartModel donutModel;

    public IndexManaged() {
    }

    @PostConstruct
    public void init() {
        createPolarAreaModel();
        createDonutModel();
    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(candidatomanaged.getAplicanteGeneral().size());
        values.add(candidatomanaged.getCandidatoListPreseleccionado().size());
        values.add(candidatomanaged.getCandidatoSeleccionado().size());
        values.add(candidatomanaged.getAlumnos().size());
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Aplicantes");
        labels.add("Pre-Seleccionados");
        labels.add("Seleccionados");
        labels.add("Alumnos");
        data.setLabels(labels);
        polarAreaModel.setData(data);
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(filterUsrList(1));
        values.add(docenteManaged.getDocenteList().size());
        values.add(filterUsrList(3));
        values.add(filterUsrList(4));
        values.add(filterUsrList(5));
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(147, 112, 219)");
        
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Administrador");
        labels.add("Docente");
        labels.add("Supervisor");
        labels.add("Analista");
        labels.add("Candidato");
        
        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelectUsr(ItemSelectEvent event) {
        if(event.getItemIndex()== 1){
            Utilidades.redireccion("docente");
        } else {
            Utilidades.redireccion("usuario");
        }
    }
    
    public void itemSelectCan(ItemSelectEvent event) {
        String page;
        switch (event.getItemIndex()) {
            case 0:
                page = "aplicante";
                break;
            case 1:
                page = "preseleccion";
                break;
            case 2:
                page = "seleccionado";
                break;
            case 3:
                page = "alumnos";
                break;
            default:
                page = "index";
        }
        Utilidades.redireccion(page);
    }

    private int filterUsrList(int filtro){
        int users = usuariosMG.getUsuarioList().stream().filter((u)->u.getTipId().getTipId() == filtro).collect(Collectors.toList()).size();
        return users;
    }
    
    public PolarAreaChartModel getPolarAreaModel() {
        return polarAreaModel;
    }

    public void setPolarAreaModel(PolarAreaChartModel polarAreaModel) {
        this.polarAreaModel = polarAreaModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }
}
