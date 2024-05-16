package TestDao;

import UM.*;
import UM.Dao.MedicoDao;
import UM.Dao.PacienteDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestPacienteDao {
    private PacienteDao pacienteDao;
    private Map<String,Paciente> pacientes;
    Paciente paciente;

    Paciente paciente1;
    Receta receta;
    @BeforeEach
    void setUp() {
        pacienteDao = PacienteDao.getInstance();
        this.paciente=new Paciente("JUAN","GARCIA",false,null);
        this.paciente1=new Paciente("JUANito","GARCIA",true,new ObraSocial("Damsu"));
        this.receta=new Receta();

    }
    @Test
    void agregarMedico_AgregaNuevoMedico() {
        pacienteDao.agregarPaciente(paciente);
        assertEquals(paciente,pacienteDao.buscarPaciente("P1"));
    }
    @Test
    void agregar_receta() {
        pacienteDao.agregarPaciente(paciente);
        receta.agregarMedicamento(new Medicamento("Ibuprofeno"),1);
        pacienteDao.agregarReceta(paciente.getId(),receta);
        List<Receta> recetas=paciente.getRecetas();
        assertFalse(recetas.isEmpty());
    }
    @Test
    void listar_pacientes_con_receta() {
        pacienteDao.agregarPaciente(paciente);
        pacienteDao.agregarPaciente(paciente1);
        receta.agregarMedicamento(new Medicamento("Ibuprofeno"),1);
        pacienteDao.agregarReceta(paciente.getId(),receta);
        List<Paciente> pacientesConRecetas=pacienteDao.listarpacientesConRecetas();
        assertEquals(1,pacientesConRecetas.size());
    }
    @Test
    void actulizar_disponibilidad_true_a_false(){
        paciente.setDisponible(true);
        pacienteDao.agregarPaciente(paciente);
        pacienteDao.actualizarDisponible(paciente.getId(),false);
        assertFalse(paciente.isDisponible());
    }
    @Test
    void actulizar_disponibilidad_false_a_true(){
        paciente.setDisponible(false);
        pacienteDao.agregarPaciente(paciente);
        pacienteDao.actualizarDisponible(paciente.getId(),true);
        assertTrue(paciente.isDisponible());
    }



}
