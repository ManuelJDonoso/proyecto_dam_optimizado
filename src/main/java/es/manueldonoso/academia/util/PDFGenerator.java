/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
/**
* Clase para generar documentos PDF de manera sencilla.
 * Utiliza la biblioteca iText para crear un archivo PDF a partir de un texto dado.
 * @author "Manuel Jesús Donoso Pérez";
 */
public class PDFGenerator {
/**
     * Genera un archivo PDF con el contenido proporcionado.
     *
     * @param textContent     El contenido de texto que se incluirá en el PDF.
     * @param outputFilePath  La ruta completa donde se guardará el archivo PDF generado.
     */
    public static void generatePDF(String textContent, String outputFilePath) {
        try {
            // Crear un objeto PdfWriter
            PdfWriter writer = new PdfWriter(outputFilePath);

            // Crear un PdfDocument con el PdfWriter
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Crear un documento para el PDF
            Document document = new Document(pdfDoc);

            // Añadir el texto del String como un párrafo en el PDF
            document.add(new Paragraph(textContent));

            // Cerrar el documento
            document.close();

            System.out.println("PDF generado exitosamente en: " + outputFilePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
