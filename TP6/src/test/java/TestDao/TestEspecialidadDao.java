package TestDao;

import UM.Dao.EspecialidadDao;
import UM.Dao.PacienteDao;
import UM.Especialidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEspecialidadDao {
    Especialidad especialidad;
    private EspecialidadDao especialidadDao;

    @BeforeEach
    void setUp() {
        especialidadDao = especialidadDao.getInstance();
        this.especialidad = new Especialidad("Cardiologia");

    }
    @Test
    void agregar_Especialidad() {
        especialidadDao.agregar(especialidad);
        assertEquals(especialidad,especialidadDao.buscar("E1"));
    }

}
