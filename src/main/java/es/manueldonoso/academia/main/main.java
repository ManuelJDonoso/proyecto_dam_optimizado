/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.manueldonoso.academia.main;

import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Stage_show;
import es.manueldonoso.academia.util.Base_datos;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class main extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          launch(args); // Lanza la aplicación JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Iniciando el programa");
        Stage_show.Mostrar_login(); // Aquí puedes mostrar la ventana de inicio de sesión
        
       
        
       // System.out.println(Base_datos.isConnection(Base_datos.conectarSqlite()));
       //   System.out.println(Base_datos.isConnection(Base_datos.conectarSqlite()));
    }
    
}
