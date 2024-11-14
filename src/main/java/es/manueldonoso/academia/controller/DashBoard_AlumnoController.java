/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Session;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class DashBoard_AlumnoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      

        if (Session.getPass() != null) {
            Base_datos.CambioContraseña(Base_datos.conectarSqlite(), Session.getUsuario(), Session.getPass());
            Session.setPass(null);
        }

    }

    Connection conn;

    public void SetConn(Connection conn) {
        this.conn = conn;
    }
}
