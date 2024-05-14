package UM;

import java.util.List;

public class Paciente {
    private String id;
    private String nombre;
    private String apellido;
    private boolean conObraSocial;

    private  ObraSocial obraSocial;

    public Paciente(String nombre, String apellido,boolean conObraSocial,ObraSocial obraSocial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.conObraSocial= conObraSocial;
        this.obraSocial= conObraSocial ? obraSocial : null;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isConObraSocial() {
        return conObraSocial;
    }

    public String getObraSocial() {
        String O = obraSocial.getNombre();
        return O;
    }
}
