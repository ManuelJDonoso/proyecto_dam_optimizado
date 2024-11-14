/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Session {

    private static String Usuario;
    private static String pass;

    public static String getUsuario() {
        return Usuario;
    }

    public static void setUsuario(String Usuario) {
        Session.Usuario = Usuario;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        Session.pass = pass;
    }

}
