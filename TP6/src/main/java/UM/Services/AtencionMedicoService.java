package UM.Services;

import java.util.Random;
import UM.Dao.MedicoDao;
import UM.Medico;

public class AtencionMedicoService {
    private static AtencionMedicoService instance;
    private MedicoDao medicoDao;

    private AtencionMedicoService(MedicoDao medicoDao) {
        this.medicoDao = medicoDao;
    }

    public static synchronized AtencionMedicoService getInstance(MedicoDao medicoDao) {
        if (instance == null) {
            instance = new AtencionMedicoService(medicoDao);
        }
        return instance;
    }

    public void empezarTurno(Medico medico) {

        // Generar aleatoriamente si se va a generar una receta
        Random random = new Random();
        boolean generarReceta = random.nextBoolean();
        if (generarReceta) {
            // Generar la receta
            generarReceta();
        }
    }



    private void generarReceta() {
        // Aquí iría la lógica para generar la receta
        System.out.println("Se ha generado una receta para el paciente.");
    }
}