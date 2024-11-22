/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *Esta clase contiene métodos para aplicar efectos visuales y realizar ajustes estéticos en nodos, ventanas y otros elementos de la interfaz de usuario en JavaFX. 
 * Aquí se incluyen funcionalidades para cerrar ventanas con animaciones, moverlas, trabajar con imágenes circulares y establecer fondos personalizados en paneles.
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Efectos_visuales {

    /**
     * Cierra la ventana aplicando un efecto de reducción de escala en el nodo
     * especificado.
     *
     * <p>
     * Este método aplica una transición de escala a un nodo, reduciéndolo a un
     * tamaño de 0 en los ejes X, Y y Z en el transcurso de 500 milisegundos.
     * Una vez completada la animación, la ventana asociada al nodo se cierra
     * automáticamente.</p>
     *
     * @param node el nodo en cuya ventana se aplicará el efecto de cierre
     */
    public static void closeEffectReducion(Node node) {
        final Stage stage = (Stage) node.getScene().getWindow();
        ScaleTransition st = new ScaleTransition(Duration.millis(500), node);
        st.setToX(0);
        st.setToY(0);
        st.setToZ(0);
        st.play();
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.close();
            }
        });
    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    /**
     * Permite mover la ventana (Stage) haciendo clic y arrastrando el mouse
     * sobre la escena.
     *
     * <p>
     * Este método establece dos manejadores de eventos en la escena de la
     * ventana especificada: uno para registrar la posición inicial del clic del
     * mouse y otro para mover la ventana según el movimiento del mouse,
     * calculando el desplazamiento basado en la posición inicial.</p>
     *
     * @param stage la ventana (Stage) que se podrá mover con el mouse
     */
    public static void darMovimientoStage(Stage stage) {

        stage.getScene().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        stage.getScene().setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    /**
     * Establece un avatar circular en el ImageView proporcionado, centrando la
     * imagen dentro del círculo.
     *
     * Este método ajusta el tamaño del ImageView para que coincida con el radio
     * proporcionado, preservando las proporciones de la imagen. Además, centra
     * la imagen recortando la parte central y luego aplica un círculo como
     * máscara para darle la forma circular.
     *
     * @param iv El ImageView donde se mostrará la imagen con forma circular.
     * @param radius El radio del círculo, que determinará el tamaño de la
     * imagen visible.
     */
    public static void Avatar(ImageView iv, double radius) {

        // Establecer el tamaño del ImageView
        iv.setFitWidth(radius * 2);
        iv.setFitHeight(radius * 2);
        iv.setPreserveRatio(true);

        // Obtener las dimensiones de la imagen original para calcular el centro
        double imageWidth = iv.getImage().getWidth();
        double imageHeight = iv.getImage().getHeight();

        // Calcular las coordenadas del centro de la imagen para centrado en el clip circular
        double centerX = (imageWidth - radius * 2) / 2;
        double centerY = (imageHeight - radius * 2) / 2;

        // Aplicar un viewport centrado en la imagen
        iv.setViewport(new Rectangle2D(centerX, centerY, radius * 2, radius * 2));

        // Crear y aplicar el círculo como clip para el ImageView
        Circle clip = new Circle(radius, radius, radius);
        iv.setClip(clip);
    }

    /**
     * Modifica el tamaño de la imagen sin cambiar el tamaño del radio. La
     * imagen se escala proporcionalmente para ajustarse al radio del círculo,
     * pero el radio no cambia.
     *
     * @param iv El ImageView que contiene la imagen.
     * @param radius El radio del círculo que se usará para el clip.
     * @param scale El factor de escala para cambiar el tamaño de la imagen.
     */
    public static void modificarTamañoImagen(ImageView iv, double radius, double scale) {
        // Establecer el tamaño del ImageView según el radio (sin cambiar el radio)
        iv.setFitWidth(radius * 2);
        iv.setFitHeight(radius * 2);
        iv.setPreserveRatio(true);

        // Obtener la imagen original
        Image image = iv.getImage();

        // Calcular el nuevo tamaño de la imagen aplicando el factor de escala
        double newWidth = image.getWidth() * scale;
        double newHeight = image.getHeight() * scale;

        // Crear una nueva imagen escalada
        Image scaledImage = new Image(image.getUrl(), newWidth, newHeight, true, true);

        // Establecer la imagen escalada al ImageView
        iv.setImage(scaledImage);

        // Obtener las nuevas dimensiones de la imagen escalada
        double imageWidth = iv.getImage().getWidth();
        double imageHeight = iv.getImage().getHeight();

        // Calcular las coordenadas del centro de la imagen escalada para centrado en el clip circular
        double centerX = (imageWidth - radius * 2) / 2;
        double centerY = (imageHeight - radius * 2) / 2;

        // Aplicar un viewport centrado en la imagen escalada
        iv.setViewport(new Rectangle2D(centerX, centerY, radius * 2, radius * 2));

        // Crear y aplicar el círculo como clip para el ImageView
        Circle clip = new Circle(radius, radius, radius);
        iv.setClip(clip);
    }

    /**
     * Establece una imagen como fondo en el AnchorPane proporcionado.
     *
     * @param pane Nodo al que se le va a modificar el fondo
     * @param imagePath La ruta de la imagen que se usará como fondo.
     */
    public static void setBackgroundImage(Pane pane, String imagePath) {
        // Cargar la imagen desde la ruta especificada
        Image backgroundImage = new Image(imagePath);

        // Configurar el fondo con la imagen
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, // No repetir la imagen
                BackgroundRepeat.NO_REPEAT, // No repetir la imagen
                BackgroundPosition.CENTER, // Centrar la imagen
                new BackgroundSize(
                        BackgroundSize.AUTO, // Ancho automático
                        BackgroundSize.AUTO, // Alto automático
                        false, // No usar ancho total del AnchorPane
                        false, // No usar alto total del AnchorPane
                        true, // Ajustar al tamaño del contenedor
                        true // Ajustar para cubrir todo el contenedor
                )
        );

        // Establecer el fondo en el AnchorPane
        pane.setBackground(new Background(background));
    }

}
