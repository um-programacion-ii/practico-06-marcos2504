package UM;
import UM.Dao.MedicoDao;
import UM.Dao.TurnoDao;
import UM.Services.GestionTurnoService;

public class Main {
    public static void main(String[] args) {
        TurnoDao turnoDao = TurnoDao.getInstance();
        MedicoDao medicoDao = MedicoDao.getInstance();
        GestionTurnoService gestionTurnoService = GestionTurnoService.getInstance(turnoDao,medicoDao);




        // Crear una especialidad
        Especialidad especialidad = new Especialidad("Cardiología");
        Especialidad especialidad2 = new Especialidad("Cardiologías");

        // Crear un médico
        Medico medico = new Medico("Juan", "Pérez", especialidad,Boolean.TRUE);
        Medico medico1 = new Medico("Juan", "Pérez", especialidad,Boolean.TRUE);
        medicoDao.agregarMedico(medico);
        medicoDao.agregarMedico(medico1);
        medicoDao.listarMedicos();

        // Crear un paciente
        Paciente paciente = new Paciente("Pedro", "Gómez");

        // Crear un GestionTurnoService


        // Simular la solicitud de un turno por parte del paciente
        gestionTurnoService.solicitarTurno(paciente, especialidad);
        medicoDao.listarMedicos();
    }
}
