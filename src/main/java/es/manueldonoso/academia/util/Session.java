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

}
