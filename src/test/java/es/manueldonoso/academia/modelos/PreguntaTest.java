/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package es.manueldonoso.academia.modelos;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class PreguntaTest {

    public PreguntaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPregunta method, of class Pregunta.
     */
    @Test
    public void testPreguntaSolucionA() {
        System.out.println("respuesta prestablecida con solucion A");
        // Datos de prueba
        String preguntaTexto = "¿Cuál es la capital de Francia?";
        String respuestaCorrecta = "París";
        String respuestaIncorrecta1 = "Londres";
        String respuestaIncorrecta2 = "Madrid";
        String respuestaIncorrecta3 = "Berlín";
        List<Integer> orden = Arrays.asList(1, 2, 3, 4);

        // Crear instancia de Pregunta con un orden predefinido
        Pregunta pregunta = new Pregunta(orden, 1, preguntaTexto, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3);

        // Verificar que las respuestas están en el orden esperado
        assertEquals(respuestaCorrecta, pregunta.getRespuestaA());
        assertEquals(respuestaIncorrecta1, pregunta.getRespuestaB());
        assertEquals(respuestaIncorrecta2, pregunta.getRespuestaC());
        assertEquals(respuestaIncorrecta3, pregunta.getRespuestaD());

        // Verificar que la respuesta correcta está marcada
        assertTrue(pregunta.isSol_A());
        assertFalse(pregunta.isSol_B());
        assertFalse(pregunta.isSol_C());
        assertFalse(pregunta.isSol_D());

    }
    
    
     /**
     * Test of getPregunta method, of class Pregunta.
     */
    @RepeatedTest(10)
    public void testPreguntaSolucionB() {
        System.out.println("respuesta prestablecida con solucion B");
        // Datos de prueba
        String preguntaTexto = "¿Cuál es la capital de Francia?";
        String respuestaCorrecta = "París";
        String respuestaIncorrecta1 = "Londres";
        String respuestaIncorrecta2 = "Madrid";
        String respuestaIncorrecta3 = "Berlín";
        List<Integer> orden = Arrays.asList(2, 1, 3, 4);

        // Crear instancia de Pregunta con un orden predefinido
        Pregunta pregunta = new Pregunta(orden, 1, preguntaTexto, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3);

        // Verificar que las respuestas están en el orden esperado
        assertEquals(respuestaCorrecta, pregunta.getRespuestaB());
        assertEquals(respuestaIncorrecta1, pregunta.getRespuestaA());
        assertEquals(respuestaIncorrecta2, pregunta.getRespuestaC());
        assertEquals(respuestaIncorrecta3, pregunta.getRespuestaD());

        // Verificar que la respuesta correcta está marcada
        assertTrue(pregunta.isSol_B());
        assertFalse(pregunta.isSol_A());
        assertFalse(pregunta.isSol_C());
        assertFalse(pregunta.isSol_D());

    }


     /**
     * Test of getPregunta method, of class Pregunta.
     */
    @RepeatedTest(10)
    public void testPreguntaSolucionC() {
        System.out.println("respuesta prestablecida con solucion C");
        // Datos de prueba
        String preguntaTexto = "¿Cuál es la capital de Francia?";
        String respuestaCorrecta = "París";
        String respuestaIncorrecta1 = "Londres";
        String respuestaIncorrecta2 = "Madrid";
        String respuestaIncorrecta3 = "Berlín";
        List<Integer> orden = Arrays.asList(2, 3, 1, 4);

        // Crear instancia de Pregunta con un orden predefinido
        Pregunta pregunta = new Pregunta(orden, 1, preguntaTexto, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3);

        // Verificar que las respuestas están en el orden esperado
        assertEquals(respuestaCorrecta, pregunta.getRespuestaB());
        assertEquals(respuestaIncorrecta1, pregunta.getRespuestaC());
        assertEquals(respuestaIncorrecta2, pregunta.getRespuestaA());
        assertEquals(respuestaIncorrecta3, pregunta.getRespuestaD());

        // Verificar que la respuesta correcta está marcada
        assertTrue(pregunta.isSol_C());
        assertFalse(pregunta.isSol_A());
        assertFalse(pregunta.isSol_B());
        assertFalse(pregunta.isSol_D());

    }

     /**
     * Test of getPregunta method, of class Pregunta.
     */
    @RepeatedTest(10)
    public void testPreguntaSolucionD() {
        System.out.println("respuesta prestablecida con solucion D");
        // Datos de prueba
        String preguntaTexto = "¿Cuál es la capital de Francia?";
        String respuestaCorrecta = "París";
        String respuestaIncorrecta1 = "Londres";
        String respuestaIncorrecta2 = "Madrid";
        String respuestaIncorrecta3 = "Berlín";
        List<Integer> orden = Arrays.asList(2, 4, 3, 1);

        // Crear instancia de Pregunta con un orden predefinido
        Pregunta pregunta = new Pregunta(orden, 1, preguntaTexto, respuestaCorrecta, respuestaIncorrecta1, respuestaIncorrecta2, respuestaIncorrecta3);

        // Verificar que las respuestas están en el orden esperado
        assertEquals(respuestaCorrecta, pregunta.getRespuestaB());
        assertEquals(respuestaIncorrecta1, pregunta.getRespuestaD());
        assertEquals(respuestaIncorrecta2, pregunta.getRespuestaC());
        assertEquals(respuestaIncorrecta3, pregunta.getRespuestaA());

        // Verificar que la respuesta correcta está marcada
        assertTrue(pregunta.isSol_D());
        assertFalse(pregunta.isSol_A());
        assertFalse(pregunta.isSol_C());
        assertFalse(pregunta.isSol_B());

    }

}
