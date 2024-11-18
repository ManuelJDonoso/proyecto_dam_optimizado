/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class utilidades {

    private static String negro = "\033[30m";
    private static String rojo = "\033[31m";
    private static String verde = "\033[32m";
    private static String amarillo = "\033[33m";
    private static String azul = "\033[34m";
    private static String margenta = "\033[35m";
    private static String celeste = "\033[30m";
    private static String blanco = "\033[30m";

    private static String frojo = "\033[41m";
    private static String fverde = "\033[42m";
    private static String fAmarillo = "\033[43m";
    private static String fazul = "\033[44m";
    private static String fMargenta = "\033[45m";
    private static String fceleste = "\033[46m";
    private static String fgris = "\033[47m";

    /**
     * Imprime un mensaje de log junto con información sobre la clase, el método
     * y la línea desde donde fue llamado. Este método es útil para depuración.
     *
     * @param message El mensaje que se desea imprimir en el log. Puede ser
     * cualquier texto que ayude a entender el estado del programa.
     *
     * Ejemplo de uso:      <pre>{@code
     * public static void main(String[] args) {
     *     log("Este es un mensaje de prueba");
     * }
     * }</pre>
     *
     * Salida esperada:
     * <pre>
     * [NombreClase:Linea] NombreMetodo: Este es un mensaje de prueba
     * </pre>
     *
     * Limitaciones: - Este método utiliza
     * `Thread.currentThread().getStackTrace()`, lo que puede ser costoso si se
     * llama frecuentemente en bucles o métodos muy utilizados. - No está
     * diseñado para reemplazar un sistema de logging robusto como Log4j o
     * SLF4J.
     */
    public static void log(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 2) {
            // El índice 2 contiene la información del método que llamó a log().
            StackTraceElement element = stackTrace[2];
            System.out.println( element.getClassName());
            System.out.println(element.getLineNumber());
            System.out.println(message);
//            System.out.printf("[%s:%d] %s: %s%n",
//              
//                    element.getMethodName(), // Método
//                    message); // Mensaje
        }
    }
}
