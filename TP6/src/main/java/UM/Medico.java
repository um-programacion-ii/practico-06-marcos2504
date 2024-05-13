package UM;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Medico {
    private String nombre;
    private String apellido;
    private Especialidad especialidad;
    private boolean disponible;

    public Medico(String nombre, String apellido, Especialidad especialidad,Boolean disponible) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.disponible= true;



    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public Receta generarReceta() {
        Random random = new Random();
        if (random.nextBoolean()) { // Hay una probabilidad de generar una receta
            int numMedicamentos = random.nextInt(3) + 1; // Generar un número aleatorio de medicamentos (entre 1 y 3)
            List<Medicamento> medicamentos = new ArrayList<>();
            for (int i = 0; i < numMedicamentos; i++) {
                medicamentos.add(new Medicamento("Medicamento " + (i + 1)));
            }
            return new Receta(medicamentos);
        } else {
            return null; // No se genera una receta
        }
    }
}
