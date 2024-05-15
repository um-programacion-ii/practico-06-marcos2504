package UM.Dao;
import UM.Medico;
import UM.Paciente;
import java.util.*;
import UM.Receta;

public class PacienteDao {
    private static PacienteDao instance;
    private Map<String, Paciente> pacientes = new HashMap<>();
    private int proximoId = 1;
    private PacienteDao() {}
    public static synchronized PacienteDao getInstance() {
        if (instance == null) {
            instance = new PacienteDao();
        }
        return instance;
    }



    public void eliminarPaciente(String id) {
        pacientes.remove(id);
    }

    public Paciente buscarPaciente(String id) {
        return pacientes.get(id);
    }

    public List<Paciente> listarpacientesConRecetas() {
        System.out.println("Lista de pacientes:");
        List<Paciente> pacientesConRecetas = new ArrayList<>();
        for (Paciente paciente : pacientes.values()) {
            if (!paciente.getRecetas().isEmpty()) {
                pacientesConRecetas.add(paciente);
            }
        }
        for (Paciente paciente : pacientesConRecetas) {
            System.out.println(" Nombre: " + paciente.getNombre() +
                    ", Apellido: " + paciente.getApellido() +
                    " , Disponible" + paciente.isDisponible() );
            paciente.mostrarRecetas();


        }
        return pacientesConRecetas;
    }
    public void agregarPaciente(Paciente paciente) {
        String id = "P" + proximoId++; // Generar un nuevo ID único
        pacientes.put(id, paciente);
        paciente.setId(id);
    }
    public void actualizarDisponible (String id,boolean disponible){
        Paciente paciente = pacientes.get(id);
        if (paciente != null) {
            paciente.setDisponible(disponible);
            pacientes.put(id, paciente);
        } else {
            System.out.println("No se encontró el médico con ID: " + id);
        }
    }
    public void agregarReceta(String idPaciente, Receta receta) {
        Paciente paciente = pacientes.get(idPaciente);
        if (paciente != null) {
            paciente.agregarReceta(receta); // Agregar la receta al paciente
            pacientes.put(idPaciente, paciente); // Actualizar la entrada en el mapa
        } else {
            System.out.println("No se encontró el paciente con ID: " + idPaciente);
        }
    }


}

