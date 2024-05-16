package TestServices;

import UM.*;
import UM.Dao.*;
import UM.Services.AtencionMedicoService;
import UM.Services.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAtencionMedicoService {
    private MedicoDao medicoDao;
    private RecetaDao recetaDao;
    private PacienteDao pacienteDao;
    private  TurnoDao turnoDao;
    private AtencionMedicoService atencionMedicoService;
    private GestionTurnoService gestionTurnoService;
    private ObraSocialDao obraSocial;
    @BeforeEach
    void SetUp(){
        turnoDao = TurnoDao.getInstance();
        medicoDao = MedicoDao.getInstance();
        pacienteDao = PacienteDao.getInstance();
        recetaDao=RecetaDao.getInstance();
        obraSocial=ObraSocialDao.getInstance();
        atencionMedicoService = AtencionMedicoService.getInstance(medicoDao,turnoDao,pacienteDao,recetaDao);
        gestionTurnoService= GestionTurnoService.getInstance(turnoDao,medicoDao);

    }
    @Test
    void empezar_turnos_con_medicos_disponibles(){
        Especialidad especialidad = new Especialidad("Cardiología");
        Especialidad especialidad2 = new Especialidad("Cardiologías");
        Medico medico = new Medico("Juan", "Pérez", especialidad);
        Medico medico1 = new Medico("Juanito", "Pérez", especialidad2);
        medicoDao.agregarMedico(medico);
        medicoDao.agregarMedico(medico1);
        Paciente paciente = new Paciente("Pedro", "Gómez",false,null);
        pacienteDao.agregarPaciente(paciente);
        Paciente paciente1 = new Paciente("Pedrito", "Gómez",false,null);
        pacienteDao.agregarPaciente(paciente1);
        Turno turno=new Turno(paciente,medico,false);
        Turno turno1=new Turno(paciente1,medico1,false);
        turnoDao.agregarTurno(turno);
        turnoDao.agregarTurno(turno1);
        assertTrue(medico.isDisponible());
        assertTrue(medico1.isDisponible());
        assertTrue(paciente.isDisponible());
        assertTrue(paciente1.isDisponible());
        assertEquals("Pendiente",turno.getEstado());
        atencionMedicoService.EmpezarTurnos();
        assertFalse(medico.isDisponible());
        assertFalse(paciente.isDisponible());
        assertEquals("En Curso",turno.getEstado());
        assertFalse(medico1.isDisponible());
        assertFalse(paciente1.isDisponible());
        assertEquals("En Curso",turno1.getEstado());
    }

    @Test
    void finalizar_turnos_con_medicos_disponibles(){
        Especialidad especialidad = new Especialidad("Cardiología");
        Especialidad especialidad2 = new Especialidad("Cardiologías");
        Medico medico = new Medico("Juan", "Pérez", especialidad);
        medico.setDisponible(false);
        Medico medico1 = new Medico("Juanito", "Pérez", especialidad2);
        medico1.setDisponible(false);
        medicoDao.agregarMedico(medico);
        medicoDao.agregarMedico(medico1);
        Paciente paciente = new Paciente("Pedro", "Gómez",false,null);
        paciente.setDisponible(false);
        pacienteDao.agregarPaciente(paciente);
        Paciente paciente1 = new Paciente("Pedrito", "Gómez",false,null);
        paciente1.setDisponible(false);
        pacienteDao.agregarPaciente(paciente1);
        Turno turno=new Turno(paciente,medico,false);
        turno.setEstado("En Curso");
        Turno turno1=new Turno(paciente1,medico1,false);
        turno1.setEstado("En Curso");
        turnoDao.agregarTurno(turno);
        turnoDao.agregarTurno(turno1);
        atencionMedicoService.FinalizarTurnos();
        assertTrue(medico.isDisponible());
        assertTrue(paciente.isDisponible());
        assertEquals("Finalizado",turno.getEstado());
        assertTrue(medico1.isDisponible());
        assertTrue(paciente1.isDisponible());
        assertEquals("Finalizado",turno1.getEstado());
    }

}
