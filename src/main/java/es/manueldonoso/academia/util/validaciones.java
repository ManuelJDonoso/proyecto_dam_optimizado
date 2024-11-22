/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

/**
 * Clase que contiene métodos estáticos para realizar validaciones comunes como
 * correos electrónicos, números de teléfono y textos con un mínimo de tres
 * letras.
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class validaciones {

    /**
     * Valida si un texto dado tiene el formato de un correo electrónico.
     * <p>
     * El formato válido se define mediante la siguiente expresión regular:
     * <pre>
     * ^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$
     * </pre> Donde:
     * <ul>
     * <li><code>^[\\w._%+-]+</code>: Representa el nombre del correo (puede
     * contener letras, números, guiones bajos, puntos, y algunos caracteres
     * especiales).</li>
     * <li><code>@</code>: Separa el nombre del dominio.</li>
     * <li><code>[\\w.-]+</code>: Representa el dominio (puede contener letras,
     * números, puntos y guiones).</li>
     * <li><code>\\.[a-zA-Z]{2,6}$</code>: Representa la extensión del dominio
     * (entre 2 y 6 letras, como "com", "org").</li>
     * </ul>
     *
     * @param texto el texto a validar como correo electrónico.
     * @return {@code true} si el texto cumple con el formato de un correo
     * electrónico válido; {@code false} en caso contrario.
     *
     * @throws NullPointerException si el texto es {@code null}.
     */
    public static boolean Email(String texto) {
        String patron = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return texto.matches(patron);
    }

    /**
     * Valida si un texto dado cumple con un formato específico de número de
     * teléfono.
     * <p>
     * El texto puede cumplir con uno de los siguientes formatos:
     * <ul>
     * <li><b>Secuencia opcional:</b> Comienza con <code>+</code> seguido de
     * exactamente dos dígitos.</li>
     * <li>Sin prefijo:
     * <ul>
     * <li>Nueve dígitos consecutivos (<code>\\d{9}</code>).</li>
     * <li>Tres dígitos seguidos de un guion <code>-</code>, luego dos dígitos,
     * otro guion <code>-</code>, dos dígitos más, otro guion <code>-</code>, y
     * finalmente dos dígitos (<code>\\d{3}-\\d{2}-\\d{2}-\\d{2}</code>).</li>
     * <li>Tres dígitos seguidos de un punto <code>.</code>, luego dos dígitos,
     * otro punto <code>.</code>, dos dígitos más, otro punto <code>.</code>, y
     * finalmente dos dígitos
     * (<code>\\d{3}\\.\\d{2}\\.\\d{2}\\.\\d{2}</code>).</li>
     * </ul>
     * </li>
     * <li>Con prefijo (<code>+XX</code>):
     * <ul>
     * <li>Formato con guion: <code>+XX-\\d{3}-\\d{2}-\\d{2}-\\d{2}</code>.</li>
     * <li>Formato con punto: <code>+XX.\\d{3}.\\d{2}.\\d{2}.\\d{2}</code>.</li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param texto el texto que se desea validar como número de teléfono.
     * @return {@code true} si el texto cumple con alguno de los formatos
     * válidos; {@code false} en caso contrario.
     * @throws NullPointerException si el texto es {@code null}.
     */
    public static boolean telefono(String texto) {
        String patron = "^(\\+\\d{2})?(\\d{9}|\\d{3}-\\d{2}-\\d{2}-\\d{2}|\\d{3}\\.\\d{2}\\.\\d{2}\\.\\d{2}|\\+\\d{2}-(\\d{3}-\\d{2}-\\d{2}-\\d{2})|\\+\\d{2}\\.(\\d{3}\\.\\d{2}\\.\\d{2}\\.\\d{2}))$";

        return texto.matches(patron);
    }

    /**
     * Valida si un texto cumple con los siguientes requisitos:
     * <ul>
     * <li>Comienza con exactamente tres letras consecutivas (mayúsculas o
     * minúsculas).</li>
     * <li>Puede contener espacios después de las tres letras iniciales.</li>
     * <li>No puede contener números ni otros caracteres especiales.</li>
     * </ul>
     *
     * @param texto el texto que se desea validar.
     * @return {@code true} si el texto cumple con los requisitos; {@code false}
     * en caso contrario.
     * @throws NullPointerException si el texto es {@code null}.
     */
    public static boolean min3letras(String texto) {
        String patron = "^[a-zA-Z]{3}[a-zA-Z ]*$";

        return texto.matches(patron);
    }
}
