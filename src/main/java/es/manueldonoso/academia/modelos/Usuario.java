/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.modelos;

import java.sql.Date;

/**
 * Representa un usuario en el sistema con información básica de contacto y autenticación.
 * 
 * La clase `Usuario` contiene los datos de un usuario, incluyendo su nombre, apellidos,
 * dirección, teléfono, contraseña, tipo de usuario, una foto en formato de arreglo de bytes,
 * y fechas de alta y baja en el sistema.
 * El tipo de usuario está limitado a los valores "ADMINISTRADOR", "PROFESOR" o "ALUMNO".
 * 
 * @author Manuel Jesús Donoso Pérez
 */
public class Usuario {
    private String Usuario, Nombre, Apellidos, Direccion, Telefono, Pass,Email;
    private TipoUsuario Tipo;
    private byte[] Foto;
    private Date FechaAlta,FechaBaja;

    /**
     * Enum que define los tipos de usuario permitidos en el sistema.
     */
    public enum TipoUsuario {
        ADMINISTRADOR,
        PROFESOR,
        ALUMNO
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return Usuario;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param Usuario El nombre de usuario a asignar.
     */
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param Nombre El nombre a asignar al usuario.
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     * 
     * @return Los apellidos del usuario.
     */
    public String getApellidos() {
        return Apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     * 
     * @param Apellidos Los apellidos a asignar al usuario.
     */
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    /**
     * Obtiene la dirección del usuario.
     * 
     * @return La dirección del usuario.
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * Establece la dirección del usuario.
     * 
     * @param Direccion La dirección a asignar al usuario.
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     * 
     * @return El número de teléfono del usuario.
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * 
     * @param Telefono El número de teléfono a asignar al usuario.
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return Pass;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param Pass La contraseña a asignar al usuario.
     */
    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    /**
     * Obtiene el tipo de usuario.
     * 
     * @return El tipo de usuario como uno de los valores de {@link TipoUsuario}.
     */
    public TipoUsuario getTipo() {
        return Tipo;
    }

    /**
     * Establece el tipo de usuario.
     * 
     * @param Tipo El tipo de usuario, que debe ser uno de los valores de {@link TipoUsuario}.
     */
    public void setTipo(TipoUsuario Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * Obtiene la foto del usuario.
     * 
     * @return La foto del usuario en formato de arreglo de bytes.
     */
    public byte[] getFoto() {
        return Foto;
    }

    /**
     * Establece la foto del usuario.
     * 
     * @param Foto La foto a asignar al usuario en formato de arreglo de bytes.
     */
    public void setFoto(byte[] Foto) {
        this.Foto = Foto;
    }

 /**
     * Obtiene la fecha de alta del usuario en el sistema.
     * 
     * @return La fecha de alta del usuario.
     */
    public Date getFechaAlta() {
        return FechaAlta;
    }

    /**
     * Establece la fecha de alta del usuario en el sistema.
     * 
     * @param FechaAlta La fecha de alta a asignar.
     */
    public void setFechaAlta(Date FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    /**
     * Obtiene la fecha de baja del usuario en el sistema.
     * 
     * @return La fecha de baja del usuario.
     */
    public Date getFechaBaja() {
        return FechaBaja;
    }

    /**
     * Establece la fecha de baja del usuario en el sistema.
     * 
     * @param FechaBaja La fecha de baja a asignar.
     */
    public void setFechaBaja(Date FechaBaja) {
        this.FechaBaja = FechaBaja;
    }
/**
 * Obtiene el Email del usuario.
 * 
 * @return 
 */
    public String getEmail() {
        return Email;
    }
    
    /**
     * Establece el Email del usuario.
     * 
     * @param Email Establece el Email del usuario.
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}