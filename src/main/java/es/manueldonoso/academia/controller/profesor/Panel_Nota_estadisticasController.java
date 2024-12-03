/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller.profesor;

import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.modelos.examen;
import es.manueldonoso.academia.util.Base_datos;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_Nota_estadisticasController implements Initializable {

    @FXML
    private VBox root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Connection conn;
    private Usuario usuario;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void LoadDatas() {
        // Crear un Accordion
        Accordion accordion = new Accordion();
        List asignaturas = usuario.getAsignaturas();
        // Crear secciones del Accordion (TitledPane)
        //TitledPane Estadisticas = new TitledPane("Estadisticas", new Label("Contenido de la sección 1"));
        // Agregar las secciones al Accordion
        //accordion.getPanes().addAll(Estadisticas);
        List<examen> Examen = null;

        // Agregar el Accordion al VBox
        root.getChildren().add(accordion);

        for (int i = 0; i < asignaturas.size(); i++) {
            String asignatura = (String) asignaturas.get(i);
            List usuarios = null;
            try {
                usuarios = Base_datos.obtenerUsuariosPorAsignaturaSinProfesor(conn, asignatura);
                System.out.println(asignatura);
                System.out.println(usuarios);
            } catch (SQLException ex) {
                Logger.getLogger(Panel_Nota_estadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            TitledPane TPasignatura = new TitledPane();
            TPasignatura.setText(asignatura);
            VBox contenidoUsuario = new VBox();
            TPasignatura.setContent(contenidoUsuario);

            for (int j = 0; j < usuarios.size(); j++) {

                TitledPane usuario = new TitledPane();
                VBox contenidoUsuarioNotas = new VBox();
                String usuarioNota = (String) usuarios.get(j);
                usuario.setText(usuarioNota);
                contenidoUsuario.getChildren().add(usuario);

                usuario.setContent(contenidoUsuarioNotas);
                try {
                    Examen = Base_datos.obtenerExamenesPorUsuarioYAsignaturaSinProfesor(conn, usuarioNota, asignatura);
                } catch (SQLException ex) {
                    Logger.getLogger(Panel_Nota_estadisticasController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(Examen);
                if (Examen.size() > 0) {
//                  //  contenidoUsuarioNotas.getChildren().add(new Label(usuarioNota));
//                  //  contenidoUsuarioNotas.getChildren().add(new Label(asignatura));

                    try {
                        Examen = Base_datos.obtenerExamenesPorUsuarioYAsignaturaSinProfesor(conn, usuarioNota, asignatura);
                        // Contenido del usuarioPane, puede ser cualquier nodo (por ejemplo, un VBox con estadísticas del usuario)
                        System.out.println("dentro de try");
                        System.out.println(Examen);
                        for (int k = 0; k < Examen.size(); k++) {

                            String exam = Examen.get(k).getExamen() + " " + Examen.get(k).getNota();
                            contenidoUsuarioNotas.getChildren().add(new Label(exam));
                            // contenidoUsuarioNotas.getChildren().add(new Label((Strings) Examen.get(1)));
                        }
                        // contenidoUsuarioNotas.getChildren().addAll(Examen);
                        //  accordion.getPanes().add(new TitledPane( asignatura),usuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Nota_estadisticasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            root.getChildren().add(TPasignatura);
            //  

        }

    }

}
