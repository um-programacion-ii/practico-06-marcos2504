package TestServices;

import UM.Dao.PacienteDao;
import UM.Dao.RecetaDao;
import UM.Medicamento;
import UM.Paciente;
import UM.Receta;
import UM.Services.GestionFarmaciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGestionFarmaciaService {
    private PacienteDao pacienteDao;
    private RecetaDao recetaDao;
    private GestionFarmaciaService gestionFarmaciaService;
    @BeforeEach
    void SetUp(){
        pacienteDao=PacienteDao.getInstance();
        recetaDao=RecetaDao.getInstance();
        gestionFarmaciaService=GestionFarmaciaService.getInstance();

    }
    @Test
    void iniciar_compras(){
        Receta receta = new Receta();
        Medicamento medicamento= new Medicamento("Ibuprofeno");
        receta.agregarMedicamento(medicamento,4);
        Paciente paciente= new Paciente("Marcos","Ibarra",false,null);
        paciente.agregarReceta(receta);
        receta.setPaciente(paciente);
        gestionFarmaciaService.agregarMedicamentoAlInventario(medicamento,500);
        pacienteDao.agregarPaciente(paciente);
        recetaDao.agregarReceta(receta);
        assertEquals("Pendiente",receta.getEstado());
        assertTrue(paciente.isDisponible());
        gestionFarmaciaService.iniciarCompras();
        assertEquals("En Curso",receta.getEstado());
        assertFalse(paciente.isDisponible());
    }
    @Test
    void finalizar_compras(){
        Receta receta = new Receta();
        Medicamento medicamento= new Medicamento("Ibuprofeno");
        receta.agregarMedicamento(medicamento,4);
        Paciente paciente= new Paciente("Marcos","Ibarra",false,null);
        paciente.agregarReceta(receta);
        receta.setPaciente(paciente);
        paciente.setDisponible(false);
        receta.setEstado("En Curso");
        gestionFarmaciaService.agregarMedicamentoAlInventario(medicamento,500);
        pacienteDao.agregarPaciente(paciente);
        recetaDao.agregarReceta(receta);
        gestionFarmaciaService.finalizarCompras();
        assertEquals("Finalizada",receta.getEstado());
        assertTrue(paciente.isDisponible());
    }


}

