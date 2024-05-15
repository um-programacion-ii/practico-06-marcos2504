package UM.Dao;
import UM.Medico;
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
        turno.setId(id);
    }
    public void listarTurnoss() {
        System.out.println("Lista de Turnos:");
        for (Turno turno : turnos.values()) {
            System.out.println(" Medico: " + turno.getMedico().getNombre() +
                    ", Paciente: " + turno.getPaciente().getNombre() + ", Con Obra social: " + turno.getPaciente().isConObraSocial() + ", Estado: "+ turno.getEstado() );
        }
    }
    public List<Turno> ObtenerTurnosPendientes(){
        List<Turno> turnosPendientes = new ArrayList<>();
        for (Turno turno: turnos.values()){
            if (turno.getEstado().equalsIgnoreCase("Pendiente")){
                turnosPendientes.add(turno);
            }
        }
        return turnosPendientes;

    }
    public void actualizarEstado (String id,String estado ){
        Turno turno = turnos.get(id);
        if (turno != null) {
            turno.setEstado(estado);
            turnos.put(id, turno);
        } else {
            System.out.println("No se encontró el médico con ID: " + id);
        }
    }
    public List<Turno> ObtenerTurnosEnCurso(){
        List<Turno> turnosEnCurso = new ArrayList<>();
        for (Turno turno: turnos.values()){
            if (turno.getEstado().equals("En Curso")){
                turnosEnCurso.add(turno);
            }
        }
        return turnosEnCurso;

    }
}
