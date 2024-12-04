/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Stage_show;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Session;
import es.manueldonoso.academia.util.utilidades;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class LoginController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXButton btn_whatsapp;
    @FXML
    private JFXButton btn_link;
    @FXML
    private JFXButton btn_olvideContraseña;
    @FXML
    private JFXComboBox<Label> cb_selectBBDD;
    @FXML
    private JFXCheckBox ck_recorgar;
    @FXML
    private JFXButton btn_iniciar;
    @FXML
    private JFXTextField txt_nombre;
    @FXML
    private JFXPasswordField txt_pass;
    @FXML
    private JFXButton btn_registrar;
    @FXML
    private ImageView iv_base_conect;
    @FXML
    private JFXButton btn_Contrato;
    @FXML
    private JFXButton btn_datosDefecto;
    @FXML
    private JFXButton btn_Contrato1;
    @FXML
    private JFXButton btn_VideoAyuda;
    @FXML
    private JFXButton btn_link1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label lb1 = new Label("EMBEDIDA");
        Label lb2 = new Label("REMOTA");
        Label lb3 = new Label("RED");
        Label lb4 = new Label("LOCAL");

        // Configurar el color de texto para cada Label
        String labelStyle = "-fx-text-fill: #C0C0C0;";
        String labelStyle2 = "-fx-prompt-fill: #C0C0C0;";
        lb1.setStyle(labelStyle);
        lb2.setStyle(labelStyle);
        lb3.setStyle(labelStyle);
        lb4.setStyle(labelStyle);
        lb1.setStyle(labelStyle2);
        lb2.setStyle(labelStyle2);
        lb3.setStyle(labelStyle2);
        lb4.setStyle(labelStyle2);

        // Añadir los Labels al ComboBox
        cb_selectBBDD.getItems().addAll(lb1, lb2, lb3, lb4);

        System.out.println("Mostrando pantalla login");

        if (Acciones.existeArchivo("Contrato.pfd")) {
            btn_Contrato.setDisable(true);
            btn_registrar.setDisable(false);
        } else {
            btn_Contrato.setDisable(false);
            btn_registrar.setDisable(true);
        }

        RecuperarUsuario();
    }

    @FXML
    private void abrirweb(ActionEvent event) {
        Acciones.abrirWeb("http://www.manueldonoso.es");
    }

    @FXML
    private void Iniciar_Sesion(ActionEvent event) {

        GuardarUsuario();
        if (cb_selectBBDD.getValue() != null) {
               
            mostrarDasboard(getConecConnection());

        }else{
           // mostrarNotificacion();
            Efectos_visuales.mensajeNotificacionError("Advertencia", "Debe seleccionar una fuente de datos");
        }

    }

     private void mostrarNotificacion() {
        Notifications.create()
                .title("Advertencia") // Título de la notificación
                .text("Debe seleccionar una fuente de datos") // Mensaje de la notificación
                .position(Pos.CENTER) // Posición de la notificación
                .showWarning(); // Tipo de notificación (advertencia)
    }
    @FXML
    private void cargarContrato(ActionEvent event) {
        Stage_show.Mostrar_crearContrato();
    }

    @FXML
    private void btn_salir(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void MostrarContrato(ActionEvent event) {
        Acciones.abrirPDF("contrato.pdf");
    }

    @FXML
    private void btn_ComprobarConexion(ActionEvent event) {
        comprobarConexion();
    }

    @FXML
    private void btn_datosDefecto(ActionEvent event) {
        try {
           
            Base_datos.eliminarTodosUsuarios(Base_datos.conectarSqlite());
            Base_datos.insertarUsuarios(Base_datos.conectarSqlite());
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Comprueba la conexión a la base de datos seleccionada en la interfaz
     * gráfica y actualiza la imagen de estado. Dependiendo del tipo de base de
     * datos seleccionada en el `cb_selectBBDD`, intenta establecer la conexión
     * usando diferentes métodos de conexión definidos en la clase `Base_datos`.
     *
     * Si la conexión es exitosa, se cambia la imagen `iv_base_conect` para
     * indicar una conexión correcta; de lo contrario, se cambia para mostrar un
     * estado de conexión fallido.
     */
    private void comprobarConexion() {
        boolean conexion;
        switch (cb_selectBBDD.getValue().getText()) {
            case "EMBEDIDA":
                conexion = Base_datos.isConnection(Base_datos.conectarSqlite());
                break;
            case "REMOTA":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlRemota());
                break;

            case "RED":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlRed());
                break;

            case "LOCAL":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlLocal());
                break;
            default:
                conexion = false;
        }

        if (conexion) {
            Acciones.imagenView_cambiarImage(this.getClass(), iv_base_conect, "src/main/resources/images/app/bbdd_ok.png");
        } else {
            Acciones.imagenView_cambiarImage(this.getClass(), iv_base_conect, "src/main/resources/images/app/bbdd_down.png");

        }
    }

    /**
     * Recupera y carga el usuario y la contraseña memorizados en la interfaz de
     * usuario. Establece los valores recuperados en los campos de texto
     * `txt_nombre` y `txt_pass`. Si ambos campos contienen valores, el checkbox
     * `ck_recorgar` se selecciona automáticamente para indicar que el usuario
     * ha sido guardado anteriormente.
     */
    private void RecuperarUsuario() {
        txt_nombre.setText(Base_datos.UsuarioMemorizado());
        txt_pass.setText(Base_datos.PassMemorizado());
        if (!txt_nombre.getText().isEmpty() && !txt_pass.getText().isEmpty()) {
            ck_recorgar.setSelected(true);
        }
    }

    /**
     * Guarda el usuario y la contraseña ingresados en la base de datos si el
     * checkbox `ck_recorgar` está seleccionado. Si el checkbox está
     * seleccionado, los valores de `txt_nombre` y `txt_pass` se memorizan
     * utilizando el método `MemorizarUsuario` de la clase `Base_datos`. Si no
     * está seleccionado, se guardan valores vacíos.
     */
    private void GuardarUsuario() {

        if (ck_recorgar.isSelected()) {
            Base_datos.MemorizarUsuario(txt_nombre.getText(), txt_pass.getText());
        } else {
            Base_datos.MemorizarUsuario("", "");
        }
    }

    /**
     * Obtiene el tipo de usuario (`fk_tipo`) de la base de datos seleccionada
     * según las credenciales ingresadas. Este método utiliza la opción
     * seleccionada en el combo box `cb_selectBBDD` para determinar el tipo de
     * conexión a la base de datos (EMBEDIDA, REMOTA, RED o LOCAL). Luego,
     * intenta verificar el usuario y contraseña ingresados en `txt_nombre` y
     * `txt_pass`.
     *
     * Dependiendo de la conexión seleccionada, se establece una conexión
     * específica y se llama al método `verificarUsuario` de la clase
     * `Base_datos` para comprobar las credenciales. Si el usuario existe y las
     * credenciales son correctas, se devuelve el valor de `fk_tipo`
     * correspondiente al usuario. En caso contrario, o si ocurre una excepción,
     * devuelve `null`.
     *
     * @return El tipo de usuario (`fk_tipo`) si el usuario y la contraseña son
     * correctos, o `null` si no coinciden o si ocurre algún error durante la
     * consulta.
     */
    private String getTipoUsuario() {

        String Usuario = txt_nombre.getText();
        String Pass = txt_pass.getText();
        Connection conn = getConecConnection();

        try {
            String tipo = Base_datos.verificarUsuario(conn, Usuario, Pass);

            if (tipo != null) {
                if (Pass.equals("cambiame")) {
                    // Muestra la pantalla de cambio de contraseña
                    Cambio_ContrasenaController cambioPassController = Stage_show.MostrarCambioPass(root);

                    // Verifica si el botón aceptar fue pulsado antes de continuar
                    if (cambioPassController != null && cambioPassController.isBotonAceptarPulsado()) {
                        // Aquí puedes incluir cualquier acción adicional necesaria después de la confirmación
                        return tipo;
                    }
                    Session.setPass(null);

                }
                return tipo;
            }
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Muestra el panel de control (dashboard) correspondiente al tipo de
     * usuario autenticado. Este método verifica el tipo de usuario actual
     * llamando a `getTipoUsuario()` y, en función de este valor, muestra el
     * dashboard específico de ese tipo de usuario.
     *
     * <ul>
     * <li>Si el usuario es "ADMINISTRADOR", se muestra el dashboard de
     * administrador.</li>
     * <li>Si el usuario es "PROFESOR", se muestra el dashboard de
     * profesor.</li>
     * <li>Si el usuario es "ALUMNO", se muestra el dashboard de alumno.</li>
     * </ul>
     *
     * El método no realiza ninguna acción si el tipo de usuario no coincide con
     * ninguno de los valores especificados.
     */
    private void mostrarDasboard(Connection conn) {
        Session.setUsuario(txt_nombre.getText());

        switch (getTipoUsuario()) {
            case "ADMINISTRADOR":
                Stage_show.Mostrar_Dasboard_Administrador(conn);
                break;
            case "PROFESOR":

                Stage_show.Mostrar_Dasboard_Profesor(conn);
                break;

            case "ALUMNO":
                Stage_show.Mostrar_Dasboard_Alumno(conn);
                break;

            default:
                Efectos_visuales.AnimacionComponenteTada(txt_nombre);
                  Efectos_visuales.AnimacionComponenteTada(txt_pass);
                  Efectos_visuales.mensajeNotificacionError("error", "Usuario o contraseña erroneos");

        }

    }

    public Connection getConecConnection() {
        String ddbb = cb_selectBBDD.getValue().getText();
        Connection conn;
        switch (ddbb) {
            case "EMBEDIDA":
                conn = Base_datos.conectarSqlite();
                break;
            case "REMOTA":
                conn = Base_datos.connectMysqlRemota();
                break;

            case "RED":
                conn = Base_datos.connectMysqlRed();
                break;

            case "LOCAL":
                conn = Base_datos.connectMysqlLocal();
                break;
            default:
                conn = null;

        }
        return conn;
    }

    @FXML
    private void MostrarDocumentacion(ActionEvent event) {
        Acciones.abrirWebLocal("/documentacion/html/index.html");
    }

    @FXML
    private void btn_olvideContraseña(ActionEvent event) {
        
        Acciones.enviarEmail("donperma@gmail.com", "recuperar contraseña", "el usuario "+txt_nombre.getText());
    }

    @FXML
    private void btn_whatsapp(ActionEvent event) {
        Acciones.enviarWhatsApp("655354312", "Esta es el wathsapp del desarrollador, para cualquier duda envie un mensaje.");
    }

    @FXML
    private void MostrarvideoAyuda(ActionEvent event) {
        Stage_show.Mostrar_VideoAyuda();
    }

    @FXML
    private void abrirweb_git(ActionEvent event) {
          Acciones.abrirWeb("https://github.com/ManuelJDonoso/proyecto_dam_optimizado");
    }

}
