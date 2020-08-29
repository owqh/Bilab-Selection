/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.managedbeans;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import sv.edu.bitlab.beans.CandidatoFacade;
import sv.edu.bitlab.entidades.Candidato;
import sv.edu.bitlab.utilidades.Utilidades;

/**
 *
 * @author Mario
 */
@Named(value = "fotoManaged")
@RequestScoped
public class FotoManaged {

    private static final Logger LOG = Logger.getLogger(FotoManaged.class.getName());

    @EJB
    private CandidatoFacade candidatoFacade;

    @Inject
    UsuarioManaged usuarioManaged;

    private Candidato perfilUsuario;
    
    private String foto;
    private String nombreFoto;

    //Ficheros
    private UploadedFile nuevaFoto;

    public FotoManaged() {
    }

    @PostConstruct
    public void inicializar() {
        try {
            perfilUsuario = candidatoFacade.candidatoPorCorreo(usuarioManaged.getUsr().getUsrAcceso());
            
            foto = perfilUsuario.getCanFoto();
            nombreFoto = FilenameUtils.getName(foto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "No se encontro al candidato, error: {0}", ex);
        }
    }

    public void updatePicture(FileUploadEvent event) {
        try {
            UploadedFile fotoSubida = event.getFile();

            InputStream input = fotoSubida.getInputStream();
            Path folder = Paths.get("C:\\Users\\Mario\\Documents\\FotosPerfil");
            String filename = FilenameUtils.getBaseName(fotoSubida.getFileName());
            String extension = FilenameUtils.getExtension(fotoSubida.getFileName());
            Path file = Files.createTempFile(folder, filename + "-", "." + extension);

            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);

            String fullPath = file.toString();
            perfilUsuario.setCanFoto(fullPath);
            candidatoFacade.edit(perfilUsuario);

            Utilidades.lanzarInfo("Exitoso ", "La foto de perfil se ha actualizado correctamente");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "No se pudo guardar la imagen, error: {0}", ex);
        }

    }

    public String getNombreFoto() {
        return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public UploadedFile getNuevaFoto() {
        return nuevaFoto;
    }

    public void setNuevaFoto(UploadedFile nuevaFoto) {
        this.nuevaFoto = nuevaFoto;
    }
}
