package UM;

import java.util.HashMap;
import java.util.Map;

public class Receta {
    private String id;
    private Map<Medicamento, Integer> medicamentos;
    private Paciente paciente;

    private String  estado;

    public Receta() {
        this.medicamentos = new HashMap<>();
        this.estado="Pendiente";
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}

