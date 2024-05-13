package UM.Dao;
import java.util.*;
import UM.Especialidad;


public class EspecialidadDao {
    private Map<String, Especialidad> especialidades = new HashMap<>();
    private int proximoId = 1;


    public void eliminarEspecialidad(String id) {
        especialidades.remove(id);
    }

    public Especialidad buscar(String id) {
        return especialidades.get(id);
    }
    public List<Especialidad> listar() {
        return new ArrayList<>(especialidades.values());
    }
    public void agregar(Especialidad especialidad) {
        String id = "E" + proximoId++; // Generar un nuevo ID único
        especialidades.put(id, especialidad);}
}
