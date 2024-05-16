package TestDao;

import UM.*;
import UM.Dao.RecetaDao;
import UM.Dao.TurnoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTurnoDao {
    private TurnoDao turnoDao;
    Turno turnoParticular;
    Turno turnoConObraSocial;
    Medico medico;
    Especialidad especialidad;
    Paciente paciente;
    ObraSocial obraSocial;
    @BeforeEach
    void SetUP(){
        turnoDao= TurnoDao.getInstance();
        this.turnoParticular=new Turno(new Paciente("marcos","ibarra",false,null),new Medico("jesus","lopez",new Especialidad("Cardiología")),false);
        this.medico= new Medico("jesus","lopez",new Especialidad("Cardiología"));
        this.obraSocial=new ObraSocial("Damsu");
        this.especialidad=new Especialidad("Cardiologia");
        medico.agregarObraSocial(obraSocial);
        this.paciente=new Paciente("juana","garcia",true,obraSocial);
        this.turnoConObraSocial=new Turno (paciente,medico,true);
    }
    @Test
    void  agregar_Turno(){
        turnoDao.agregarTurno(turnoParticular);
        assertEquals(turnoParticular,turnoDao.buscarTurno(turnoParticular.getId()));
    }
    @Test
    void obtener_Turnos_Pendientes(){
        turnoParticular.setEstado("Pendiente");
        turnoConObraSocial.setEstado("En Curso");
        turnoDao.agregarTurno(turnoParticular);
        turnoDao.agregarTurno(turnoConObraSocial);
        List<Turno> turnosPendientes= turnoDao.ObtenerTurnosPendientes();
        assertEquals(1,turnosPendientes.size());

    }
    @Test
    void Obtener_turnos_en_curso(){
        turnoParticular.setEstado("Pendiente");
        turnoConObraSocial.setEstado("En Curso");
        turnoDao.agregarTurno(turnoParticular);
        turnoDao.agregarTurno(turnoConObraSocial);
        List<Turno> turnosEnCurso= turnoDao.ObtenerTurnosEnCurso();
        assertEquals(1,turnosEnCurso.size());

    }
    @Test
    void Actulizar_Estado(){
        turnoParticular.setEstado("Pendiente");
        turnoDao.agregarTurno(turnoParticular);
        turnoDao.actualizarEstado(turnoParticular.getId(),"En Curso");
        assertEquals("En Curso",turnoParticular.getEstado());

    }




}
