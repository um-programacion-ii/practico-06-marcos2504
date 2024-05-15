package UM.Services;

import java.util.Random;

import UM.*;
import UM.Dao.MedicoDao;
import UM.Dao.PacienteDao;
import UM.Dao.TurnoDao;

import java.util.List;
import java.util.ArrayList;

import UM.Dao.RecetaDao;

public class AtencionMedicoService {
    private static AtencionMedicoService instance;
    private MedicoDao medicoDao;
    private RecetaDao recetaDao;
    private PacienteDao pacienteDao;
    private final TurnoDao turnoDao;

    private AtencionMedicoService(MedicoDao medicoDao, TurnoDao turnoDao,PacienteDao pacienteDao,RecetaDao recetaDao) {
        this.medicoDao = medicoDao;
        this.turnoDao = turnoDao;
        this.pacienteDao=pacienteDao;
        this.recetaDao=recetaDao;
    }

    public static synchronized AtencionMedicoService getInstance(MedicoDao medicoDao, TurnoDao turnoDao,PacienteDao pacienteDao,RecetaDao recetaDao) {
        if (instance == null) {
            instance = new AtencionMedicoService(medicoDao,turnoDao,pacienteDao,recetaDao);
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



    public void EmpezarTurnos() {
            List<Turno> turnosPendientes = turnoDao.ObtenerTurnosPendientes();
            for (Turno turno : turnosPendientes) {
                // Verificar si el médico asociado al turno está disponible
                Medico medico = turno.getMedico();
                Paciente paciente = turno.getPaciente();
                if (medico.isDisponible() && paciente.isDisponible()) {
                    medicoDao.actualizarDisponibilidadMedico(medico.getId(), false);
                    pacienteDao.actualizarDisponible(paciente.getId(), false);
                    turnoDao.actualizarEstado(turno.getId(), "En Curso");
                    medicoDao.decrementarContadorTurnos(medico.getId());
                }
            }

        }

    public void FinalizarTurnos() {

            List<Turno> turnosEnCurso = turnoDao.ObtenerTurnosEnCurso();
            for (Turno turno : turnosEnCurso) {
                // Verificar si el médico asociado al turno está disponible
                Medico medico = turno.getMedico();
                Paciente paciente = turno.getPaciente();
                medicoDao.actualizarDisponibilidadMedico(medico.getId(), true);
                pacienteDao.actualizarDisponible(paciente.getId(), true);
                turnoDao.actualizarEstado(turno.getId(), "Finalizado");
                Receta receta= generarReceta();
                recetaDao.agregarPaciente(receta.getId(),paciente);
                pacienteDao.agregarReceta(paciente.getId(), receta);

            }

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
