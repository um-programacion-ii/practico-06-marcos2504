package UM.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import UM.Dao.PacienteDao;
import UM.Dao.RecetaDao;
import UM.Paciente;
import UM.Receta;
import UM.Medicamento;
import  java.util.Map;


public class GestionFarmaciaService {
    private static GestionFarmaciaService instance;
    private PacienteDao pacienteDao;
    private RecetaDao recetaDao;
    private Map<Medicamento, Integer> inventario;

    private GestionFarmaciaService() {
        this.inventario =  new HashMap<>();
        this.pacienteDao = PacienteDao.getInstance();
        this.recetaDao=RecetaDao.getInstance();
    }

    public static GestionFarmaciaService getInstance() {
        if (instance == null) {
            instance = new GestionFarmaciaService();
        }
        return instance;
    }

    public void iniciarCompras() {
        List<Receta> recetasPendientes = recetaDao.ObtenerRecetasPendientes();
        for (Receta receta : recetasPendientes) {
            Paciente paciente = receta.getPaciente();
           if (paciente.isDisponible()) {
                // Cambiar el estado del paciente a no disponible
                pacienteDao.actualizarDisponible(paciente.getId(), false);
                // Cambiar el estado de la receta a en curso
                recetaDao.actualizarEstado(receta.getId(), "En Curso");
            }
        }
    }
    public void finalizarCompras() {
        List<Receta> recetasEnCurso = recetaDao.ObtenerRecetasEnCurso();
        for (Receta receta : recetasEnCurso) {
            Paciente paciente = receta.getPaciente();
            boolean medicamentosDisponibles = verificarDisponibilidadMedicamentos(receta);

            if (medicamentosDisponibles) {
                // Cambiar el estado del paciente a disponible
                pacienteDao.actualizarDisponible(paciente.getId(), true);
                // Cambiar el estado de la receta a finalizada
                recetaDao.actualizarEstado(receta.getId(), "Finalizada");
            } else {
                // Cambiar el estado del paciente a disponible
                pacienteDao.actualizarDisponible(paciente.getId(), true);
                // Cambiar el estado de la receta a pendiente
                recetaDao.actualizarEstado(receta.getId(), "Pendiente");
                System.out.println("Agun medicamento no estaba disponible ");
            }
        }
    }
        public boolean verificarDisponibilidadMedicamentos(Receta receta) {
            Map<Medicamento, Integer> medicamentosReceta = receta.getMedicamentos();

            for (Map.Entry<Medicamento, Integer> entry : medicamentosReceta.entrySet()) {
                Medicamento medicamentoReceta = entry.getKey();
                int cantidadReceta = entry.getValue();

                // Verificar si el medicamento de la receta está en el inventario de la farmacia
                if (!inventario.containsKey(medicamentoReceta)) {
                    return false; // El medicamento no está en el inventario
                }

                // Verificar si hay suficiente cantidad del medicamento en el inventario
                int cantidadInventario = inventario.get(medicamentoReceta);
                if (cantidadInventario < cantidadReceta) {
                    agregarMedicamentoAlInventario(medicamentoReceta,100);
                    return false; // No hay suficiente cantidad del medicamento en el inventario
                }
            }

            // Todos los medicamentos de la receta están disponibles en el inventario
            return true;
        }
    public void agregarMedicamentoAlInventario(Medicamento medicamento, int cantidad) {
        if (inventario.containsKey(medicamento)) {
            // Si el medicamento ya está en el inventario, actualizamos la cantidad
            int cantidadExistente = inventario.get(medicamento);
            inventario.put(medicamento, cantidadExistente + cantidad);
        } else {
            // Si el medicamento no está en el inventario, lo agregamos con la cantidad especificada
            inventario.put(medicamento, cantidad);
        }
    }
}

