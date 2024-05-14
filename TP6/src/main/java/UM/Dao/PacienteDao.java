package UM.Dao;
import UM.Paciente;
import java.util.*;

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

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes.values());
    }

    public void agregarPaciente(Paciente paciente) {
        String id = "P" + proximoId++; // Generar un nuevo ID único
        pacientes.put(id, paciente);
        paciente.setId(id);
    }
}
