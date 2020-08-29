/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.bitlab.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import sv.edu.bitlab.managedbeans.FotoManaged;


@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Inject
    FotoManaged fotoManaged;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = fotoManaged.getFoto();
        String filename = fotoManaged.getNombreFoto();
        File file = new File(path);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
