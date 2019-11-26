package dom;

import org.junit.Assert;
import org.junit.Test;
import java.util.Map;

public class DOMGolesTest {

    @Test
    public void devolverVisitante3GolesACauteruccio() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_muchos_goles.xml";

        Map<String, Integer> DicGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        int cantidadGolesDic = DicGolesVisitante.get("Martín Cauteruccio");

        Assert.assertEquals("El metodo devolverVisitante3GolesACauteruccio no esta devolviendo el valor esperado",
                cantidadGolesDic, 3);
    }

    @Test
    public void devolverVisitante2GolesARescaldani() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_muchos_goles.xml";

        Map<String, Integer> DicGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        int cantidadGolesDic = DicGolesVisitante.get("Ezequiel Rescaldani");

        Assert.assertEquals("El metodo devolverVisitante2GolesARescaldani no esta devolviendo el valor esperado",
                cantidadGolesDic, 2);
    }

    @Test
    public void devolverVisitante1GolAMandarino() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_muchos_goles.xml";

        Map<String, Integer> DicGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        int cantidadGolesDic = DicGolesVisitante.get("Germán Mandarino");

        Assert.assertEquals("El metodo devolverVisitante1GolAMandarino no esta devolviendo el valor esperado",
                cantidadGolesDic, 1);
    }

    @Test
    public void devolverLocal1GolAAciar() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_muchos_goles.xml";
        Map<String, Integer> DicGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);
        int cantidadGolesDic = DicGolesLocal.get("Walter Aciar");

        Assert.assertEquals("El metodo devolverLocal1GolAAciar no esta devolviendo el valor esperado",
                cantidadGolesDic, 1);
    }

    @Test
    public void devolver0Goles() throws Exception {
        DOMGoles domGoles = new DOMGoles();
        String filename = "quilmes_2012_sin_goles.xml";

        Map<String, Integer> DicGolesVisitante = domGoles.dameDiccionarioAutorCantidadGoles("visitante", filename);
        Map<String, Integer> DicGolesLocal = domGoles.dameDiccionarioAutorCantidadGoles("local", filename);

        Assert.assertEquals("El metodo devolver0Goles no esta devolviendo el valor esperado para el local",
                DicGolesLocal.size(), 0);

        Assert.assertEquals("El metodo devolver0Goles no esta devolviendo el valor esperado para el visitante",
                DicGolesVisitante.size(), 0);
    }

}
