/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import es.manueldonoso.academia.main.main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
     *
     * @throws URISyntaxException Si la dirección URL proporcionada no es
     * válida.
     * @throws IOException Si ocurre un error de entrada/salida al intentar
     * abrir el navegador.
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     abrirWeb("https://www.ejemplo.com");
     * </pre>
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
     * Cambia la imagen mostrada en un {@link ImageView} dado, cargando la imagen desde una ruta específica.
     *
     * @param claseReferencia Una clase de referencia para el contexto de carga (no se utiliza en el método, pero podría ser útil si necesitas cargar desde recursos empaquetados).
     * @param imageView       El {@link ImageView} en el que se mostrará la nueva imagen.
     * @param url             La ruta local del archivo de imagen que se desea cargar y mostrar en el {@code ImageView}.
     *
     * <p>Este método intenta cargar una imagen desde la ruta especificada, crea un objeto {@link Image}
     * y lo establece en el {@link ImageView} dado. Si ocurre un error durante la carga de la imagen, el método
     * captura la excepción y muestra un mensaje de error en la salida estándar.</p>
     *
     * <p><strong>Ejemplo de uso:</strong></p>
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
}
