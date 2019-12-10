package dom;

import org.junit.Assert;
import org.junit.Test;

public class DOMCapitanEquipoTest {

    @Test
    public void dameCapitanLocal() throws Exception {
        DOMCapitanEquipo domCapitanEquipo = new DOMCapitanEquipo();
        String filePath = "quilmes_2012.xml";
        String capitan = domCapitanEquipo.dameCapitan("local",filePath);

        Assert.assertEquals("El método dameCapitanLocal no está devolviendo lo esperado",
                "Mario Vera", capitan);
    }

    @Test
    public void dameCapitanVisitante() throws Exception {
        DOMCapitanEquipo domCapitanEquipo = new DOMCapitanEquipo();
        String filePath = "quilmes_2012.xml";
        String capitan = domCapitanEquipo.dameCapitan("visitante",filePath);

        Assert.assertEquals("El método dameCapitanVisitante no está devolviendo lo esperado",
                "Miguel Caneo", capitan);
    }

}
