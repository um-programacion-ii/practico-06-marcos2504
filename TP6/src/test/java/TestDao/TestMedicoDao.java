package TestDao;

import UM.Dao.MedicoDao;
import UM.Especialidad;
import UM.Medico;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import UM.ObraSocial;

public class TestMedicoDao {

        private MedicoDao medicoDao;
        Medico medico;
        Medico medico1;
        @BeforeEach
        void setUp() {
            medicoDao = MedicoDao.getInstance();
            this.medico = new Medico("Juan", "Perez", new Especialidad("Pediatría"));
            this.medico1 = new Medico("Juanito", "Perez", new Especialidad("Cardiología"));
        }

        @Test
        void agregarMedico_AgregaNuevoMedico() {
            medicoDao.agregarMedico(medico);
            assertEquals(medico,medicoDao.buscarMedico("M1"));
        }

        @Test
        void incrementarContadorTurno() {
            medicoDao.agregarMedico(medico);
            medicoDao.incrementarContadorTurnos(medico.getId());
            assertEquals(1, medico.getContadorTurnos());
        }
        @Test
        void decrementarContadorTurnos() {
            medicoDao.agregarMedico(medico);
            medicoDao.incrementarContadorTurnos(medico.getId());
            assertEquals(1, medico.getContadorTurnos());
            medicoDao.decrementarContadorTurnos(medico.getId());
            assertEquals(0, medico.getContadorTurnos());
        }
        @Test
        void listar_medicos() {
            medicoDao.agregarMedico(medico);
            medicoDao.agregarMedico(medico1);
            List<Medico> listamedicos = medicoDao.listarMedicos();
            assertEquals(2,listamedicos.size());
    }
        @Test
        void listar_medicos_con_obra_social() {
            medico.agregarObraSocial(new ObraSocial("DAMSU"));
            medicoDao.agregarMedico(medico);
            medicoDao.agregarMedico(medico1);
            List<Medico> listamedicos = medicoDao.listarMedicosObraSocial();
            assertEquals(1,listamedicos.size());
        }
        @Test
        void actulizar_disponibilidad_true_a_false(){
            medico.setDisponible(true);
            medicoDao.agregarMedico(medico);
            medicoDao.actualizarDisponibilidadMedico(medico.getId(),false);
            assertFalse(medico.isDisponible());
        }
        @Test
        void actulizar_disponibilidad_false_a_true(){
            medico.setDisponible(false);
            medicoDao.agregarMedico(medico);
            medicoDao.actualizarDisponibilidadMedico(medico.getId(),true);
            assertTrue(medico.isDisponible());
    }




}


