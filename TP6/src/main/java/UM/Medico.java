package UM;
import java.util.List;
import java.util.ArrayList;

public class Medico {
    private String id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private boolean disponible;
    private int contadorTurnos;
    private List<ObraSocial> obrasocial;

    public Medico(String nombre, String apellido, Especialidad especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.disponible = true;
        this.obrasocial = new ArrayList<>();


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void incrementarContadorTurnos() {
        contadorTurnos++;
    }
    public void decrementarContadorTurnos() {
        contadorTurnos--;
    }

    public int getContadorTurnos() {
        return contadorTurnos;
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

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void agregarObraSocial(ObraSocial obra) {
        obrasocial.add(obra);
    }
    public List<String> getObrasocial() {
        List<String> nombresObrasSociales = new ArrayList<>();
        for (ObraSocial obraSocial : obrasocial) {
            nombresObrasSociales.add(obraSocial.getNombre());
        }
        return nombresObrasSociales;
    }

}
