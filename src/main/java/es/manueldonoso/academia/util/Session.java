/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

/**
 * Clase que gestiona una sesión con información de usuario y contraseña.
 * Proporciona métodos estáticos para establecer y obtener los valores de
 * usuario y contraseña.
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Session {

    /**
     * Nombre de usuario de la sesión actual.
     */
    private static String Usuario;

    /**
     * puntuacion para los examentes
     */
    private static int Puntuacion;
   
    /**
     * total pregunta examenes
     */
     public static int totalPregunstas;
    /**
     * Contraseña del usuario de la sesión actual.
     */
    private static String pass;

    /**
     * Obtiene el nombre de usuario de la sesión actual.
     *
     * @return El nombre de usuario como un {@code String}.
     */
    public static String getUsuario() {
        return Usuario;
    }

    /**
     * Establece el nombre de usuario para la sesión actual.
     *
     * @param Usuario El nombre de usuario como un {@code String}.
     */
    public static void setUsuario(String Usuario) {
        Session.Usuario = Usuario;
    }

    /**
     * Obtiene la contraseña del usuario de la sesión actual.
     *
     * @return La contraseña como un {@code String}.
     */
    public static String getPass() {
        return pass;
    }

    /**
     * Establece la contraseña del usuario para la sesión actual.
     *
     * @param pass La contraseña como un {@code String}.
     */
    public static void setPass(String pass) {
        Session.pass = pass;
    }

    /**
     *devuelve la puntuacion del examen que esta realizando el usuario en esta session 
     * @return Nota
     */
    public static int getPuntuacion() {
        return Puntuacion;
    }

   
    /**
     *establece la puntuacion del examen que esta realizando el usuario en esta session 
     * @param Puntuacion Nota
     */
    public static void setPuntuacion(int Puntuacion) {
        Session.Puntuacion = Puntuacion;
    }
    /**
     * altera el valor del resultado de la nota sin borrar el dato anterior.
     * @param modifificar  valor para añadir o restar.
     */
    public static void sumar_Punto(int modifificar){
        Puntuacion=Puntuacion+modifificar;
    }

    public static int getTotalPregunstas() {
        return totalPregunstas;
    }

    public static void setTotalPregunstas(int totalPregunstas) {
        Session.totalPregunstas = totalPregunstas;
    }
    
   

}
