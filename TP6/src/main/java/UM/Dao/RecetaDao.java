package UM.Dao;
import UM.Receta;
import UM.Turno;
import UM.Paciente;

import java.util.*;

public class RecetaDao {
    private static RecetaDao instance;
    private Map<String, Receta> recetas = new HashMap<>();
    private int proximoId = 1;


    private RecetaDao() {
    }

    public static synchronized RecetaDao getInstance() {
        if (instance == null) {
            instance = new RecetaDao();
        }
        return instance;
    }


    public void eliminarReceta(String id) {
        recetas.remove(id);
    }

    public Receta buscarReceta(String id) {
        return recetas.get(id);
    }

    public List<Receta> listarRecetas() {
        return new ArrayList<>(recetas.values());
    }

    public void agregarReceta(Receta receta) {
        String id = "R" + proximoId++; // Generar un nuevo ID único
        recetas.put(id, receta);
        receta.setId(id);


    }

    public void actualizarEstado(String id, String estado) {
        Receta receta = recetas.get(id);
        if (receta != null) {
            receta.setEstado(estado);
            recetas.put(id, receta);
        } else {
            System.out.println("No se encontró el médico con ID: " + id);
        }
    }

    public void agregarPaciente(String id, Paciente paciente) {
        Receta receta = recetas.get(id);
        if (receta != null) {
            receta.setPaciente(paciente);
            recetas.put(id, receta);
        } else {
            System.out.println("No se encontró el médico con ID: " + id);
        }
    }

    public List<Receta> ObtenerRecetasPendientes() {
        List<Receta> recetasPendientes = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getEstado().equalsIgnoreCase("Pendiente")) {
                recetasPendientes.add(receta);
            }
        }
        return recetasPendientes;

    }

    public List<Receta> ObtenerRecetasEnCurso() {
        List<Receta> recetasPendientes = new ArrayList<>();
        for (Receta receta : recetas.values()) {
            if (receta.getEstado().equalsIgnoreCase("En Curso")) {
                recetasPendientes.add(receta);
            }
        }
        return recetasPendientes;
    }
}

