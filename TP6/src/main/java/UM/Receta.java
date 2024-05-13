package UM;
import java.util.List;

public class Receta {
    private List<Medicamento> medicamentos;


    public Receta(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
}

