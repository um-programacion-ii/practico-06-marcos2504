package UM.Dao;

import UM.Medico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.*;

public class MedicoDao {
    private static MedicoDao instance;
    private Map<String, Medico> medicos = new HashMap<>();
    private int proximoId = 1;

    // Constructor privado para evitar la instanciación directa
    private MedicoDao() {}

    // Método estático para obtener la instancia única de MedicoDao
    public static synchronized MedicoDao getInstance() {
        if (instance == null) {
            instance = new MedicoDao();
        }
        return instance;
    }

    public void eliminarMedico(String id) {
        medicos.remove(id);
    }

    public Medico buscarMedico(String id) {
        return medicos.get(id);
    }

    public void listarMedicos() {
        System.out.println("Lista de médicos:");
        for (Medico medico : medicos.values()) {
            System.out.println(" Nombre: " + medico.getNombre() +
                    ", Apellido: " + medico.getApellido() + ", Especialidad: " + medico.getEspecialidad().getNombre() +
                    ", Disponible: " + medico.isDisponible());
        }
    }

    public void agregarMedico(Medico medico) {
        String id = "M" + proximoId++;
        medicos.put(id, medico);
    }

    public void actualizarDisponibilidadMedico(String id, boolean disponible) {
        Medico medico = medicos.get(id);
        if (medico != null) {
            medico.setDisponible(disponible);
            medicos.put(id, medico);
        } else {
            System.out.println("No se encontró el médico con ID: " + id);
        }
    }
    public Map<String, Medico> listarMedicosConIds() {
        return medicos; // Devuelve el mapa de médicos directamente
    }

}



