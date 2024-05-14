package UM;
import UM.Dao.MedicoDao;
import UM.Dao.TurnoDao;
import UM.Services.GestionTurnoService;
import UM.Dao.ObraSocialDao;

public class Main {
    public static void main(String[] args) {
        TurnoDao turnoDao = TurnoDao.getInstance();
        MedicoDao medicoDao = MedicoDao.getInstance();
        GestionTurnoService gestionTurnoService = GestionTurnoService.getInstance(turnoDao,medicoDao);
        ObraSocialDao obraSocialDao = ObraSocialDao.getInstance();
        ObraSocial obraSocial= new ObraSocial("Damsu");
        obraSocialDao.agregar(obraSocial);
        ObraSocial obraSocial2= new ObraSocial("Osde");
        obraSocialDao.agregar(obraSocial);

        // Crear una especialidad
        Especialidad especialidad = new Especialidad("Cardiología");
        Especialidad especialidad2 = new Especialidad("Cardiologías");


        // Crear un médico
        Medico medico = new Medico("Juan", "Pérez", especialidad,true);
        Medico medico1 = new Medico("Juanito", "Pérez", especialidad,true);
        Medico medico3 = new Medico("Juanito", "Pérez", especialidad2,true);
        medico.agregarObraSocial(obraSocial);
        medicoDao.agregarMedico(medico);
        medicoDao.agregarMedico(medico1);
        medicoDao.agregarMedico(medico3);

        medicoDao.listarMedicos();

        // Crear un paciente
        Paciente paciente = new Paciente("Pedro", "Gómez",false,null);

        // Crear un GestionTurnoService


        // Simular la solicitud de un turno por parte del paciente
        gestionTurnoService.solicitarTurno(paciente, especialidad);
        turnoDao.listarTurnoss();
        gestionTurnoService.solicitarTurno(paciente, especialidad);
        turnoDao.listarTurnoss();


        medicoDao.listarMedicos();
    }
}
