package TestServices;

import UM.Dao.MedicoDao;
import UM.Dao.PacienteDao;
import UM.Dao.TurnoDao;
import UM.Especialidad;
import UM.Medico;
import UM.ObraSocial;
import UM.Paciente;
import UM.Services.GestionTurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGestionTurnoService {
    private GestionTurnoService gestionTurnoService;
    private TurnoDao turnoDao;
    private MedicoDao medicoDao;
    private PacienteDao pacienteDao;
    Especialidad especialidad;
    @BeforeEach
    void SetUp(){
        turnoDao= TurnoDao.getInstance();
        medicoDao=MedicoDao.getInstance();
        pacienteDao=PacienteDao.getInstance();
        gestionTurnoService=GestionTurnoService.getInstance(turnoDao,medicoDao);
        this.especialidad=new Especialidad("Cardiología");
    }
    @Test
    void solicitar_Turno_Particular_Hay_medicos(){
        assertTrue(turnoDao.listarTurnos().isEmpty());
        Paciente paciente=new Paciente("Pedro","Torres",false,null);
        Medico  medico=new Medico("Lucas","Fernandez",especialidad);
        medicoDao.agregarMedico(medico);
        pacienteDao.agregarPaciente(paciente);
        gestionTurnoService.solicitarTurno(paciente,especialidad);
        assertFalse(turnoDao.listarTurnos().isEmpty());
    }
    @Test
    void solicitar_turno_particular_No_hay_medicos(){
        assertTrue(turnoDao.listarTurnos().isEmpty());
        Paciente paciente=new Paciente("Pedro","Torres",false,null);
        pacienteDao.agregarPaciente(paciente);
        gestionTurnoService.solicitarTurno(paciente,especialidad);
        assertTrue(turnoDao.listarTurnos().isEmpty());
    }
    @Test
    void Solicitar_turno_con_obra_social_que_coinciden(){
        assertTrue(turnoDao.listarTurnos().isEmpty());
        ObraSocial obraSocial=new ObraSocial("Damsu");
        Paciente paciente=new Paciente("Pedro","Torres",true,obraSocial);
        Medico  medico=new Medico("Lucas","Fernandez",especialidad);
        medico.agregarObraSocial(obraSocial);
        medicoDao.agregarMedico(medico);
        pacienteDao.agregarPaciente(paciente);
        gestionTurnoService.solicitarTurno(paciente,especialidad);
        assertFalse(turnoDao.listarTurnos().isEmpty());
    }
    @Test
    void Solicitar_turno_con_obra_social_que_no_coinciden(){
        assertTrue(turnoDao.listarTurnos().isEmpty());
        ObraSocial obraSocial=new ObraSocial("Damsu");
        ObraSocial obraSocial1=new ObraSocial("Osde");
        Paciente paciente=new Paciente("Pedro","Torres",true,obraSocial);
        Medico  medico=new Medico("Lucas","Fernandez",especialidad);
        medico.agregarObraSocial(obraSocial1);
        medicoDao.agregarMedico(medico);
        pacienteDao.agregarPaciente(paciente);
        gestionTurnoService.solicitarTurno(paciente,especialidad);
        assertTrue(turnoDao.listarTurnos().isEmpty());
    }
}
