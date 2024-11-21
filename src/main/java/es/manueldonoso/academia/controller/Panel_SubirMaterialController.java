/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Material;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Efectos_visuales;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_SubirMaterialController implements Initializable {

    @FXML
    private JFXComboBox<String> cb_asignaturas;

    @FXML
    private HBox root;

    @FXML
    private JFXTextField tf_archivo;

    @FXML
    private JFXTextField tf_tema;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colum_Asignatura.setCellValueFactory(new PropertyValueFactory<>("asignatura"));
        colum_tema.setCellValueFactory(new PropertyValueFactory<>("tema"));
        column_archivo.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        // Configurar la columna de acciones
        column_accion.setCellFactory(param -> new TableCell<Material, Void>() {
            private final JFXButton btnDescargar = new JFXButton("Descargar");
            private final JFXButton btnEliminar = new JFXButton("Eliminar");
            private final JFXCheckBox activo = new JFXCheckBox();

            // Crear un contenedor para los botones
            private final HBox hbox = new HBox(10, btnDescargar, btnEliminar, activo);

            {
                btnDescargar.setOnAction(event -> {
                    Material material = getTableView().getItems().get(getIndex());
                    Base_datos.descargarArchivo(conn, material.getId(), material.getNombre());
                });

                btnEliminar.setOnAction(event -> {
                    Material material = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmar eliminación");
                    alert.setHeaderText("¿Estás seguro de que deseas eliminar el archivo?");
                    alert.setContentText(material.getNombre());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        Base_datos.eliminarArchivo(conn, material.getId(), material.getNombre());
                        cargarDatosDesdeBaseDeDatos();
                    }

                });
                activo.setOnAction(event -> {
                    Material material = getTableView().getItems().get(getIndex());
                    material.setActivo(activo.isSelected()); // Actualiza el estado activo del material
                    // Aquí puedes implementar la lógica para actualizar la base de datos
                    System.out.println("Estado activo actualizado: " + material.isActivo());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Material material = getTableView().getItems().get(getIndex());
                    activo.setSelected(material.isActivo()); // Sincroniza el estado del CheckBox con el valor en la base de datos
                    setGraphic(hbox);
                }
            }
        });

    }

    @FXML
    private void btn_agregar(ActionEvent event) {
        Base_datos.insertarFichero(conn, cb_asignaturas.getValue(), nombreArchivo, rutaArchivo, tf_tema.getText());
        cargarDatosDesdeBaseDeDatos();

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @FXML
    private void btn_selecionar(ActionEvent event) {
        // Crear el FileChooser
        FileChooser fileChooser = new FileChooser();

        // Configurar el título del diálogo
        fileChooser.setTitle("Selecciona un archivo");

        // Agregar filtros para los tipos de archivo
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        // Obtener el stage actual a partir del evento
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Mostrar el diálogo y obtener el archivo seleccionado
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            nombreArchivo = selectedFile.getName();
            rutaArchivo = selectedFile.getAbsolutePath();

            tf_archivo.setText(selectedFile.getName());

            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    public void loadCbAsignaturas(Usuario user) {
        cb_asignaturas.getItems().addAll(user.getAsignaturas());
        usuario = user;

    }

    public void cargarDatosDesdeBaseDeDatos() {
        ObservableList<Material> listaMaterial = FXCollections.observableArrayList();

        // Obtener la lista de asignaturas
        List<String> listaAsignaturas = usuario.getAsignaturas();

        // Consulta preparada
        String consulta = "SELECT * FROM Material WHERE Asignatura = ?";

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
