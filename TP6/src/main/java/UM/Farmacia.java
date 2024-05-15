package UM;

import java.util.HashMap;
import java.util.Map;

public class Farmacia {
    private Map<String, Integer> inventario;

    public Farmacia() {
        this.inventario = new HashMap<>();
    }

    // Método para agregar un medicamento al inventario de la farmacia
    public void agregarMedicamento(String nombreMedicamento, int cantidad) {
        inventario.put(nombreMedicamento, cantidad);
    }

    // Método para verificar la disponibilidad de un medicamento en la farmacia
    public boolean verificarDisponibilidad(String nombreMedicamento) {
        Integer cantidadDisponible = inventario.get(nombreMedicamento);
        return cantidadDisponible != null && cantidadDisponible > 0;
    }
}
