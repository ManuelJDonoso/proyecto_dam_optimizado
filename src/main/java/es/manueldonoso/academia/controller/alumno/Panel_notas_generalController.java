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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
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

    /**
     * Carga las notas del usuario en el panel de acordeón. Cada asignatura se
     * muestra como un TitledPane con un gráfico de barras y detalles de
     * exámenes.
     *
     * @param user El usuario del cual se obtendrán las asignaturas y notas.
     */
    public void CargarNotas(Usuario user) {

        Panel_acordeon_asignaturas.getPanes().clear();
        this.user = user;
        List asignaturas = this.user.getAsignaturas();
        List Examen = null ,NotasFinales ;
        
        
        
          
        

        for (int i = 0; i < asignaturas.size(); i++) {
            VBox panel = new VBox();
            panel.setMinHeight(400); // Altura mínima para uniformidad
            String Asignatura = (String) asignaturas.get(i);
              //examenes por usuario y asignatura
            try {
                Examen = Base_datos.obtenerExamenesPorUsuarioYAsignatura(conn, user.getUsuario(), Asignatura);
            } catch (SQLException ex) {
                Logger.getLogger(Panel_notas_generalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Lista con el nombre temario
         
            NotasFinales = Base_datos.BuscarExamenesRealizados(conn, Asignatura);
           
           
           
            //titulo del acordeon   
            
            TitledPane asignatura = new TitledPane(Asignatura, panel);
            
            

            dibujarGrafico(panel, NotasFinales, Asignatura);
            //separador
            panel.getChildren().add(new Separator());
            mostrarNotas(Examen, panel);
         


            anadirTitledPane(Panel_acordeon_asignaturas, asignatura);
        }
    }

    /**
     * Añade un TitledPane al Accordion especificado.
     *
     * @param accordion El Accordion al que se añadirá el TitledPane.
     * @param titledPane El asignatura que se añadirá al Accordion.
     */
    private  void anadirTitledPane(Accordion accordion, TitledPane asignatura) {
        if (accordion != null && asignatura != null) {
            accordion.getPanes().add(asignatura);
        }
    }
    
    private void mostrarNotas(List Examen,VBox panel ){
        ScrollPane sp = new ScrollPane(); // Crear el ScrollPane
        panel.getChildren().add(sp);
        VBox vb = new VBox(); // Crear el VBox
        for (int j = 0; j < Examen.size(); j++) {
            
            examen ex = (examen) Examen.get(j);
            HBox hb = new HBox(new Text(ex.getExamen()), new Text("Nota: " + ex.getNota()));
            vb.getChildren().add(hb);
            
        }
        // Configurar el VBox como contenido del ScrollPane
        sp.setContent(vb);
        // Opcional: Configuración adicional
        sp.setFitToWidth(true); // Ajusta el contenido al ancho del ScrollPane
        sp.setPannable(true);   // Habilita el desplazamiento con el mouse
    }
    private void dibujarGrafico( VBox panel,List NotasFinales,String Asignatura){
    

                // Crear el gráfico de barras horizontales
                NumberAxis xAxis = new NumberAxis(0, 10, 1); // Eje X (notas) con valor máximo 10
                xAxis.setLabel("Notas");
                xAxis.setAutoRanging(false); // Desactivar autoajuste para mantener el mismo rango
                xAxis.setTickUnit(1);

                CategoryAxis yAxis = new CategoryAxis(); // Eje Y (títulos de exámenes)
                yAxis.setLabel("Exámenes");

                BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);
                barChart.setTitle("Notas de Exámenes");
                barChart.setLegendVisible(false); // Ocultar la leyenda si no es necesaria
                barChart.setCategoryGap(10); // Espaciado entre categorías
                barChart.setBarGap(5); // Espaciado entre barras

                // Establecer un ancho fijo para el gráfico
                barChart.setPrefWidth(400); // Ajusta el ancho según sea necesario
                barChart.setMinHeight(200);

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
    }
}
