/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import es.manueldonoso.academia.modelos.Material;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Base_datos;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class PanelMaterialController implements Initializable {

    private String nombreArchivo, rutaArchivo;
    private Connection conn;
    //---------------------------Tabla----------------------------
    @FXML
    private TableView<Material> tabla_Material;
    @FXML
    private TableColumn<Material, String> colum_Asignatura;
    @FXML
    private TableColumn<Material, String> colum_tema;
    @FXML
    private TableColumn<Material, String> column_archivo;
    @FXML
    private TableColumn<Material, Void> column_accion;

    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colum_Asignatura.setCellValueFactory(new PropertyValueFactory<>("asignatura"));
        colum_tema.setCellValueFactory(new PropertyValueFactory<>("tema"));
        column_archivo.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        // Configurar la columna de acciones
        column_accion.setCellFactory(param -> new TableCell<Material, Void>() {
            private final JFXButton btnDescargar = new JFXButton("Descargar");

            // Crear un contenedor para los botones
            private final HBox hbox = new HBox(10, btnDescargar);

            {
                btnDescargar.setOnAction(event -> {
                    Material material = getTableView().getItems().get(getIndex());
                    Base_datos.descargarArchivo(conn, material.getId(), material.getNombre());
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Material material = getTableView().getItems().get(getIndex());
                   
                    setGraphic(hbox);
                }
            }
        });
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void loadUser(Usuario user) {

        this.usuario = user;

    }

    public void cargarDatosDesdeBaseDeDatos() {
        ObservableList<Material> listaMaterial = FXCollections.observableArrayList();

        // Obtener la lista de asignaturas
        List<String> listaAsignaturas = usuario.getAsignaturas();

        // Consulta preparada
        String consulta = "SELECT * FROM Material WHERE Asignatura = ? AND activo = 1 ";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            // Iterar sobre las asignaturas y cargar los datos
            for (String asignatura : listaAsignaturas) {
                pstmt.setString(1, asignatura); // Sustituir el parámetro en la consulta
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String nombre = rs.getString("Nombre");
                        String tema = rs.getString("Tema");
                        boolean activo = "1".equals(rs.getString("activo")); // Comparación simplificada

                        // Crear el objeto Material y añadirlo a la lista
                        Material material = new Material();
                        material.setAsignatura(asignatura);
                        material.setTema(tema);
                        material.setNombre(nombre);
                        material.setActivo(activo);
                        material.setId(rs.getString("id"));

                        listaMaterial.add(material);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }

        // Configurar los datos en la tabla
        tabla_Material.setItems(listaMaterial);
    }
}
