package UM.Dao;
import  UM.Turno;
import java.util.*;

public class TurnoDao {
    private static TurnoDao instance;
    private Map<String, Turno> turnos = new HashMap<>();
    private int proximoId = 1;
    private TurnoDao() {}

    // Método estático para obtener la instancia única de MedicoDao
    public static synchronized TurnoDao getInstance() {
        if (instance == null) {
            instance = new TurnoDao();
        }
        return instance;
    }

    // Otros métodos de la clase

    public void eliminarTurno(String id) {
        turnos.remove(id);
    }

    public Turno buscarTurno(String id) {
        return turnos.get(id);
    }

    public List<Turno> listarTurnos() {
        return new ArrayList<>(turnos.values());
    }

    public void agregarTurno(Turno turno) {
        String id = "T" + proximoId++; // Generar un nuevo ID único
        turnos.put(id, turno);
    }
}
