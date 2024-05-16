package TestDao;


import UM.Dao.ObraSocialDao;
import UM.ObraSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestObraSocialDao {
    ObraSocial obraSocial;
    private ObraSocialDao obraSocialDao;

    @BeforeEach
    void setUp() {
        obraSocialDao = obraSocialDao.getInstance();
        this.obraSocial = new ObraSocial("Damsu");

    }
    @Test
    void agregar_ObraSocial() {
        obraSocialDao.agregar(obraSocial);
        assertEquals(obraSocial,obraSocialDao.buscar("O1"));
    }

}

