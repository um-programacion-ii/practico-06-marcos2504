package UM;

public class Especialidad {
    private String id;
    private String nombre;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

