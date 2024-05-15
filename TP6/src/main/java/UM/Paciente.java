package UM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import UM.Medicamento;


public class Paciente {
    private String id;
    private String nombre;
    private String apellido;
    private List<Receta> recetas;
    private boolean disponible;
    private boolean conObraSocial;

    private ObraSocial obraSocial;

    public Paciente(String nombre, String apellido, boolean conObraSocial, ObraSocial obraSocial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.conObraSocial = conObraSocial;
        this.obraSocial = conObraSocial ? obraSocial : null;
        this.recetas = new ArrayList<>();
        this.disponible = true;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void agregarReceta(Receta receta) {
        recetas.add(receta);
    }

    public void mostrarRecetas() {
        for (Receta receta : recetas) {
            if (receta != null) { // Verifica si la receta no es nula
                System.out.println("Receta:");
                Map<Medicamento, Integer> medicamentos = receta.getMedicamentos();
                System.out.println("Estado: "+ receta.getEstado());
                for (Map.Entry<Medicamento, Integer> entry : medicamentos.entrySet()) {
                    Medicamento medicamento = entry.getKey();
                    int cantidad = entry.getValue();
                    System.out.println("Medicamento: " + medicamento.getNombre() + ", Cantidad: " + cantidad);
                }
                System.out.println(); // Separador entre recetas
            }

        }
    }
    public List<Receta> getRecetas () {
            return recetas;
        }
    }
