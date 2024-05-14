package UM;

import java.util.HashMap;
import java.util.Map;

public class Receta {
    private String id;
    private Map<Medicamento, Integer> medicamentos;

    public Receta() {
        this.medicamentos = new HashMap<>();
    }

    public void agregarMedicamento(Medicamento medicamento, int cantidad) {
        medicamentos.put(medicamento, cantidad);
    }

    public Map<Medicamento, Integer> getMedicamentos() {
        return medicamentos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

