/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class VideoAyudaController implements Initializable {

    @FXML
    private Slider Slider_tiempo;
    @FXML
    private JFXButton brn_play;

    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaVideo;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Ruta del archivo de video
        URL videoUrl = getClass().getResource("/mediaVideo/videoAyuda.mp4");
        if (videoUrl == null) {
            System.err.println("El archivo de video no se encontró en la ruta especificada.");
            return;
        }

        // Crear el objeto Media y MediaPlayer
        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        if (videoUrl == null) {
            System.err.println("El archivo de video no se encontró.");
            return;
        }

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnError(() -> System.err.println("Error en MediaPlayer: " + mediaPlayer.getError().getMessage()));
        mediaVideo.setMediaPlayer(mediaPlayer);

        // Asociar el MediaPlayer al MediaView
        mediaVideo.setMediaPlayer(mediaPlayer);

        // Configurar el deslizador para reflejar el progreso del video
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            double progress = newTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
            Slider_tiempo.setValue(progress * 100);
        });

        // Permitir que el usuario busque en el video con el deslizador
        Slider_tiempo.setOnMouseReleased(event -> {
            if (mediaPlayer.getTotalDuration() != null) {
                double position = Slider_tiempo.getValue() / 100;
                mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(position));
            }
        });

        // Configurar el botón Play/Pause
        brn_play.setOnAction(event -> togglePlayPause());
    }

    private void togglePlayPause() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            brn_play.setText("Play");
        } else {
            mediaPlayer.play();
            brn_play.setText("Pause");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;

        // Configurar el cierre de la ventana para detener el MediaPlayer
        stage.setOnCloseRequest(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
        });
    }
}
