/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXTextArea;
import es.manueldonoso.academia.main.main;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * Clase que proporciona métodos utilitarios para trabajar con archivos,
 * imágenes, cámaras web y abrir recursos en el navegador o aplicaciones
 * predeterminadas.
 *
 * 
 * Incluye funcionalidades como abrir páginas web, cargar imágenes en
 * componentes de interfaz, capturar fotos desde la cámara web, y manipular
 * datos de imágenes y archivos.
 *
 *
 *
 * Ejemplo de uso:
 * <pre>
 * Acciones.abrirWeb("https://ejemplo.com");
 * </pre>
 *
 *
 * @author "Manuel Jesús Donoso Pérez";
 * @version 1.0
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
                } catch (URISyntaxException | IOException ex) {
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

    /**
     * Genera un nombre de usuario único basado en el nombre y apellido.
     *
     * @param conn Conexión a la base de datos.
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @return Nombre de usuario único.
     */
    public static String generaUsuario(Connection conn, String nombre, String apellido) {
        String usuario = nombre.substring(0, 3) + apellido.substring(0, 3);
        usuario = usuario.toUpperCase();
        int i = 0;
        while (Base_datos.IsUsuario(conn, usuario)) {
            i++;
            usuario = usuario.substring(0, 5) + i;
        }
        System.out.println(usuario);
        return usuario;
    }

    /**
     * Genera una consulta SQL reemplazando los marcadores de posición con
     * valores específicos.
     *
     * @param sql La consulta SQL con marcadores de posición.
     * @param parametros Los valores a reemplazar en los marcadores.
     * @return Consulta SQL con valores reemplazados.
     */
    public static String generarConsultaSQL(String sql, Object... parametros) {
        for (Object param : parametros) {
            String valor = (param != null) ? param.toString() : "NULL";
            sql = sql.replaceFirst("\\?", "'" + valor + "'");
        }
        return sql;
    }

    /**
     * Convierte un objeto {@link java.util.Date} en un {@link java.sql.Date}.
     *
     * @param date La fecha de tipo {@link java.util.Date}.
     * @return Fecha convertida a {@link java.sql.Date}.
     */
    public static java.sql.Date utilDate_to_sqlDate(java.util.Date date) {
        java.util.Date utilDate = date; // Fecha actual
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /**
     * Captura el contenido de varios  TextArea en un contenedor
     * {@link VBox}.
     *
     * @param preguntas Contenedor principal con varios {@link VBox} hijos.
     * @return Lista de mapas con las respuestas capturadas.
     */
    public static List<Map<String, String>> capturarContenidoTextAreas(VBox preguntas) {
        List<Map<String, String>> listaRespuestas = new ArrayList<>();

        // Iterar sobre los VBox hijos del VBox principal (preguntas)
        for (Node nodo : preguntas.getChildren()) {
            if (nodo instanceof VBox) { // Cada panel es un VBox
                VBox panel = (VBox) nodo;

                // Mapa para guardar el contenido del panel actual
                Map<String, String> respuestasPanel = new HashMap<>();

                // Llamar al método recursivo para obtener los TextAreas dentro del panel
                obtenerTextAreasRecursivos(panel, respuestasPanel);

                // Añadir el mapa del panel a la lista
                listaRespuestas.add(respuestasPanel);
            }
        }

        return listaRespuestas;
    }

    /**
     * Método recursivo para obtener el contenido de todos los
     * {@link JFXTextArea} dentro de un {@link VBox}, almacenando el contenido
     * en un mapa con la clave correspondiente al ID del {@code JFXTextArea}.
     *
     * @param vbox El {@link VBox} raíz desde donde se inicia la búsqueda de los
     * {@code JFXTextArea}.
     * @param respuestasPanel Un mapa donde se almacenan las respuestas. La
     * clave es el ID del {@code JFXTextArea}, y el valor es el texto contenido
     * en él.
     *
     * <p>
     * Este método recorre recursivamente los nodos hijos de un {@code VBox},
     * permitiendo capturar el contenido de estructuras complejas que contienen
     * múltiples {@link VBox} anidados.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     VBox preguntas = new VBox();
     *     Map&lt;String, String&gt; respuestas = new HashMap&lt;&gt;();
     *     obtenerTextAreasRecursivos(preguntas, respuestas);
     * </pre>
     */
    private static void obtenerTextAreasRecursivos(VBox vbox, Map<String, String> respuestasPanel) {
        // Iterar sobre todos los nodos dentro del VBox
        for (Node nodo : vbox.getChildren()) {
            if (nodo instanceof VBox) {
                // Llamar recursivamente a sub-vbox
                obtenerTextAreasRecursivos((VBox) nodo, respuestasPanel);
            } else if (nodo instanceof JFXTextArea) {
                // Verificar si el nodo es un JFXTextArea
                JFXTextArea textArea = (JFXTextArea) nodo;

                // Guardar el contenido si el JFXTextArea tiene un id
                if (textArea.getId() != null) {
                    respuestasPanel.put(textArea.getId(), textArea.getText());
                }
            }
        }
    }

    /**
     * Genera un número aleatorio dentro de un rango específico.
     *
     * @param min El valor mínimo del rango, inclusivo.
     * @param max El valor máximo del rango, inclusivo.
     * @return Un número entero aleatorio dentro del rango especificado
     * ({@code [min, max]}).
     * @throws IllegalArgumentException Si {@code min} es mayor que {@code max}.
     *
     * <p>
     * Este método utiliza la clase {@link Random} para generar el número
     * aleatorio.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     int numeroAleatorio = generarNumeroAleatorio(1, 10);
     * </pre>
     */
    public static int generarNumeroAleatorio(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el valor máximo.");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

        /**
     * Abre el cliente de correo predeterminado del sistema con un borrador de email 
     * preconfigurado utilizando los parámetros proporcionados.
     *
     * @param destinatario La dirección de correo electrónico del destinatario. Debe ser válida.
     * @param asunto       El asunto del correo. Puede incluir caracteres especiales, 
     *                     que serán codificados automáticamente.
     * @param mensaje      El cuerpo del mensaje. También será codificado para evitar errores
     *                     con caracteres especiales.
     * @throws UnsupportedOperationException Si la funcionalidad de correo no es soportada
     *                                       en el sistema operativo.
     * @throws IllegalArgumentException      Si el formato del URI no es válido.
     */
    public static void enviarEmail(String destinatario, String asunto, String mensaje) {
        try {
            // Codifica los parámetros para evitar problemas con caracteres especiales
            String encodedAsunto = URLEncoder.encode(asunto, "UTF-8");
            String encodedMensaje = URLEncoder.encode(mensaje, "UTF-8");

            // Construye el URI para el email
            String uriString = String.format(
                "mailto:%s?subject=%s&body=%s",
                destinatario,
                encodedAsunto,
                encodedMensaje
            );

            URI mailto = new URI(uriString);

            // Abre el cliente de correo predeterminado
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
                Desktop.getDesktop().mail(mailto);
            } else {
                System.out.println("El cliente de correo no está soportado en este sistema.");
            }
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error al codificar los parámetros: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al enviar el email: " + e.getMessage());
        }
    }
    
      /**
     * Abre WhatsApp en el navegador o en la aplicación de escritorio/móvil predeterminada para enviar un mensaje
     * a un número de teléfono específico.
     *
     * @param numeroTelefono El número de teléfono en formato internacional (incluyendo el código de país, 
     *                       por ejemplo, "34600111222" para España).
     * @param mensaje        El mensaje a enviar. Puede contener caracteres especiales, que serán codificados automáticamente.
     * @throws UnsupportedOperationException Si la funcionalidad de navegador no está soportada.
     * @throws IllegalArgumentException      Si el número de teléfono no es válido o el formato de URI falla.
     */
    public static void enviarWhatsApp(String numeroTelefono, String mensaje) {
        try {
            // Verifica si Desktop es compatible
            if (!Desktop.isDesktopSupported()) {
                throw new UnsupportedOperationException("La funcionalidad de navegador no está soportada.");
            }

            // Codifica el mensaje para evitar problemas con caracteres especiales
            String encodedMensaje = URLEncoder.encode(mensaje, "UTF-8");

            // Construye el URI de WhatsApp
            String uriString = String.format("https://wa.me/%s?text=%s", numeroTelefono, encodedMensaje);
            URI whatsappURI = new URI(uriString);

            // Abre el enlace en el navegador
            Desktop.getDesktop().browse(whatsappURI);

        } catch (UnsupportedEncodingException e) {
            System.err.println("Error al codificar el mensaje: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al abrir WhatsApp: " + e.getMessage());
        }
    }
  
}
