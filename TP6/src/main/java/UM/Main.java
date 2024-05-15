package UM;
import UM.Dao.*;
import UM.Services.AtencionMedicoService;
import UM.Services.GestionTurnoService;

public class Main {
    public static void main(String[] args) {
        TurnoDao turnoDao = TurnoDao.getInstance();
        MedicoDao medicoDao = MedicoDao.getInstance();
        PacienteDao pacienteDao = PacienteDao.getInstance();
        RecetaDao recetaDao=RecetaDao.getInstance();
        GestionTurnoService gestionTurnoService = GestionTurnoService.getInstance(turnoDao,medicoDao);
        AtencionMedicoService atencionMedicoService = AtencionMedicoService.getInstance(medicoDao,turnoDao,pacienteDao,recetaDao);
        ObraSocialDao obraSocialDao = ObraSocialDao.getInstance();
        ObraSocial obraSocial= new ObraSocial("Damsu");
        obraSocialDao.agregar(obraSocial);
        ObraSocial obraSocial2= new ObraSocial("Osde");
        obraSocialDao.agregar(obraSocial);

        // Crear una especialidad
        Especialidad especialidad = new Especialidad("Cardiología");
        Especialidad especialidad2 = new Especialidad("Cardiologías");


        // Crear un médico
        Medico medico = new Medico("Juan", "Pérez", especialidad);
        Medico medico1 = new Medico("Juanito", "Pérez", especialidad2);
        Medico medico3 = new Medico("Juanito", "Pérez", especialidad2);
        medico.agregarObraSocial(obraSocial);
        medicoDao.agregarMedico(medico);
        medicoDao.agregarMedico(medico1);
        medicoDao.agregarMedico(medico3);

        medicoDao.listarMedicos();

        // Crear un paciente
        Paciente paciente = new Paciente("Pedro", "Gómez",false,null);
        pacienteDao.agregarPaciente(paciente);
        Paciente paciente1 = new Paciente("Pedrito", "Gómez",false,null);
        pacienteDao.agregarPaciente(paciente1);
        // Crear un GestionTurnoService


        // Simular la solicitud de un turno por parte del paciente
        gestionTurnoService.solicitarTurno(paciente, especialidad);
        turnoDao.listarTurnoss();
        gestionTurnoService.solicitarTurno(paciente, especialidad);
        gestionTurnoService.solicitarTurno(paciente1, especialidad2);
        turnoDao.listarTurnoss();


        medicoDao.listarMedicos();
        atencionMedicoService.EmpezarTurnos();
        medicoDao.listarMedicos();
        turnoDao.listarTurnoss();
        atencionMedicoService.EmpezarTurnos();
        medicoDao.listarMedicos();
        turnoDao.listarTurnoss();
        atencionMedicoService.FinalizarTurnos();
        medicoDao.listarMedicos();
        turnoDao.listarTurnoss();
        atencionMedicoService.EmpezarTurnos();
        medicoDao.listarMedicos();
        turnoDao.listarTurnoss();
        atencionMedicoService.FinalizarTurnos();
        pacienteDao.listarpacientesConRecetas();


    }
}
