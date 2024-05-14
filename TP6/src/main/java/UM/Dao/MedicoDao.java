package UM.Dao;

import UM.Medico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicoDao {
    private static MedicoDao instance;
    private Map<String, Medico> medicos = new HashMap<>();
    private int proximoId = 1;

    // Constructor privado para evitar la instanciación directa
    private MedicoDao() {
    }

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

    public List<Medico> listarMedicos(){
        System.out.println("Lista de médicos:");
        List<Medico> listaMedicos = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            listaMedicos.add(medico);


            System.out.println(" Nombre: " + medico.getNombre() +
                    ", Apellido: " + medico.getApellido() + ", Especialidad: " + medico.getEspecialidad().getNombre() +
                    " , Turnos" + medico.getContadorTurnos());
        }
        return listaMedicos;
    }

    public List<Medico> listarMedicosObraSocial() {
        System.out.println("Lista de médicos:");
        List<Medico> medicosConObraSocial = new ArrayList<>();
        for (Medico medico : medicos.values()) {
            if (!medico.getObrasocial().isEmpty()) {
                medicosConObraSocial.add(medico);
            }
        }
        for (Medico medico : medicosConObraSocial) {
            System.out.println(" Nombre: " + medico.getNombre() +
                    ", Apellido: " + medico.getApellido() + ", Especialidad: " + medico.getEspecialidad().getNombre() +
                    " , Turnos" + medico.getContadorTurnos() + ", Obras Sociales: " + medico.getObrasocial());


            }


        return medicosConObraSocial;
    }



        public void agregarMedico (Medico medico){
            String id = "M" + proximoId++;
            medicos.put(id, medico);
            medico.setId(id);
        }
        public void incrementarContadorTurnos (String idMedico){
            Medico medico = medicos.get(idMedico);
            if (medico != null) {
                medico.incrementarContadorTurnos();
            } else {
                System.out.println("No se encontró el médico con ID: " + idMedico);
            }
        }
        public void actualizarDisponibilidadMedico (String id,boolean disponible){
            Medico medico = medicos.get(id);
            if (medico != null) {
                medico.setDisponible(disponible);
                medicos.put(id, medico);
            } else {
                System.out.println("No se encontró el médico con ID: " + id);
            }
        }
        public List<Medico> listarMedicosDisponibles () {
            List<Medico> medicosDisponibles = new ArrayList<>();
            for (Medico medico : medicos.values()) {
                if (medico.isDisponible()) {
                    medicosDisponibles.add(medico);
                }
            }
            return medicosDisponibles;
        }
        public Map<String, Medico> listarMedicosConIds () {
            return medicos; // Devuelve el mapa de médicos directamente
        }

    }




