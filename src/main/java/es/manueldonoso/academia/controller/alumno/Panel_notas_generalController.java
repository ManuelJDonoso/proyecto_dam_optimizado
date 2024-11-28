/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller.alumno;

import com.itextpdf.kernel.colors.Separation;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_notas_generalController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private Accordion Panel_acordeon_asignaturas;

    private Connection conn;
    private Usuario user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

  public void CargarNotas(Usuario user) {
    Panel_acordeon_asignaturas.getPanes().clear();
    this.user = user;
    List asignaturas = this.user.getAsignaturas();

    for (int i = 0; i < asignaturas.size(); i++) {
        VBox panel = new VBox();
        String Asignatura = (String) asignaturas.get(i);

        TitledPane asignatura = new TitledPane(Asignatura, panel);

        try {
            List Examen = Base_datos.obtenerExamenesPorUsuarioYAsignatura(conn, user.getUsuario(), Asignatura);
            List NotasFinales = Base_datos.BuscarExamenes(conn, Asignatura);

            // Crear el gráfico de barras horizontales
            NumberAxis xAxis = new NumberAxis(0, 10, 1); // Eje X (notas) con valor máximo 10
            xAxis.setLabel("Notas");
            CategoryAxis yAxis = new CategoryAxis(); // Eje Y (títulos de exámenes)
            yAxis.setLabel("Exámenes");

            BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Notas de Exámenes");
            barChart.setLegendVisible(false); // Ocultar la leyenda si no es necesaria

            XYChart.Series<Number, String> series = new XYChart.Series<>();

            for (int j = 0; j < NotasFinales.size(); j++) {
                String titulo = (String) NotasFinales.get(j);
                String nota = Base_datos.NotaExamenMax(conn, user.getUsuario(), Asignatura, titulo);

                // Agregar datos al gráfico
                series.getData().add(new XYChart.Data<>(Double.parseDouble(nota), titulo));

                // Agregar texto al panel
                panel.getChildren().add(new Text(titulo));
                panel.getChildren().add(new Text("Nota: " + nota));
            }

            barChart.getData().add(series);
            panel.getChildren().add(barChart); // Añadir el gráfico al panel

            panel.getChildren().add(new Separator());

            for (int j = 0; j < Examen.size(); j++) {
                examen ex = (examen) Examen.get(j);
                panel.getChildren().add(new Text(ex.getExamen()));
                panel.getChildren().add(new Text("Nota: " + ex.getNota()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Panel_notas_generalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        anadirTitledPane(Panel_acordeon_asignaturas, asignatura);
    }
}
    /**
     * Añade un TitledPane al Accordion especificado.
     *
     * @param accordion El Accordion al que se añadirá el TitledPane.
     * @param titledPane El asignatura que se añadirá al Accordion.
     */
    private static void anadirTitledPane(Accordion accordion, TitledPane asignatura) {
        if (accordion != null && asignatura != null) {
            accordion.getPanes().add(asignatura);
        }
    }
}
