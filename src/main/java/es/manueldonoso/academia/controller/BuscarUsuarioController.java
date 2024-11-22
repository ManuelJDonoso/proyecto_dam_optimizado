/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Stage_show;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class BuscarUsuarioController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField tf_Nombre;
    @FXML
    private JFXTextField tf_Apellidos;
    @FXML
    private JFXTextField tf_Direccion;
    @FXML
    private JFXTextField tf_Email;
    @FXML
    private JFXTextField tf_Telefono;
    @FXML
    private JFXTextField tf_Usuario;
    @FXML
    private TableView<Usuario> tabla_admin;
    @FXML
    private TableColumn<Usuario, String> colum_usuario_admin;
    @FXML
    private TableColumn<Usuario, Void> columna_Accion_administrador;
    @FXML
    private TableView<Usuario> tabla_profesor;
    @FXML
    private TableColumn<Usuario, String> usuario_profesor;
    @FXML
    private TableColumn<Usuario, Void> accion_profesor;
    @FXML
    private TableView<Usuario> tabla_alumnoa;
    @FXML
    private TableColumn<Usuario, String> Usuario_alumno;
    @FXML
    private TableColumn<Usuario, Void> accion_alumno;

    private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void buscarUsuarios(KeyEvent event) {
        buscarUsuarios();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private void buscarUsuarios() {
        System.out.println("Buscando...");

        ObservableList<Usuario> dataAdmin = Base_datos.buscarUser(conn, "ADMINISTRADOR", tf_Nombre.getText().trim(), tf_Apellidos.getText().trim(), tf_Telefono.getText().trim(), tf_Email.getText().trim(), tf_Usuario.getText().trim(), tf_Direccion.getText().trim());
        tabla_admin.setItems(dataAdmin);

        ObservableList<Usuario> datapro = Base_datos.buscarUser(conn, "PROFESOR", tf_Nombre.getText().trim(), tf_Apellidos.getText().trim(), tf_Telefono.getText().trim(), tf_Email.getText().trim(), tf_Usuario.getText().trim(), tf_Direccion.getText().trim());
        tabla_profesor.setItems(datapro);

        ObservableList<Usuario> dataalum = Base_datos.buscarUser(conn, "ALUMNO", tf_Nombre.getText().trim(), tf_Apellidos.getText().trim(), tf_Telefono.getText().trim(), tf_Email.getText().trim(), tf_Usuario.getText().trim(), tf_Direccion.getText().trim());
        tabla_alumnoa.setItems(dataalum);
    }

    private Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> createButtonCellFactory() {
        return column -> new TableCell<Usuario, Void>() {
            JFXButton button = new JFXButton("Editar");

            {
                // Cargar la imagen
                Image image = new Image(getClass().getResourceAsStream("/images/app/edit_log.png"));
                ImageView imageView = new ImageView(image);

                // Configurar el tamaño de la imagen si es necesario
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);

                // Asignar el ImageView al botón
                button.setGraphic(imageView);

                button.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());

                    //definir la acción que deseas realizar al presionar el botón
                    Stage_show.Mostrar_ModificarDastos(conn, root, usuario.getUsuario());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }

    public void iniciar() {
        colum_usuario_admin.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        columna_Accion_administrador.setCellFactory(createButtonCellFactory());

        usuario_profesor.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        accion_profesor.setCellFactory(createButtonCellFactory());

        Usuario_alumno.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        accion_alumno.setCellFactory(createButtonCellFactory());

        buscarUsuarios();
    }
}
