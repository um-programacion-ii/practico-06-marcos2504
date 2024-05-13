package UM.Dao;

import UM.ObraSocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObraSocialDao { private Map<String, ObraSocial> obrasocials = new HashMap<>();
    private int proximoId = 1;

    public void agregar(String id, ObraSocial obraSocial) {
        obrasocials.put(id, obraSocial);
    }

    public void eliminar(String id) {
        obrasocials.remove(id);
    }

    public ObraSocial buscar(String id) {
        return obrasocials.get(id);
    }
    public List<ObraSocial> listar() {
        return new ArrayList<>(obrasocials.values());
    }
    public void agregar(ObraSocial obraSocial) {
        String id = "M" + proximoId++; // Generar un nuevo ID único
        obrasocials.put(id, obraSocial);}
}
