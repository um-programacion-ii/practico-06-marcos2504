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
        // Obtener una lista de todos los médicos disponibles
        if (paciente.isConObraSocial()) {
            List<Medico> medicosObraSocial = medicoDao.listarMedicosObraSocial();
            boolean turnoAsignado = false;
            // Iterar sobre la lista y buscar un médico con la especialidad deseada
            for (Medico medico : medicosObraSocial) {
                if (medico.getEspecialidad().equals(especialidad) &&  medico.getObrasocial().contains(paciente.getObraSocial())) {
                    medicoDao.incrementarContadorTurnos(medico.getId());

                    // Crear un nuevo turno con el paciente y el médico
                    Turno turno = new Turno(paciente, medico, paciente.isConObraSocial());

                    // Agregar el turno a la base de datos
                    turnoDao.agregarTurno(turno);

                    // Imprimir el mensaje de éxito con el nombre del paciente y médico asignado
                    System.out.println("Turno solicitado con éxito para el paciente " + paciente.getNombre() +
                            ". Médico asignado: " + medico.getNombre());
                    turnoAsignado = true;
                    break; // Salir del bucle una vez que se haya asignado el turno
                }
            }
            if (!turnoAsignado) {
                System.out.println("Lo sentimos, no hay médicos disponibles para la especialidad que reciban esa obra social " +
                        especialidad.getNombre() + " ");
            }
        }
        else {
            List<Medico> medicosObraSocial = medicoDao.listarMedicos();
            boolean turnoAsignado = false;
            // Iterar sobre la lista y buscar un médico con la especialidad deseada
            for (Medico medico : medicosObraSocial) {
                if (medico.getEspecialidad().equals(especialidad)) {
                    medicoDao.incrementarContadorTurnos(medico.getId());

                    // Crear un nuevo turno con el paciente y el médico
                    Turno turno = new Turno(paciente, medico, paciente.isConObraSocial());

                    // Agregar el turno a la base de datos
                    turnoDao.agregarTurno(turno);

                    // Imprimir el mensaje de éxito con el nombre del paciente y médico asignado
                    System.out.println("Turno solicitado con éxito para el paciente " + paciente.getNombre() +
                            ". Médico asignado: " + medico.getNombre());
                    turnoAsignado = true;
                    break; // Salir del bucle una vez que se haya asignado el turno
                }
            }
            if (!turnoAsignado) {
                System.out.println("Lo sentimos, no hay médicos disponibles para la especialidad  " +
                        especialidad.getNombre() + " ");
            }
        }
    }
}
