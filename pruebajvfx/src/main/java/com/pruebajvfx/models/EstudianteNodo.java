package com.pruebajvfx.models;

public class EstudianteNodo {
    private String codigo; 
    private String nombre; 
    private String carrera;
    private String proyecto;
    private Double promedio;
    private EstudianteNodo enlace;
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getProyecto() {
        return proyecto;
    }
    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
    public Double getPromedio() {
        return promedio;
    }
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
    public EstudianteNodo getEnlace() {
        return enlace;
    }
    public void setEnlace(EstudianteNodo enlace) {
        this.enlace = enlace;
    }
    public EstudianteNodo() {
    }
    public EstudianteNodo(String codigo, String nombre, String carrera, String proyecto, Double promedio,
            EstudianteNodo enlace) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carrera = carrera;
        this.proyecto = proyecto;
        this.promedio = promedio;
        this.enlace = enlace;
    }    
}
