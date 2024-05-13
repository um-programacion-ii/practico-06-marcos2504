package UM.Services;
import UM.Paciente;
import UM.Medico;
import UM.Especialidad;
import java.util.List;
import java.util.Random;
import java.util.Map;
import UM.Turno;
import UM.Dao.TurnoDao;
import UM.Dao.MedicoDao;


public class GestionTurnoService {
    private static GestionTurnoService instance;
    private TurnoDao turnoDao;
    private MedicoDao medicoDao;

    // Constructor privado para evitar la instanciación directa
    private GestionTurnoService(TurnoDao turnoDao, MedicoDao medicoDao) {
        this.turnoDao = turnoDao;
        this.medicoDao = medicoDao;
    }

    // Método estático para obtener la instancia única de GestionTurnoService
    public static synchronized GestionTurnoService getInstance(TurnoDao turnoDAO, MedicoDao medicoDao) {
        if (instance == null) {
            instance = new GestionTurnoService(turnoDAO, medicoDao);
        }
        return instance;
    }



    public void solicitarTurno(Paciente paciente, Especialidad especialidad) {
        // Obtener un mapa de todos los médicos junto con sus IDs
        Map<String, Medico> medicosConIds = medicoDao.listarMedicosConIds();

        // Iterar sobre el mapa y buscar un médico disponible con la especialidad deseada
        for (Map.Entry<String, Medico> entry : medicosConIds.entrySet()) {
            String id = entry.getKey();
            Medico medico = entry.getValue();

            if (medico.getEspecialidad().equals(especialidad) && medico.isDisponible()) {
                medicoDao.actualizarDisponibilidadMedico(id, false);
                Turno turno = new Turno(paciente,medico);
                turnoDao.agregarTurno(turno);
                // Aquí puedes usar el ID del médico para realizar otras operaciones, como iniciar un turno, etc.
                System.out.println("Turno solicitado con éxito para el paciente " + paciente.getNombre() +
                        ". Médico asignado: " + medico.getNombre() + ". ID del médico: " + id);
                return;
            }
        }

        System.out.println("Lo sentimos, no hay médicos disponibles para la especialidad " +
                especialidad.getNombre() + " en este momento.");
    }


}

