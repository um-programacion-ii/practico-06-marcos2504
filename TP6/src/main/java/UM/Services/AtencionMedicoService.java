package UM.Services;

import java.util.Random;
import UM.Dao.MedicoDao;
import UM.Medico;
import UM.Medicamento;
import java.util.List;
import java.util.ArrayList;
import UM.Receta;
import UM.Dao.RecetaDao;
public class AtencionMedicoService {
    private static AtencionMedicoService instance;
    private MedicoDao medicoDao;
    private RecetaDao recetaDao;

    private AtencionMedicoService(MedicoDao medicoDao) {
        this.medicoDao = medicoDao;
    }

    public static synchronized AtencionMedicoService getInstance(MedicoDao medicoDao) {
        if (instance == null) {
            instance = new AtencionMedicoService(medicoDao);
        }
        return instance;
    }
    private static final List<Medicamento> MEDICAMENTOS_DISPONIBLES = new ArrayList<>();

    static {
        // Agregar medicamentos disponibles a la lista
        MEDICAMENTOS_DISPONIBLES.add(new Medicamento("Paracetamol"));
        MEDICAMENTOS_DISPONIBLES.add(new Medicamento("Ibuprofeno"));
        MEDICAMENTOS_DISPONIBLES.add(new Medicamento("Omeprazol"));
        MEDICAMENTOS_DISPONIBLES.add(new Medicamento("Amoxicilina"));
        MEDICAMENTOS_DISPONIBLES.add(new Medicamento("Loratadina"));
    }

    public void empezarTurno() {

    }



    public Receta generarReceta() {
        Random random = new Random();

        // Generar aleatoriamente la posibilidad de generar una receta
        boolean generarReceta = random.nextBoolean();

        // Si no se genera una receta, devolver null
        if (!generarReceta) {
            return null;
        }

        // Crear una instancia de receta
        Receta receta = new Receta();

        // Generar aleatoriamente la cantidad de medicamentos en la receta
        int cantidadMedicamentos = random.nextInt(5) + 1; // El rango es [1, 5]

        // Elegir aleatoriamente los medicamentos de la lista y agregarlos a la receta
        for (int i = 0; i < cantidadMedicamentos; i++) {
            int indiceMedicamento = random.nextInt(MEDICAMENTOS_DISPONIBLES.size());
            Medicamento medicamento = MEDICAMENTOS_DISPONIBLES.get(indiceMedicamento);
            int cantidad = random.nextInt(5) + 1; //
            receta.agregarMedicamento(medicamento, cantidad);
        }
        recetaDao.agregarReceta(receta);

        return receta;
    }
}
