package UM.Dao;
import UM.Receta;
import java.util.*;

public class RecetaDao {
    private Map<String, Receta> recetas = new HashMap<>();
    private int proximoId = 1;


    public void eliminarReceta(String id) {
        recetas.remove(id);
    }

    public Receta buscarReceta(String id) {
        return recetas.get(id);
    }

    public List<Receta> listarRecetas() {
        return new ArrayList<>(recetas.values());
    }

    public void agregarReceta(Receta receta) {
        String id = "R" + proximoId++; // Generar un nuevo ID único
        recetas.put(id, receta);
    }
}

