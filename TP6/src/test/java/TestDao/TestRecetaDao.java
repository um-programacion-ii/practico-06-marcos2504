package TestDao;

import UM.Dao.RecetaDao;
import UM.Medicamento;
import UM.Paciente;
import UM.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecetaDao {
    private RecetaDao recetaDao;
    Receta receta;
    Receta receta1;
    Receta receta2;

    @BeforeEach
    void SetUp(){
        recetaDao= RecetaDao.getInstance();
        this.receta= new Receta();
        this.receta1= new Receta();
        this.receta2= new Receta();
        receta.agregarMedicamento(new Medicamento("IBUPROFENO"),3);
    }
    @Test
    void agregar_Receta() {
        recetaDao.agregarReceta(receta);
        assertEquals(receta, recetaDao.buscarReceta("R1"));
    }
    @Test
    void actulizar_Estado_En_Cuso(){
        receta.setEstado("Pendiente");
        recetaDao.agregarReceta(receta);
        recetaDao.actualizarEstado(receta.getId(),"En Curso");
        assertEquals("En Curso",receta.getEstado());
    }
    @Test
    void actulizar_Estado_Finalizado(){
        receta.setEstado("En Curso");
        recetaDao.agregarReceta(receta);
        recetaDao.actualizarEstado(receta.getId(),"Finalizado");
        assertEquals("Finalizado",receta.getEstado());
    }
    @Test
    void agregar_paciente(){
        Paciente paciente= new Paciente("juan","fernadez",false,null);
        recetaDao.agregarReceta(receta);
        recetaDao.agregarPaciente(receta.getId(), paciente);
        assertEquals(paciente,receta.getPaciente());
    }
    @Test
    void Obtener_Recetas_Pendientes(){
        receta.setEstado("Pendiente");
        receta1.setEstado("En Curso");
        receta2.setEstado("Pendiente");
        recetaDao.agregarReceta(receta);
        recetaDao.agregarReceta(receta1);
        recetaDao.agregarReceta(receta2);
        List<Receta> recetasPendientes= recetaDao.ObtenerRecetasPendientes();
        assertEquals(2,recetasPendientes.size());

    }
    @Test
    void Obtener_Recetas_En_Curso(){
        receta.setEstado("Pendiente");
        receta1.setEstado("En Curso");
        receta2.setEstado("Pendiente");
        recetaDao.agregarReceta(receta);
        recetaDao.agregarReceta(receta1);
        recetaDao.agregarReceta(receta2);
        List<Receta> recetasEnCurso= recetaDao.ObtenerRecetasEnCurso();
        assertEquals(1,recetasEnCurso.size());

    }


}
