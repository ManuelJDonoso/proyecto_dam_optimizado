/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import com.github.sarxos.webcam.Webcam;
import es.manueldonoso.academia.main.main;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Acciones {

    /**
     * Abre una página web en el navegador predeterminado del sistema utilizando
     * la URL especificada.
     *
     * @param dir La dirección URL de la página web a abrir, en formato de
     * cadena.
     *
     * <p>
     * Este método utiliza la clase {@link java.awt.Desktop} para abrir la URL
     * en el navegador web predeterminado. Antes de intentar abrir la URL, se
     * verifica si el sistema soporta la operación
     * {@link java.awt.Desktop.Action#BROWSE}.
     * </p>
     */
    public static void abrirWeb(String dir) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop Dt = java.awt.Desktop.getDesktop();
            if (Dt.isSupported(java.awt.Desktop.Action.BROWSE)) {

                java.net.URI uri;
                try {
                    uri = new java.net.URI(dir);
                    Dt.browse(uri);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * Abre un archivo HTML local en el navegador predeterminado del sistema. La
     * ruta al archivo debe estar dentro del directorio de recursos del
     * proyecto, típicamente `src/main/resources`, y se debe proporcionar como
     * ruta relativa.
     *
     * @param dir La ruta relativa al archivo HTML dentro del directorio de
     * recursos, por ejemplo, "/documentacion/html/index.html".
     */
    public static void abrirWebLocal(String dir) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop Dt = java.awt.Desktop.getDesktop();
            if (Dt.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    // Obtén la URL del archivo dentro de resources
                    java.net.URL url = main.class.getResource(dir);  // `main.class` depende de la clase donde se encuentra este método
                    if (url != null) {
                        // Convierte la URL a URI y ábrela
                        java.net.URI uri = url.toURI();
                        Dt.browse(uri);
                    } else {
                        System.err.println("Archivo no encontrado en la ruta especificada: " + dir);
                    }
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Abre un archivo PDF utilizando la aplicación predeterminada del sistema
     * operativo.
     *
     * @param filePath Ruta del archivo PDF a abrir.
     */
    public static void abrirPDF(String filePath) {
        File archivoPDF = new File(filePath);

        // Verifica si el archivo existe
        if (!archivoPDF.exists()) {
            System.err.println("El archivo no existe: " + filePath);
            return;
        }

        // Verifica si el sistema soporta la acción Desktop
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(archivoPDF);
            } catch (IOException e) {
                System.err.println("Error al intentar abrir el archivo PDF: " + e.getMessage());
            }
        } else {
            System.err.println("La funcionalidad para abrir archivos no está soportada en este sistema.");
        }
    }

    /**
     * Verifica si un archivo existe en la ruta especificada.
     *
     * @param filePath La ruta del archivo a verificar, en formato de cadena.
     * @return {@code true} si el archivo existe, o {@code false} si no existe.
     *
     * <p>
     * Este método crea un objeto {@link File} con la ruta especificada y
     * utiliza el método {@link File#exists()} para verificar la existencia del
     * archivo.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     boolean archivoExiste = existeArchivo("ruta/al/archivo.txt");
     * </pre>
     */
    public static boolean existeArchivo(String filePath) {
        File archivo = new File(filePath);

        return archivo.exists();
    }

    /**
     * Cambia la imagen mostrada en un {@link ImageView} dado, cargando la
     * imagen desde una ruta específica.
     *
     * @param claseReferencia Una clase de referencia para el contexto de carga
     * (no se utiliza en el método, pero podría ser útil si necesitas cargar
     * desde recursos empaquetados).
     * @param imageView El {@link ImageView} en el que se mostrará la nueva
     * imagen.
     * @param url La ruta local del archivo de imagen que se desea cargar y
     * mostrar en el {@code ImageView}.
     *
     * <p>
     * Este método intenta cargar una imagen desde la ruta especificada, crea un
     * objeto {@link Image} y lo establece en el {@link ImageView} dado. Si
     * ocurre un error durante la carga de la imagen, el método captura la
     * excepción y muestra un mensaje de error en la salida estándar.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     imagenView_cambiarImage(getClass(), imageView, "ruta/a/la/imagen.png");
     * </pre>
     *
     * @see ImageView
     * @see Image
     */
    public static void imagenView_cambiarImage(Class<?> claseReferencia, ImageView imageView, String url) {
        try {
            File file = new File(url);
            Image image = new Image(file.toURI().toString());
            imageView.imageProperty().unbind();
            imageView.setImage(image);
        } catch (Exception e) {
            System.out.println("Error cargando la imagen: " + e.getMessage());
        }
    }

    /**
     * Cambia la imagen mostrada en un {@link ImageView} a partir de un arreglo
     * de bytes.
     *
     * @param claseReferencia La clase de referencia, aunque no se utiliza
     * directamente en el método, se incluye para seguir el formato del método
     * original.
     * @param imageView El componente {@link ImageView} donde se mostrará la
     * nueva imagen.
     * @param imageData El arreglo de bytes que contiene los datos de la imagen
     * a mostrar. El arreglo no debe ser nulo ni vacío.
     *
     * <p>
     * Este método toma un arreglo de bytes que representa una imagen, lo
     * convierte en un objeto {@link Image} y lo establece en el
     * {@link ImageView} proporcionado. Si el arreglo de bytes está vacío o
     * nulo, se mostrará un mensaje de error en la consola.</p>
     *
     * <p>
     * Si ocurre algún error durante la conversión o la actualización de la
     * imagen, se captura la excepción y se muestra el mensaje de error.</p>
     *
     * <p>
     * Ejemplo de uso:</p>
     * <pre>
     * byte[] imageBytes = ... // Obtener el arreglo de bytes de la imagen
     * imagenView_cambiarImage(MyClass.class, imageView, imageBytes);
     * </pre>
     */
    public static void imagenView_cambiarImage(Class<?> claseReferencia, ImageView imageView, byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
                Image image = new Image(byteArrayInputStream);
                imageView.imageProperty().unbind();
                imageView.setImage(image);
            } else {
                System.out.println("Error: El arreglo de bytes está vacío o es nulo.");
            }
        } catch (Exception e) {
            System.out.println("Error cargando la imagen: " + e.getMessage());
        }
    }

    /**
     * Captura una foto de la cámara web y devuelve la imagen capturada como un
     * arreglo de bytes (byte[]).
     *
     * @param imageView El componente {@link ImageView} donde se muestra el
     * flujo de la cámara.
     * @param estadoCamara Una propiedad booleana que indica el estado de la
     * cámara (activada o desactivada).
     * @param selWebCam Una referencia a la cámara web seleccionada.
     * @param bufferedImage Una referencia a la última imagen capturada como
     * {@link BufferedImage}.
     * @param imageProperty La propiedad de la imagen que enlaza con el
     * {@link ImageView} para mostrar el flujo de la cámara.
     * @return Un arreglo de bytes que representa la imagen capturada en formato
     * JPG, o {@code null} si no se capturó ninguna imagen.
     *
     * <p>
     * Este método controla el encendido y apagado de la cámara. Cuando la
     * cámara está activada, inicia un flujo de captura de imágenes que se
     * muestra en tiempo real en el {@link ImageView}. Al desactivar la cámara,
     * captura la última imagen como un arreglo de bytes en formato JPG y la
     * devuelve.</p>
     *
     * <p>
     * Si no hay cámaras disponibles, muestra una alerta de información y no
     * realiza ninguna captura.</p>
     *
     * <p>
     * Ejemplo de uso:</p>
     * <pre>
     * BooleanProperty estadoCamara = new SimpleBooleanProperty(false);
     * AtomicReference Webcam selWebCam = new AtomicReference();
     * AtomicReference BufferedImage bufferedImage = new AtomicReference();
     * ObjectProperty Image imageProperty = new SimpleObjectProperty();
     *
     * byte[] foto = CapturarFoto(imageView, estadoCamara, selWebCam, bufferedImage, imageProperty);
     * if (foto != null) {
     *     // Procesar el arreglo de bytes de la imagen capturada
     * }
     * </pre>
     */
    public static byte[] CapturarFoto(ImageView imageView, BooleanProperty estadoCamara,
            AtomicReference<Webcam> selWebCam, AtomicReference<BufferedImage> bufferedImage,
            ObjectProperty<Image> imageProperty) {

        if (Webcam.getWebcams().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No hay cámaras web disponibles", ButtonType.OK);
            alert.showAndWait();
            System.out.println("No hay cámaras");
            return null;
        }

        if (!estadoCamara.get()) {
            imageView.imageProperty().unbind();

            Task<Void> webCamInitializer = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    if (selWebCam.get() == null || !selWebCam.get().isOpen()) {
                        selWebCam.set(Webcam.getWebcams().get(0));
                        selWebCam.get().open();
                    }
                    estadoCamara.set(true);

                    Task<Void> task = new Task<>() {
                        @Override
                        protected Void call() throws Exception {
                            while (estadoCamara.get()) {
                                try {
                                    BufferedImage currentImage = selWebCam.get().getImage();
                                    bufferedImage.set(currentImage);

                                    if (bufferedImage.get() != null) {
                                        Platform.runLater(() -> {
                                            final Image mainImage = SwingFXUtils.toFXImage(bufferedImage.get(), null);
                                            imageProperty.set(mainImage);
                                        });
                                        bufferedImage.get().flush();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            return null;
                        }
                    };

                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                    imageView.imageProperty().bind(imageProperty);
                    return null;
                }
            };

            Thread webcamThread = new Thread(webCamInitializer);
            webcamThread.setDaemon(true);
            webcamThread.start();
            return null;
        } else {
            estadoCamara.set(false);
            if (selWebCam.get() != null) {
                selWebCam.get().close();
            }

            // Convertir BufferedImage a byte[]
            if (bufferedImage.get() != null) {
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    ImageIO.write(bufferedImage.get(), "jpg", baos);
                    return baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Convierte una imagen de tipo {@link Image} en un arreglo de bytes
     * (byte[]).
     *
     * @param image La imagen de tipo {@link Image} que se convertirá a un
     * arreglo de bytes. Si es {@code null}, se devuelve un arreglo de bytes
     * vacío.
     * @return Un arreglo de bytes que representa la imagen en formato JPG.
     *
     * <p>
     * Este método utiliza {@link SwingFXUtils} para convertir la imagen de tipo
     * {@link Image} a un {@link BufferedImage}, y luego utiliza {@link ImageIO}
     * para escribir la imagen en un flujo de salida en formato JPG.</p>
     *
     * <p>
     * Ejemplo de uso:</p>
     * <pre>
     * Image image = new Image("file:example.jpg");
     * byte[] imageBytes = ImageToByte(image);
     * // Procesar el arreglo de bytes como se necesite
     * </pre>
     */
    public static byte[] ImageToByte(Image image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (image != null) {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bImage, "jpg", baos);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        byte[] res = baos.toByteArray();

        try {
            baos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * Abre una ventana para seleccionar un archivo de imagen y convierte la
     * imagen seleccionada en un arreglo de bytes (byte[]).
     *
     * @param stage La ventana principal de la aplicación, necesaria para
     * mostrar el diálogo de selección de archivo.
     * @return Un arreglo de bytes que representa la imagen seleccionada.
     * Devuelve un arreglo vacío si no se selecciona ningún archivo o si ocurre
     * algún error durante la lectura del archivo.
     *
     * <p>
     * Este método abre un diálogo de selección de archivo mediante
     * {@link FileChooser}, permitiendo al usuario seleccionar una imagen con
     * extensiones .jpg, .jpeg o .png. Si el usuario selecciona una imagen, se
     * lee el archivo y se convierte en un arreglo de bytes para su posterior
     * uso.</p>
     *
     * <p>
     * Ejemplo de uso:</p>
     * <pre>
     * Stage stage = new Stage();
     * byte[] imageBytes = ImageToByte(stage);
     * if (imageBytes.length > 0) {
     *     // Procesar el arreglo de bytes de la imagen seleccionada
     * }
     * </pre>
     */
    public static byte[] ImageToByte(Stage stage) {
        // Abrir un diálogo para seleccionar el archivo de imagen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.png", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Si el usuario no selecciona ningún archivo, devolver un arreglo de bytes vacío
        if (selectedFile == null) {
            return new byte[0];
        }

        // Convertir el archivo seleccionado a un arreglo de bytes
        try (FileInputStream fis = new FileInputStream(selectedFile); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new byte[0];
        }
    }
}
