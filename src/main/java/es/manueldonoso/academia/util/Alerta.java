/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Alerta {

    public static void Error(String titulo, String barra, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(barra);
        alerta.setContentText(mensaje);

        // Obtener el Stage de la alerta
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Eliminar la barra de título
        stage.initStyle(javafx.stage.StageStyle.UTILITY);  // Elimina la barra de título
        stage.setOpacity(0.95);  // Opcional: ajuste de opacidad para un estilo personalizado

        alerta.showAndWait();

    }

    public static boolean Confirmacion(String titulo, String barra, String mensaje) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(barra);
        alerta.setContentText(mensaje);

        // Obtener el Stage de la alerta
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Eliminar la barra de título
        stage.initStyle(javafx.stage.StageStyle.UTILITY);  // Elimina la barra de título
        stage.setOpacity(0.95);  // Opcional: ajuste de opacidad para un estilo personalizado

        Optional<ButtonType> result = alerta.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
