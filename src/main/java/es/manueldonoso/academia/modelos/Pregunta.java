/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.modelos;

import static es.manueldonoso.academia.util.Acciones.generarNumeroAleatorio;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una pregunta con una respuesta correcta y tres respuestas incorrectas.
 * Las respuestas se asignan de forma aleatoria a cuatro opciones (A, B, C, D).
 * 
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Pregunta {

    private String pregunta, respuestaV, respuesta1, respuesta2, respuesta3;
    private String respuestaA, respuestaB, respuestaC, respuestaD;
    private boolean Sol_A = false, Sol_B = false, Sol_C = false, Sol_D = false;
    private List orden;

    /**
     * Crea una instancia de pregunta con los datos proporcionados.
     *
     * @param pregunta   el texto de la pregunta.
     * @param respuestaV la respuesta correcta.
     * @param respuesta1 la primera respuesta incorrecta.
     * @param respuesta2 la segunda respuesta incorrecta.
     * @param respuesta3 la tercera respuesta incorrecta.
     */
    public Pregunta(String pregunta, String respuestaV, String respuesta1, String respuesta2, String respuesta3) {
        this.pregunta = pregunta;
        this.respuestaV = respuestaV;
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        orden = ordenAleatorioRespuesta();
        asignarRepuesta((int) orden.get(0), respuestaV, Sol_A);
        asignarRepuesta((int) orden.get(1), respuesta1, Sol_B);
        asignarRepuesta((int) orden.get(2), respuesta2, Sol_C);
        asignarRepuesta((int) orden.get(3), respuesta3, Sol_D);
    }

    /**
     * Genera un orden aleatorio para asignar las respuestas a las opciones.
     *
     * @return una lista con el orden aleatorio de las respuestas.
     */
    private List ordenAleatorioRespuesta() {
        List orden = new ArrayList();
        int numeros = 0;
        int j;

        for (int i = 0; numeros < 4; i++) {
            j = generarNumeroAleatorio(1, 4);
            if (!orden.contains(j)) {
                orden.add(j);
            }
            numeros = orden.size();
        }

        return orden;

    }

    /**
     * Asigna una respuesta a una opción específica (A, B, C, D).
     *
     * @param i         el índice de la opción (1 para A, 2 para B, 3 para C, 4 para D).
     * @param respuesta el texto de la respuesta.
     * @param correcta  indica si la respuesta es correcta.
     */
    private void asignarRepuesta(int i, String respuesta, boolean correcta) {
        switch (i) {
            case 1:
                respuestaA = respuesta;
                correcta = true;
                break;
            case 2:
                respuestaB = respuesta;
                break;
            case 3:
                respuestaC = respuesta;
                break;
            case 4:
                respuestaD = respuesta;
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Devuelve el texto de la Pregunta.
     *
     * @return el texto de la Pregunta.
     */
    public String getPregunta() {
        return this.pregunta;
    }

    /**
     * Devuelve la opción A.
     *
     * @return el texto de la opción A.
     */
    public String getRespuestaA() {
        return this.respuestaA;
    }

    /**
     * Devuelve la opción B.
     *
     * @return el texto de la opción B.
     */
    public String getRespuestaB() {
        return this.respuestaB;
    }

    /**
     * Devuelve la opción C.
     *
     * @return el texto de la opción C.
     */
    public String getRespuestaC() {
        return this.respuestaC;
    }

    /**
     * Devuelve la opción D.
     *
     * @return el texto de la opción D.
     */
    public String getRespuestaD() {
        return this.respuestaD;
    }

    /**
     * Verifica si la opción A es la respuesta correcta.
     *
     * @return true si la opción A es correcta, false en caso contrario.
     */
    public boolean isSol_A() {
        return Sol_A;
    }

    /**
     * Verifica si la opción B es la respuesta correcta.
     *
     * @return true si la opción B es correcta, false en caso contrario.
     */
    public boolean isSol_B() {
        return Sol_B;
    }

    /**
     * Verifica si la opción C es la respuesta correcta.
     *
     * @return true si la opción C es correcta, false en caso contrario.
     */
    public boolean isSol_C() {
        return Sol_C;
    }

    /**
     * Verifica si la opción D es la respuesta correcta.
     *
     * @return true si la opción D es correcta, false en caso contrario.
     */
    public boolean isSol_D() {
        return Sol_D;
    }

    /**
     * Devuelve una representación en formato texto de la Pregunta y sus respuestas.
     *
     * @return una cadena que representa la Pregunta y sus datos.
     */
    @Override
    public String toString() {
        return "pregunta{" + "pregunta=" + pregunta + ", \n respuestaV=" + respuestaV
                + ", \n respuesta1=" + respuesta1 + ", \n respuesta2=" + respuesta2
                + ", \n respuesta3=" + respuesta3 + ", \n respuestaA=" + respuestaA
                + ", \n respuestaB=" + respuestaB + ",\n  respuestaC=" + respuestaC
                + ", \n respuestaD=" + respuestaD + ",\n  Sol_A=" + Sol_A
                + ", \n Sol_B=" + Sol_B + ",\n  Sol_C=" + Sol_C + ", \n Sol_D=" + Sol_D + "}";
    }

}
