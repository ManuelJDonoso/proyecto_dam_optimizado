/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.modelos;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class examen {
    private String examen;
    private Double nota;

    public examen(String examen, Double nota) {
        this.examen = examen;
        this.nota = nota;
    }

    public examen() {
    }

  

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "examen{" + "examen=" + examen + ", nota=" + nota + '}';
    }
    
    
    
}
