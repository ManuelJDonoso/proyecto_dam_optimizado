/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Clase para gestionar la generación de un contrato de licencia de uso de
 * software. Permite personalizar el contrato con el nombre del usuario final y
 * la fecha del acuerdo.
 *
 * @author donpe
 */
public class Contrato {

    private static String fecha;
    private static String usuarioFinal = "";

    // Plantilla del contrato, donde se incluirán los datos personalizados
    private static final String plantillaContrato = "Contrato de Licencia de Uso de Software\n"
            + "\n"
            + "Entre Manuel Jesús Donoso Pérez, con página web www.manueldonoso.es y correo de contacto dev@manueldonoso.es (en adelante, \"el Propietario\")"
            + " y "
            + "El usuario final del software %s  (en adelante, \"el Usuario\").\n"
            + "\n"
            + "OBJETO DEL CONTRATO\n\n"
            + "Este contrato regula el uso del software desarrollado por el Propietario (en adelante, \"la Aplicación\"), que el Usuario acepta al hacer uso de la misma. Al descargar, instalar o utilizar la Aplicación, el Usuario acepta los términos y condiciones establecidos en este documento.\n"
            + "\n"
            + "1. Derechos de Uso\n"
            + "\n"
            + "1.1 El Propietario concede al Usuario una licencia no exclusiva, intransferible y gratuita para usar la Aplicación.\n"
            + "\n"
            + "1.2 El Usuario puede distribuir la Aplicación a terceros, siempre y cuando no se realice con fines comerciales o de lucro.\n"
            + "\n"
            + "1.3 El Usuario no podrá cobrar por la distribución o uso de la Aplicación, ya sea directa o indirectamente.\n"
            + "\n"
            + "2. Modificaciones del Software\n"
            + "\n"
            + "2.1 El Usuario tiene derecho a modificar el código de la Aplicación, siempre que cumpla con las siguientes condiciones:\n"
            + "\n"
            + "    Deberá reconocer explícitamente que la versión modificada se basa en la obra original de Manuel Jesús Donoso Pérez.\n"
            + "    Incluir una mención visible y clara del autor original en cualquier distribución del software modificado.\n"
            + "\n"
            + "2.2 Cualquier redistribución de la versión modificada también estará sujeta a las restricciones del presente contrato, incluidas las disposiciones sobre la prohibición de lucro.\n"
            + "\n"
            + "3. Exoneración de Responsabilidad\n"
            + "\n"
            + "3.1 La Aplicación se proporciona \"tal cual\" y el Propietario no garantiza que la misma esté libre de errores o sea adecuada para cualquier propósito específico.\n"
            + "\n"
            + "3.2 El Usuario utiliza la Aplicación bajo su propia responsabilidad, y el Propietario no será responsable de ningún daño derivado del uso o la imposibilidad de uso de la Aplicación.\n"
            + "\n"
            + "4. Duración y Terminación\n"
            + "\n"
            + "4.1 Esta licencia es efectiva a partir del momento en que el Usuario instala o utiliza la Aplicación y permanecerá en vigor hasta que el Usuario decida dejar de usarla o el Propietario decida rescindir la licencia.\n"
            + "\n"
            + "4.2 El Propietario se reserva el derecho de modificar los términos de este contrato en cualquier momento, notificando a los usuarios de los cambios pertinentes en la página web oficial.\n"
            + "\n"
            + "5. Ley Aplicable y Jurisdicción\n"
            + "\n"
            + "Este contrato se regirá e interpretará de acuerdo con las leyes españolas. Cualquier disputa que surja en relación con este contrato será sometida a los tribunales de España.\n"
            + "\n"
            + "6. Aceptación de los Términos\n"
            + "\n"
            + "Al utilizar la Aplicación, el Usuario declara que ha leído, entendido y aceptado los términos de este contrato en su totalidad.\n"
            + "\n"
            + "\n"
            + "Fecha de contrato: %s\n"
            + "Manuel Jesús Donoso Pérez\n"
            + "www.manueldonoso.es\n"
            + "dev@manueldonoso.es";

    /**
     * Genera el contrato personalizado con el nombre del usuario final y la
     * fecha.
     *
     * @return El contrato personalizado como un {@code String}.
     *
     * <p>
     * Ejemplo de contrato generado:
     * </p>
     * <pre>
     * Contrato de Licencia de Uso de Software
     * ...
     * Usuario final: Juan Pérez
     * Fecha de contrato: lunes 20 noviembre de 2024
     * </pre>
     */
    public static String getContrato() {
        return String.format(plantillaContrato, usuarioFinal, fecha);
    }

    /**
     * Configura el nombre del usuario final y la fecha del contrato.
     *
     * @param usuario El nombre del usuario final.
     * @param fechaContrato La fecha del contrato en formato de texto.
     *
     * <p>
     * Este método debe llamarse antes de generar el contrato mediante
     * {@link #getContrato()}.
     * </p>
     */
    public static void setUsuarioFinal(String usuario, String fechaContrato) {
        usuarioFinal = usuario;
        fecha = fechaContrato;
    }

    /**
     * Formatea una fecha en el formato "día de la semana día mes de año" (por
     * ejemplo: "martes 7 diciembre de 2024").
     *
     * @param fecha La fecha a formatear.
     * @return La fecha formateada como un {@code String}.
     *
     * <p>
     * Este método utiliza la configuración regional de España para generar el
     * texto en español.
     * </p>
     *
     * <p>
     * Ejemplo:
     * </p>
     * <pre>
     * Date fechaActual = new Date();
     * String fechaFormateada = Contrato.formatearFecha(fechaActual);
     * System.out.println(fechaFormateada); // "martes 7 diciembre de 2024"
     * </pre>
     */
    private static String formatearFecha(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("EEEE d MMMM 'de' yyyy", new Locale("es", "ES"));
        return formateador.format(fecha);
    }
}
